package com.nuzzle.backend.security.handler.exception;

import com.nuzzle.backend.global.exception.ErrorCode;
import com.nuzzle.backend.security.info.AuthenticationResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // filter 단에서 발생한 에러 핸들링
        ErrorCode errorCode = (ErrorCode) request.getAttribute("exception");
        if (errorCode == null)
            AuthenticationResponse.makeFailureResponse(response, ErrorCode.MISSING_REQUEST_PARAMETER);
        AuthenticationResponse.makeFailureResponse(response, errorCode);
    }
}