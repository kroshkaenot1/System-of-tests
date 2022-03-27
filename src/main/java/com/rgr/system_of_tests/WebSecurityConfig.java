package com.rgr.system_of_tests;

import com.rgr.system_of_tests.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home","/registration").permitAll()
                .antMatchers("/test").hasAnyRole(Roles.ADMIN.name(),Roles.TESTER.name(),Roles.USER.name())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Roles.ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Roles.ADMIN.name(),Roles.TESTER.name(),Roles.USER.name())
                .antMatchers(HttpMethod.POST,"/api/**").hasAnyRole(Roles.ADMIN.name(),Roles.TESTER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,active from users where username=?")
                .authoritiesByUsernameQuery("select u.username,ur.roles from users u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}