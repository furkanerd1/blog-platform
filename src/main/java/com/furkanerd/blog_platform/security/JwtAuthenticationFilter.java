package com.furkanerd.blog_platform.security;

import com.furkanerd.blog_platform.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final AuthenticationService authenticationService;

        public JwtAuthenticationFilter(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {

            try {
                String token = extractToken(request);

                if (token != null) {
                    UserDetails userDetails = authenticationService.validToken(token);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // Add userId to request attributes for controller access
                    if (userDetails instanceof BlogUserDetails) {
                        request.setAttribute("userId", ((BlogUserDetails) userDetails).getId());
                    }
                }
            } catch (Exception e) {
                // Don't throw exceptions here - just don't authenticate the request
                log.warn("Received invalid auth token");
            }

            filterChain.doFilter(request, response);
        }

        private String extractToken(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }
            return null;
        }
    }
