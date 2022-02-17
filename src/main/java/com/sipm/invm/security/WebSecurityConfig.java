package com.sipm.invm.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic()
                .and()
                .authorizeRequests().antMatchers("/api/**")
                .hasRole("USER").antMatchers("/**")
                .hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("spuser").password("spuser")
                .roles("USER").and().withUser("admin").password("admin")
                .roles("USER","ADMIN");
    }
}
