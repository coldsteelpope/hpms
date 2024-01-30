package com.hus.hpms.interceptor;

import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.domain.Department;
import com.hus.hpms.dto.department.DepartmentSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        DepartmentSession departmentSession = (DepartmentSession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        log.info("[-] AdminCheckInterceptor");
        log.info("departmentSession = {}", departmentSession);

        if (!departmentSession.getAdmin())
        {
            response.sendRedirect("/restrict/admin");
            return false;
        }
        return true;
    }
}
