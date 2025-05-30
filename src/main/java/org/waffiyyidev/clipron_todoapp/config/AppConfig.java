package org.waffiyyidev.clipron_todoapp.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.waffiyyidev.clipron_todoapp.exception.AuthenticationExceptionHandler;
import org.waffiyyidev.clipron_todoapp.exception.SecurityException;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class AppConfig {
   private final JwtTokenValidator jwtTokenValidator;
   private final AuthenticationExceptionHandler authenticationExceptionHandler;
   private final SecurityException securityException;

   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
        .sessionManagement(
          management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(Authorize -> Authorize
          .requestMatchers("/api/v1/auth/**")
          .permitAll()
          //swagger
          .requestMatchers("/v3/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
                           "/swagger-resources/**", "/configuration/ui", "/configuration/security",
                           "/swagger-ui/**", "/webjars/**", "/swagger-ui.html")
          .permitAll()
          .anyRequest()
          .authenticated())
        .exceptionHandling(request -> {
           request.authenticationEntryPoint(authenticationExceptionHandler);
           request.accessDeniedHandler(securityException);
        })
        .addFilterBefore(jwtTokenValidator, BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()));
      return http.build();
   }

   private CorsConfigurationSource corsConfigurationSource() {
      return new CorsConfigurationSource() {
         @Override
         public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3000",
                                                "https://clipron-todo.vercel.app",
                                                "http://localhost:8065",
                                                "https://coastal-turkey-donezo-todo-app-69b1821d.koyeb.app"));
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Arrays.asList("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;
         }
      };
   }

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

}
