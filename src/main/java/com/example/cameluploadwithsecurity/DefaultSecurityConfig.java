package com.example.cameluploadwithsecurity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
  @Bean
  @ConditionalOnMissingBean(UserDetailsService.class)
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    String generatedPassword = "{noop}pass";
    return new InMemoryUserDetailsManager(
        User
            .withUsername("user")
            .password(generatedPassword)
            .roles("USER")
            .build());
  }

  @Bean
  @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
  DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(
      ApplicationEventPublisher delegate) {
    return new DefaultAuthenticationEventPublisher(delegate);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults())
    ;
    return http.build();
  }
}
