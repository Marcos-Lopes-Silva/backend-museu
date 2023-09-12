package com.museu.museu.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.museu.museu.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = recuperarToken(request);
            
            if(token != null) {

                var subject = tokenService.getSubject(token);
                

            }

            filterChain.doFilter(request, response);
    }
    

    private String recuperarToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");

        if (authorization != null && !authorization.equalsIgnoreCase("Bearer ")) {
            return authorization.split(" ")[1];
        }

        return null;
    }
    

}
