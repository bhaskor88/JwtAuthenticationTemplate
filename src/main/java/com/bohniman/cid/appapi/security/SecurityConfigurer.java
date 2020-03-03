package com.bohniman.cid.appapi.security;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import com.bohniman.cid.appapi.filters.JwtRequestFilter;
import com.bohniman.cid.appapi.services.MyUserDetailsService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfigurer
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder bcrCryptPasswordEncoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {

        // BcryptPassword Encoder
        // System.out.println(new BCryptPasswordEncoder().encode("bhaskor"));
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println(new BCryptPasswordEncoder().encode("bhaskor"));
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // http.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
        // response.setContentType("application/json;charset=UTF-8");
        // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // response.getWriter().write(
        // new JSONObject().put("timestamp", LocalDateTime.now()).put("message", "Access
        // denied").toString());
        // });
    }

}