package com.nuzzle.backend.security.config;


import com.nuzzle.backend.global.constants.Constants;
import com.nuzzle.backend.global.utility.JwtUtil;
import com.nuzzle.backend.security.filter.GlobalLoggerFilter;
import com.nuzzle.backend.security.filter.JwtAuthenticationFilter;
import com.nuzzle.backend.security.filter.JwtExceptionFilter;
import com.nuzzle.backend.security.handler.jwt.JwtAccessDeniedHandler;
import com.nuzzle.backend.security.handler.jwt.JwtAuthEntryPoint;
import com.nuzzle.backend.security.handler.login.DefaultFailureHandler;
import com.nuzzle.backend.security.handler.login.DefaultSuccessHandler;
import com.nuzzle.backend.security.handler.login.Oauth2FailureHandler;
import com.nuzzle.backend.security.handler.login.Oauth2SuccessHandler;
import com.nuzzle.backend.security.handler.logout.CustomLogoutProcessHandler;
import com.nuzzle.backend.security.handler.logout.CustomLogoutResultHandler;
import com.nuzzle.backend.security.service.CustomOauth2UserDetailService;
import com.nuzzle.backend.security.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DefaultSuccessHandler defaultSuccessHandler;
    private final DefaultFailureHandler defaultFailureHandler;
    private final CustomLogoutProcessHandler customSignOutProcessHandler;
    private final CustomLogoutResultHandler customSignOutResultHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2FailureHandler oauth2FailureHandler;
    private final CustomOauth2UserDetailService customOauth2UserDetailService;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://nuz2le.com", "https://api.nuz2le.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
        configuration.setAllowCredentials(true);
        // 필요한 경우, 허용할 헤더와 기타 설정 추가
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // csrf 보호 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // httpBasic 기본 인증 방식 해제
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안하고 상태가 없는 방식으로 인증 = JWT 사용
                )

                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers(Constants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .requestMatchers(Constants.USER_URLS.toArray(String[]::new)).hasRole("USER")
                                .anyRequest().authenticated()
                )

                .formLogin(configurer ->
                        configurer
                                .loginPage("/login")
                                .loginProcessingUrl("/api/v1/auth/login")
                                .usernameParameter("serial_id")
                                .passwordParameter("password")
                                .successHandler(defaultSuccessHandler)
                                .failureHandler(defaultFailureHandler)
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oauth2SuccessHandler)
                        .failureHandler(oauth2FailureHandler)
                        .userInfoEndpoint(it -> it.userService(customOauth2UserDetailService))
                )
                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(customSignOutProcessHandler)
                                .logoutSuccessHandler(customSignOutResultHandler)
                )

                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil, customUserDetailService),
                        LogoutFilter.class)
                .addFilterBefore(
                        new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .addFilterBefore(
                        new GlobalLoggerFilter(),
                        JwtExceptionFilter.class)

                .getOrBuild();
    }
}