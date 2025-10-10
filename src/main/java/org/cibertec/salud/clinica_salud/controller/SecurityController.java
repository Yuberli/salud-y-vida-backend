package org.cibertec.salud.clinica_salud.controller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.RegistroUsuarioDto;
import org.cibertec.salud.clinica_salud.service.RolService;
import org.cibertec.salud.clinica_salud.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    @GetMapping("/user")
    public String index (Model model) {
        model.addAttribute("userList",
                usuarioService.obtenerUsuarios());
        return "security/index";
    }

    @GetMapping("/user/create")
    public String create(Model model) {
        model.addAttribute("user", new RegistroUsuarioDto());
        model.addAttribute("rolList", rolService.obtenerRoles());
        return "security/user-create";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(Model model, @PathVariable int id, RedirectAttributes redirectAttrs) {
        RegistroUsuarioDto usuarioDto = usuarioService.obtenerRegistroUsuarioXIdUsuario(id);

        if (usuarioDto == null) {
            redirectAttrs.addFlashAttribute("error", "Usuario no encontrado.");
            return "redirect:/security/user";
        }

        model.addAttribute("user", usuarioDto);
        model.addAttribute("rolList", rolService.obtenerRoles());
        return "security/user-edit";
    }

    @PostMapping("/user/register")
    public String create(@ModelAttribute("user") RegistroUsuarioDto user, RedirectAttributes redirectAttrs){
        usuarioService.registrarUsuario(user);
        redirectAttrs.addFlashAttribute("success", "Usuario actualizado correctamente.");
        return "redirect:/security/user";
    }

}
