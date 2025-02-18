package com.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")  // 관리자 페이지는 ADMIN 역할 필요
                .requestMatchers(
                    new AntPathRequestMatcher("/inquiry"),  // 문의하기 페이지
                    new AntPathRequestMatcher("/inquiry/**")  // 문의하기 관련 모든 하위 경로
                ).permitAll()  // 모든 사용자 접근 허용
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()  // 나머지는 모두 허용
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    new AntPathRequestMatcher("/h2-console/**"),
                    new AntPathRequestMatcher("/inquiry/**")  // 문의하기 관련 CSRF 비활성화
                )
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable())
            );
            
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
