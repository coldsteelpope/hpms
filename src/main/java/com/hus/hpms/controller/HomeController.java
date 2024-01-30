package com.hus.hpms.controller;

import com.hus.hpms.constants.ElementNames;
import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.domain.Comment;
import com.hus.hpms.domain.Department;
import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.department.DepTypeElement;
import com.hus.hpms.dto.department.DepartmentPerformance;
import com.hus.hpms.dto.department.DepartmentSession;
import com.hus.hpms.dto.department.MajorTypeElement;
import com.hus.hpms.dto.request.RequestUpdateInitParam;
import com.hus.hpms.service.CommentService;
import com.hus.hpms.service.DepartmentService;
import com.hus.hpms.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.engine.ElementName;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController
{
    private final DepartmentService departmentService;
    private final RequestService requestService;
    private final CommentService commentService;
    @GetMapping("/")
    public String home(Model model)
    {
        List<DepartmentPerformance> depTypeDepartmentsPerformance = departmentService.findAllDepTypeDepartmentsPerformance();
        model.addAttribute("departments", depTypeDepartmentsPerformance);

        return "index";
    }

    @GetMapping("/major")
    public String major(Model model)
    {
        List<DepartmentPerformance> majorTypeDepartmentsPerformace = departmentService.findAllMajorTypeDepartmentsPerformace();
        model.addAttribute("departments", majorTypeDepartmentsPerformace);
        return "major";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @GetMapping("/request/{id}")
    public String request(@PathVariable String id, Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)DepartmentSession departmentSession)
    {
        List<Request> requests = requestService.findById(id);
        List<Department> departments = new ArrayList<>();
        List<Comment> comments = commentService.findAllByRequestId(id);

        for (Request request : requests) {
            Long departmentId = request.getDepartmentId();
            Optional<Department> department = departmentService.findById(departmentId);
            department.ifPresent(departments::add);
        }

        model.addAttribute("departmentSession", departmentSession);
        model.addAttribute("departments", departments);
        model.addAttribute("requests", requests);
        model.addAttribute("comments", comments);
        return "request/request";
    }

    @GetMapping("/request/create")
    public String requestCreate(Model model)
    {
        model.addAttribute("collegeNames", ElementNames.collegeNamesKor);
        model.addAttribute("areaNames", ElementNames.areaNames);
        model.addAttribute("detailOneSelectNames", ElementNames.detailOneSelectNames);
        model.addAttribute("detailTwoSelectNames", ElementNames.detailTwoSelectNames);
        model.addAttribute("detailThreeSelectNames", ElementNames.detailThreeSelectNames);
        model.addAttribute("detailFourSelectNames", ElementNames.detailFourSelectNames);
        model.addAttribute("detailFiveSelectNames", ElementNames.detailFiveSelectNames);

        setDepartmentsAndMajorsInModel(model);
        return "request/createRequest";
    }

    @GetMapping("/request/update/{id}")
    public String requestUpdate(@PathVariable("id") String id, Model model)
    {
        setDepartmentsAndMajorsInModel(model);
        List<Request> requests = requestService.findById(id);

        RequestUpdateInitParam requestUpdateInitParam = new RequestUpdateInitParam();
        setRequestUpdateInitParam(requestUpdateInitParam, requests);

        model.addAttribute("requests", requests);
        model.addAttribute("requestUpdateInitParam", requestUpdateInitParam);
        model.addAttribute("collegeNames", ElementNames.collegeNamesKor);


        model.addAttribute("areaNames", ElementNames.areaNames);
        model.addAttribute("detailOneSelectNames", ElementNames.detailOneSelectNames);
        model.addAttribute("detailTwoSelectNames", ElementNames.detailTwoSelectNames);
        model.addAttribute("detailThreeSelectNames", ElementNames.detailThreeSelectNames);
        model.addAttribute("detailFourSelectNames", ElementNames.detailFourSelectNames);
        model.addAttribute("detailFiveSelectNames", ElementNames.detailFiveSelectNames);

        log.info("requestUpdateInitParam = {}", requestUpdateInitParam);
        return "request/updateRequest";
    }

    @GetMapping("/master")
    public String master(Model model)
    {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("collegeNames", ElementNames.collegeNamesKor);

        return "master/master";
    }

    @GetMapping("/restrict/admin")
    public String restrictAdmin()
    {
        return "restrict/admin";
    }

    @GetMapping("/restrict/master")
    public String restrictMaster()
    {
        return "restrict/master";
    }

    private void setRequestUpdateInitParam(RequestUpdateInitParam requestUpdateInitParam, List<Request> requests)
    {
        requestUpdateInitParam.setArea(requests.get(0).getArea());
        requestUpdateInitParam.setDetailArea(requests.get(0).getDetailArea());
        requestUpdateInitParam.setRequest(requests.get(0).getRequest());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedRequestDate = requests.get(0).getRequestDate().format(formatter);
        String formattedExpectedCompleteDate = requests.get(0).getExpectedCompleteDate().format(formatter);

        requestUpdateInitParam.setRequestDate(formattedRequestDate);
        requestUpdateInitParam.setExpectedCompleteDate(formattedExpectedCompleteDate);

        for(Request request : requests) {
            Optional<Department> department = departmentService.findById(request.getDepartmentId());
            requestUpdateInitParam.getDepartmentIds().add(request.getDepartmentId());
            department.ifPresent(value -> requestUpdateInitParam.getDepartments().add(value));
        }
    }

    private void setDepartmentsAndMajorsInModel(Model model)
    {
        List<DepTypeElement> depTypeElements = departmentService.findAllTypeDep();
        model.addAttribute("depTypeElements", depTypeElements);

        for (int index = 0; index < ElementNames.collegeNamesEng.length; ++index)
        {
            model.addAttribute(ElementNames.collegeNamesEng[index], departmentService.findAllTypeMajor(ElementNames.collegeNamesKor[index]));
        }
    }
}
