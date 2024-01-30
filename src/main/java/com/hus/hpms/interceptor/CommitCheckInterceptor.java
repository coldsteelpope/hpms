package com.hus.hpms.interceptor;

import com.hus.hpms.constants.SessionConst;
import com.hus.hpms.dto.department.DepartmentSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class CommitCheckInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        DepartmentSession departmentSession = (DepartmentSession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(!departmentSession.getCommit())
        {
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
