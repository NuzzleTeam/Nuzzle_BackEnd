package com.nuzzle.backend.security.filter;

import com.nuzzle.backend.global.constants.Constants;
import com.nuzzle.backend.global.utility.HeaderUtil;
import com.nuzzle.backend.global.utility.JwtUtil;
import com.nuzzle.backend.security.info.UserPrincipal;
import com.nuzzle.backend.security.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new IllegalArgumentException("Authorization Header Is Not Found!"));
        Claims claims = jwtUtil.validateToken(token);

        UserPrincipal userPrincipal = (UserPrincipal) customUserDetailService.loadUserById(
                claims.get(Constants.CLAIM_USER_ID, Long.class));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userPrincipal, null, userPrincipal.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Constants.NO_NEED_AUTH_URLS.contains(request.getRequestURI())
                || request.getRequestURI().startsWith("/guest");
    }
}