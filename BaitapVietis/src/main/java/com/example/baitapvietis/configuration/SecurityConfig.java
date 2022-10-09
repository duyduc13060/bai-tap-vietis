package com.example.baitapvietis.configuration;

import com.example.baitapvietis.model.entity.UserEntity;
import com.example.baitapvietis.service.UserService;
import com.example.baitapvietis.service.impl.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
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
        System.out.println(userService + "usse");

//   auth.inMemoryAuthentication()
//           .withUser("user").password(passwordEncoder.encode("pass")).roles("USER")
//           .and()
//           .withUser("admin").password(passwordEncoder.encode("pass")).roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/users/add").hasRole("ADMIN")
                .antMatchers("/users/list").hasRole("USER")
                .antMatchers("/users/edit/**").hasRole("ADMIN")
                .antMatchers("/home/index").authenticated()
                .anyRequest().permitAll();

        http
                .formLogin()
                .loginPage("/auth/login/form")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/auth/login/success")
                .failureUrl("/auth/login/error")
                .usernameParameter("username")
                .passwordParameter("password");
        http
                .exceptionHandling()
                .accessDeniedPage("/auth/access/denied");

        http
                .logout()
                .logoutUrl("/auth/logoff")
                .logoutSuccessUrl("/auth/logoff/success");


    }
}
