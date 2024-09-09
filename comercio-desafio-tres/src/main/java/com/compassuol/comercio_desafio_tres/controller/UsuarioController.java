package com.compassuol.comercio_desafio_tres.controller;
import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.dto.UsuarioDTO;
import com.compassuol.comercio_desafio_tres.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
   @Autowired
   private UsuarioService usuarioService;
   @GetMapping
   public List<UsuarioDTO> getAllUsuarios() {
       return usuarioService.getAllUsuarios();
   }
   @GetMapping("/{id}")
   public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
       UsuarioDTO usuarioDto = usuarioService.getUsuarioById(id);
       return ResponseEntity.ok(usuarioDto);
   }
   @PostMapping
   public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
       UsuarioDTO usuarioCriado = usuarioService.createUsuario(usuarioDTO);
       UsuarioDTO usuarioCriadoDTO = new UsuarioDTO(
           usuarioCriado.getId(),
           usuarioCriado.getNome(),
           usuarioCriado.getEmail(),
           usuarioCriado.getPermissao()
       );
       return ResponseEntity.ok(usuarioCriadoDTO);
   }
   @PutMapping("/{id}")
   public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDetails) {
       UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
       return ResponseEntity.ok(updatedUsuario);
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
       usuarioService.deleteUsuario(id);
       return ResponseEntity.noContent().build();
   }
  
   @PutMapping("/reset-senha")
   public ResponseEntity<Void> resetSenha(@RequestParam String email, @RequestParam String novaSenha) {
       usuarioService.resetarSenha(email, novaSenha);
       return ResponseEntity.ok().build();
   }
}
