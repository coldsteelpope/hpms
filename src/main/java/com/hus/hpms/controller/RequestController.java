package com.hus.hpms.controller;

import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.request.RequestCRUDParam;
import com.hus.hpms.dto.request.RequestCreateParam;
import com.hus.hpms.dto.request.RequestUpdateParam;
import com.hus.hpms.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController
{
    private final RequestService requestService;
    @PostMapping("/create")
    public String create(RequestCreateParam requestCreateParam)
    {
        List<Request> requests = new ArrayList<>();
        setRequests(requests, requestCreateParam);
        String id = requestService.save(requests).get(0).getId();
        return "redirect:/request/" + id;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, RequestUpdateParam requestUpdateParam)
    {
        List<Request> requests = new ArrayList<>();
        log.info("requestUpdateParam = {}", requestUpdateParam);
        setRequests(requests, requestUpdateParam);
        requestService.update(requests, id);
        return "redirect:/request/" + id;
    }

    @PostMapping("/status/update/processing/{id}")
    public String updateProcessingStatus(@PathVariable("id") String id)
    {
        requestService.updateStatus(id, "processing");
        return "redirect:/request/" + id;
    }

    @PostMapping("/status/update/done/{id}")
    public String updateDoneStatus(@PathVariable("id") String id)
    {
        requestService.updateStatus(id, "done");
        return "redirect:/request/" + id;
    }

    private void setRequests(List<Request> requests, RequestCRUDParam requestCRUDParam)
    {
        List<String> departmentIds = requestCRUDParam.getDepartmentIds();

        for (int index = 0; index < departmentIds.size(); ++index)
        {
            Request request = new Request();
            request.setDepartmentId(Long.parseLong(departmentIds.get(index)));
            request.setArea(requestCRUDParam.getArea());
            request.setDetailArea(requestCRUDParam.getDetailArea().get(Integer.parseInt(request.getArea()) - 1));
            request.setRequest(requestCRUDParam.getRequest());
            request.setStatus("ready");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime parsedRequestDate = LocalDate.parse(requestCRUDParam.getCurrentDate(), formatter).atStartOfDay();
            LocalDateTime parsedExpectedCompleteDate = LocalDate.parse(requestCRUDParam.getExpectedCompleteDate(), formatter).atStartOfDay();

            request.setRequestDate(parsedRequestDate);
            request.setExpectedCompleteDate(parsedExpectedCompleteDate);
            request.setInsertDate(LocalDateTime.now());
            request.setUpdateDate(LocalDateTime.now());
            requests.add(request);
        }
    }
}
