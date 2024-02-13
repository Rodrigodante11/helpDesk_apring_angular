package com.rodrigo.helpdesk.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");  // pegando o token

        if(header != null && header.startsWith("Bearer ")){ // conferindo se existe e se contem Bearer
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7)); // pegando apenas o token sem o "Bearer " = 7

            if(authToken !=null){ // Token valido
                SecurityContextHolder.getContext().setAuthentication(authToken); //
            }
        }
        chain.doFilter(request, response );
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if(jwtUtil.tokenValido(token)) {   // verifica se o token eh valido.tokenValido())
            String usarname = jwtUtil.getUsername(token);
            UserDetails details = userDetailsService.loadUserByUsername(usarname);
            return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());

        }
        return null;
    }


}
