package com.compassuol.comercio_desafio_tres.service;
import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.dto.UsuarioDTO;
import com.compassuol.comercio_desafio_tres.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsuarioService {
	@Autowired
   private UsuarioRepository usuarioRepository;
   public List<UsuarioDTO> getAllUsuarios() {
       return usuarioRepository.findAll().stream()
           .map(this::convertToDTO)
           .collect(Collectors.toList());
   }
   public UsuarioDTO getUsuarioById(Long id) {
       Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
       return convertToDTO(usuario);
   }
   public UsuarioDTO createUsuario(UsuarioDTO usuarioDto) {
       Usuario usuario = convertToEntity(usuarioDto);
       Usuario novoUsuario = usuarioRepository.save(usuario);
       return convertToDTO(novoUsuario);
   }
   public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDto) {
       Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
       usuario.setNome(usuarioDto.getNome());
       usuario.setEmail(usuarioDto.getEmail());
       usuario.setPermissao(usuarioDto.getPermissao());
       usuario = usuarioRepository.save(usuario);
       return convertToDTO(usuario);
   }
   public void deleteUsuario(Long id) {
       usuarioRepository.deleteById(id);
   }
   private UsuarioDTO convertToDTO(Usuario usuario) {
       return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPermissao());
   }
   private Usuario convertToEntity(UsuarioDTO usuarioDto) {
       return new Usuario(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getSenha(), usuarioDto.getPermissao());
   }
  
   public void resetarSenha(String email, String novaSenha) {
       Usuario usuario = usuarioRepository.findByEmail(email)
           .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o e-mail: " + email));
       usuario.setSenha(novaSenha);
       usuarioRepository.save(usuario);
   }
}
