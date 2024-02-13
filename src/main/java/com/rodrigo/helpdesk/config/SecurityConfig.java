package com.rodrigo.helpdesk.config;

import com.rodrigo.helpdesk.security.JWTAuthenticationFilter;
import com.rodrigo.helpdesk.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final  String[] PUBLIC_MATCHERS = {"/h2-console/**"};

    @Autowired
    private Environment env;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){  // permintindo o h2 console caso for profile de "test"
            http.headers().frameOptions().disable();
        }


        http.cors().and().csrf().disable(); // por causa do @beans vai pegar o metodo abaixo automaticamente(corsConfigurationSource())

        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

        http.authorizeRequests()
                        .antMatchers(PUBLIC_MATCHERS).permitAll() // esse nao precisa de autenticacao
                        .anyRequest().authenticated(); // mas todo o resto precisa

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET" , "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**" , corsConfiguration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){  // critografando o password
        return new BCryptPasswordEncoder();
    }
}
