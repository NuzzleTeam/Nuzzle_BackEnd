<<<<<<< HEAD
//package com.nuzzle.backend.global.intercepter;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class UserIdInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        request.setAttribute("USER_ID", authentication.getName());
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//}
=======
package com.nuzzle.backend.global.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("USER_ID", authentication.getName());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
