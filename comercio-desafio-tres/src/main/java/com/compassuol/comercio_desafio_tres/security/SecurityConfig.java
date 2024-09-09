package com.compassuol.comercio_desafio_tres.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  
   private final JwtRequestFilter jwtRequestFilter;
  
   public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
       this.jwtRequestFilter = jwtRequestFilter;
   }
  
   @Bean(name = "customSecurityFilterChain")
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
           .csrf().disable() 
           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
           .and()
           .authorizeHttpRequests()
               .requestMatchers("/api/auth/login", "/api/usuarios/**", "/api/produtos").permitAll() 
               .requestMatchers("/api/produtos/**", "/api/itens-venda/**", "/api/vendas/**").hasAnyRole("ADMIN", "USER") 
               .requestMatchers("/api/**").hasRole("ADMIN") 
               .anyRequest().authenticated()
           .and()
           .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
           .httpBasic(); 
      
       return http.build();
   }
  
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
}
