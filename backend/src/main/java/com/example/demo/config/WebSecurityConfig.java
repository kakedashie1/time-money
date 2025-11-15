package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(
						auth -> auth
								.requestMatchers("/css/*").permitAll()
								.requestMatchers("/admin/**").hasRole("ADMIN")
								.anyRequest().authenticated())
				.formLogin(
						form -> form
								.loginPage("/login")
								.permitAll()
								.failureUrl("/login-error")
								.defaultSuccessUrl("/", true))
				.exceptionHandling(ex -> ex.accessDeniedPage("/access-error"));

		return http.build();
	}
}
