package com.example.baitapvietis.configuration;

import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.service.UserService;
import com.example.baitapvietis.service.impl.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.authorizeRequests()
                .antMatchers("/users/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .antMatchers("/warehouses/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/warehouses/**").hasAuthority("ADMIN")
                .antMatchers("/antens/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/antens/**").hasAuthority("ADMIN")
                .antMatchers("/shelfs/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/shelfs/**").hasAuthority("ADMIN")
                .antMatchers("/readers/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/readers/**").hasAuthority("ADMIN")
                .antMatchers("/gates/list").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/gates/**").hasAuthority("ADMIN")
                .antMatchers("/home/index").authenticated()
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/auth/login/form")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/auth/login/success")
                .failureUrl("/auth/login/error")
                .usernameParameter("username")
                .passwordParameter("password");

        http.exceptionHandling()
                .accessDeniedPage("/auth/access/denied");

        http.logout()
                .logoutUrl("/auth/logoff")
                .logoutSuccessUrl("/auth/logoff/success");


    }
}
