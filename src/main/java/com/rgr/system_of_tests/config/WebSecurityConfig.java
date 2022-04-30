package com.rgr.system_of_tests.config;

import com.rgr.system_of_tests.repo.models.Role;
import com.rgr.system_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService usersService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/registration","/activate/*","/img/*").permitAll()
                .antMatchers("/test").authenticated()
                .antMatchers("/test/*").authenticated()
                .antMatchers("/test/*/edit").hasAnyAuthority(Role.ADMIN.getAuthority(), Role.TESTER.getAuthority())
                .antMatchers("/test/*/remove").hasAnyAuthority(Role.ADMIN.getAuthority(), Role.TESTER.getAuthority())
                .antMatchers("/test/add").hasAnyAuthority(Role.ADMIN.getAuthority(), Role.TESTER.getAuthority())
                .antMatchers("/admin").hasAuthority(Role.ADMIN.getAuthority())
                .antMatchers("/admin/*/edit").hasAuthority(Role.ADMIN.getAuthority())
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