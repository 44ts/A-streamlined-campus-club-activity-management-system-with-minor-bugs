package com.campusactivity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 静态资源放行
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/uploads/**").permitAll()
                // 公开页面放行
                .antMatchers("/", "/user/login", "/user/register", "/user/profile", "/user/profile/**", "/user/list", "/activity/list", "/announcement").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/organizer/**").hasRole("ORGANIZER")
                .anyRequest().permitAll()
            )
            .formLogin().disable()
            .logout().disable()
            .csrf().disable(); // 开发阶段暂时禁用CSRF

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}