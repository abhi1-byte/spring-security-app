package com.abhi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsService userDetailsService;

    BCryptPasswordEncoder encoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Autowired
    public void validateCredentials(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Bean
    public SecurityFilterChain customSecurityFilter(HttpSecurity httpSecurity) {
        try {
            httpSecurity.authorizeHttpRequests(authorize ->
                            authorize.requestMatchers("/user/register", "/showLogin", "/bank/").permitAll()
                                    .requestMatchers("/bank/offers").authenticated()
                                    .requestMatchers("/bank/balance").hasAnyRole("MANAGER", "CUSTOMER")
                                    .requestMatchers("/bank/loan").hasRole("MANAGER")
                                    .anyRequest().authenticated())
                    .formLogin(login -> login.loginPage("/showLogin") // TO launch Form page upon GET request
                            .defaultSuccessUrl("/bank/", true) // HomePage URL
                            .loginProcessingUrl("/login") // For POST mode request to submit and process the page..it's for spring security default, but can change
                            .failureUrl("/showLogin?error") //Authentication failed URL
                    )
                    .rememberMe(remember ->
                            remember.key("a-very-secret-and-long-key-value") // A private key to secure the cookie
                                    .tokenValiditySeconds(86400)// Cookie valid for 1 day (in seconds)))
                    ).logout(logout -> logout.logoutUrl("/logout")// default, but can change
                            .logoutSuccessUrl("/logout-success")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID", "remember-me"))
                    .exceptionHandling(exception -> exception.accessDeniedPage("/denial"))
                    .sessionManagement(session -> session.maximumSessions(4).maxSessionsPreventsLogin(true));
            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
