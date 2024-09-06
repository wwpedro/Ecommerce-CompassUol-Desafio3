package com.compassuol.comercio_desafio_tres.service;

import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.exception.ResourceNotFoundException;
import com.compassuol.comercio_desafio_tres.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = getUsuarioById(id);
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setPermissao(usuarioDetails.getPermissao());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = getUsuarioById(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
    }
}
