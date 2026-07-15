package com.example.gestionusuarios.service;

import com.example.gestionusuarios.exception.ResourceNotFoundException;
import com.example.gestionusuarios.model.Usuario;
import com.example.gestionusuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    public Usuario save(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioDetails) {
        Usuario usuario = findById(id);

        if (!usuario.getEmail().equals(usuarioDetails.getEmail())
                && usuarioRepository.existsByEmail(usuarioDetails.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuarioDetails.getEmail());
        }

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setActivo(usuarioDetails.isActivo());

        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isBlank()) {
            usuario.setPassword(usuarioDetails.getPassword());
        }

        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }
}
