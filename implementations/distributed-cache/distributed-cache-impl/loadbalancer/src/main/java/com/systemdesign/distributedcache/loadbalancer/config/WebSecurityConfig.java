package com.systemdesign.distributedcache.loadbalancer.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration @EnableWebSecurity public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
