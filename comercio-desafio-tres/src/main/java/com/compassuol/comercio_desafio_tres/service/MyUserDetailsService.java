package com.compassuol.comercio_desafio_tres.service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.repository.UsuarioRepository;
import java.util.*;
@Service
public class MyUserDetailsService implements UserDetailsService {
   private final UsuarioRepository usuarioRepository;
  
   public MyUserDetailsService(UsuarioRepository usuarioRepository) {
       this.usuarioRepository = usuarioRepository;
   }
  
   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
       if (usuarioOpt.isPresent()) {
           Usuario usuario = usuarioOpt.get();
          
           List<SimpleGrantedAuthority> authorities = Arrays.asList(
               new SimpleGrantedAuthority("ROLE_" + usuario.getPermissao())
           );
          
           return new org.springframework.security.core.userdetails.User(
               usuario.getEmail(),
               usuario.getSenha(),
               authorities
           );
       } else {
           throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
       }
   }
}