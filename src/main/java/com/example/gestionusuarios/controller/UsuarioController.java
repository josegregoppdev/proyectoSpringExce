package com.example.gestionusuarios.controller;

import com.example.gestionusuarios.model.Usuario;
import com.example.gestionusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("titulo", "Crear Usuario");
        return "usuarios/form";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("usuario") Usuario usuario,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Usuario");
            return "usuarios/form";
        }

        try {
            usuarioService.save(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario creado exitosamente");
            return "redirect:/usuarios";
        } catch (IllegalArgumentException e) {
            model.addAttribute("titulo", "Crear Usuario");
            model.addAttribute("error", e.getMessage());
            return "usuarios/form";
        }
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.findById(id));
        return "usuarios/detail";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Editar Usuario");
        return "usuarios/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("usuario") Usuario usuario,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Editar Usuario");
            return "usuarios/form";
        }

        try {
            usuarioService.update(id, usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
            return "redirect:/usuarios/" + id;
        } catch (IllegalArgumentException e) {
            model.addAttribute("titulo", "Editar Usuario");
            model.addAttribute("error", e.getMessage());
            return "usuarios/form";
        }
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        return "redirect:/usuarios";
    }

    @GetMapping("/error/{tipo}")
    public String provocarError(@PathVariable String tipo) {
        switch (tipo) {
            case "404":
                usuarioService.findById(999L);
                break;
            case "400":
                throw new IllegalArgumentException("Este es un error de prueba 400");
            case "500":
                throw new RuntimeException("Este es un error de prueba 500");
            default:
                throw new RuntimeException("Tipo de error no reconocido");
        }
        return "redirect:/usuarios";
    }
}
