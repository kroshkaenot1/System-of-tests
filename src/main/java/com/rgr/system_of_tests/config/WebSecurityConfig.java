package com.rgr.system_of_tests.config;

import com.rgr.system_of_tests.models.Roles;
import com.rgr.system_of_tests.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsersService usersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/test").authenticated()
                .antMatchers("/test/*/edit").hasAnyAuthority(Roles.ADMIN.getAuthority(),Roles.TESTER.getAuthority())
                .antMatchers("/test/*/remove").hasAnyAuthority(Roles.ADMIN.getAuthority(),Roles.TESTER.getAuthority())
                .antMatchers("/test/add").hasAnyAuthority(Roles.ADMIN.getAuthority(),Roles.TESTER.getAuthority())
                .antMatchers("/admin").hasAuthority(Roles.ADMIN.getAuthority())
                .antMatchers("/", "/home","/registration","/activate/*").permitAll()
                .antMatchers(HttpMethod.POST,"/test").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}