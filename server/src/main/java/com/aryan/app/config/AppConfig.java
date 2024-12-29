package com.aryan.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}

// iska matlab hai ki agar hamara api ka endpoint /api/ se start hota hai tohame
// bo authenticated request hai and usko password se secure karna hai yani login
// logout banana hai usko
// Authorize.requestMatchers("/api/**").authenticated()

// iska matlab baaki ki api ko permit kar do bina password ke allow kardo. isko
// koi bhi access kar sakta hai
// .anyRequest().permitAll()
