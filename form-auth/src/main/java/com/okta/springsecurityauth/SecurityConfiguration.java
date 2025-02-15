package com.okta.springsecurityauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/authorize", "/token", "/revoke")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic();
    }

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password("{noop}pass") // Spring Security 5 requires specifying the password storage format
            .roles("USER");
    }
*/
}



