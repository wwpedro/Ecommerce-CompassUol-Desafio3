package com.compassuol.comercio_desafio_tres.controller;
import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.dto.LoginDTO;
import com.compassuol.comercio_desafio_tres.repository.UsuarioRepository;
import com.compassuol.comercio_desafio_tres.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private JwtUtil jwtUtil;
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
       System.out.println("Recebido email: " + loginDTO.getEmail());
       Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginDTO.getEmail());
       if (usuarioOpt.isPresent()) {
           Usuario usuario = usuarioOpt.get();
           System.out.println("Usuário encontrado: " + usuario.getEmail());
           if (loginDTO.getSenha().equals(usuario.getSenha())) {
               System.out.println("A senha é " + usuario.getSenha());
               try {
                   
                   String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getPermissao());
                   boolean isAdmin = "ADMIN".equalsIgnoreCase(usuario.getPermissao());
                   AuthResponse authResponse = new AuthResponse(token, isAdmin);
                   return ResponseEntity.ok(authResponse);
               } catch (io.jsonwebtoken.io.DecodingException e) {
                   
                   System.out.println("Erro de decodificação do JWT: " + e.getMessage());
                   return ResponseEntity.status(500).body("Erro ao processar o token JWT. Verifique a configuração.");
               } catch (Exception e) {
                   
                   System.out.println("Erro ao gerar o token JWT: " + e.getMessage());
                   return ResponseEntity.status(500).body("Erro interno no servidor.");
               }
           } else {
               System.out.println("Senha incorreta para o email: " + loginDTO.getEmail());
               return ResponseEntity.status(401).body("Credenciais inválidas");
           }
       } else {
           System.out.println("Usuário não encontrado para o email: " + loginDTO.getEmail());
           return ResponseEntity.status(401).body("Credenciais inválidas");
       }
   }
}
class AuthResponse {
   private String token;
   private boolean isAdmin;
   public AuthResponse(String token, boolean isAdmin) {
       this.token = token;
       this.isAdmin = isAdmin;
   }
   
   public String getToken() {
       return token;
   }
   public void setToken(String token) {
       this.token = token;
   }
   public boolean isAdmin() {
       return isAdmin;
   }
   public void setAdmin(boolean admin) {
       isAdmin = admin;
   }
}
