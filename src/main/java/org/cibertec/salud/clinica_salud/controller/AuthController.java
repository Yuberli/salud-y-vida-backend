package org.cibertec.salud.clinica_salud.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.cibertec.salud.clinica_salud.dto.UsuarioSecurityDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")

public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UsuarioSecurityDto usuarioDto = (UsuarioSecurityDto)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("nomusuario", usuarioDto.getUsername());
        return "auth/home";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }

}
