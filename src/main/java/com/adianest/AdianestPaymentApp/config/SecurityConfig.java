/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.config;

import com.adianest.AdianestPaymentApp.jwt.JwtConfig;
import com.adianest.AdianestPaymentApp.jwt.JwtTokenVerifierFilter;
import com.adianest.AdianestPaymentApp.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.adianest.AdianestPaymentApp.service.implement.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/**").permitAll();

        JwtUsernameAndPasswordAuthenticationFilter loginFilter =
                new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig);
        loginFilter.setFilterProcessesUrl("/login/mobile");

        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login/mobile").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/api/profile/picture/**").permitAll()
                .antMatchers("/api/**").hasAuthority(AppRole.ROLE_USER.name())
                .anyRequest().authenticated()
                .and()
                .addFilter(loginFilter)
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }
}
