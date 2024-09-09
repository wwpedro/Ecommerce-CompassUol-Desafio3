package com.compassuol.comercio_desafio_tres.security;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.compassuol.comercio_desafio_tres.service.MyUserDetailsService;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
   @Autowired
   private JwtUtil jwtUtil;
  
   @Autowired
   private MyUserDetailsService userDetailsService;
  
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                   FilterChain chain)
   throws ServletException, IOException {
      
       final String requestTokenHeader = request.getHeader("Authorization");
       String username = null;
       String jwtToken = null;
      
       
       if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
           jwtToken = requestTokenHeader.substring(7);
           try {
               username = jwtUtil.extractUsername(jwtToken);
           } catch (ExpiredJwtException e) {
               logger.warn("Token JWT expirado");
           } catch (Exception e) {
               logger.warn("Erro ao extrair token JWT");
           }
       } else {
           logger.warn("JWT Token não encontrado ou não começa com 'Bearer '");
       }
      
       
       if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
          
           if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                   new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities()
                   );
               usernamePasswordAuthenticationToken
                   .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              
               
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
       }
      
       chain.doFilter(request, response);
   }
}
