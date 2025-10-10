package org.cibertec.salud.clinica_salud.service.impl;
import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.RegistroUsuarioDto;
import org.cibertec.salud.clinica_salud.entity.RolEntity;
import org.cibertec.salud.clinica_salud.entity.UsuarioEntity;
import org.cibertec.salud.clinica_salud.repository.RolRepository;
import org.cibertec.salud.clinica_salud.repository.UsuarioRepository;
import org.cibertec.salud.clinica_salud.service.EmailService;
import org.cibertec.salud.clinica_salud.service.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl  implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final EmailService emailService;

    @Override
    public List<UsuarioEntity> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity obtenerUsuarioXIdUsuario (Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public UsuarioEntity obtenerUsuarioXNomUsuario (String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public void registrarUsuario(RegistroUsuarioDto usuarioDto) {
        UsuarioEntity usuario;
        String password = "123456";

        if(usuarioDto.getIdusuario() != null) {
            usuario = obtenerUsuarioXIdUsuario(usuarioDto.getIdusuario());
            usuario.setActivo(usuarioDto.getActivo());
        }else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario = new UsuarioEntity();
            usuario.setNomusuario(usuarioDto.getNomusuario());
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setActivo(true);
        }

        usuario.setNombres(usuarioDto.getNombres());
        usuario.setApellidos(usuarioDto.getApellidos());
        usuario.setEmail(usuarioDto.getEmail());
        Set<RolEntity> roles = new HashSet<>();
        if(usuarioDto.getRolesSeleccionados() != null) {
            for (Integer id: usuarioDto.getRolesSeleccionados()) {
                rolRepository.findById(id).ifPresent(roles::add);
            }
        }

        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        if(usuarioDto.getIdusuario() == null){
            emailService.enviarEmail(usuarioDto.getEmail(),
                    "Bienvenido al sistema, sus datos : " +
                    usuarioDto.getNomusuario() + ", su password es: " +password);
        }
    }

    @Override
    public RegistroUsuarioDto obtenerRegistroUsuarioXIdUsuario (Integer idUsuario) {
        UsuarioEntity usuario = obtenerUsuarioXIdUsuario(idUsuario);
        if(usuario == null) {
            return null;
        }
            RegistroUsuarioDto registroUsuarioDto = new RegistroUsuarioDto();
            registroUsuarioDto.setIdusuario(usuario.getIdusuario());
            registroUsuarioDto.setNombres(usuario.getNombres());
            registroUsuarioDto.setApellidos(usuario.getApellidos());
            registroUsuarioDto.setEmail(usuario.getEmail());
            registroUsuarioDto.setActivo(usuario.getActivo());
            registroUsuarioDto.setNomusuario(usuario.getNomusuario());
            Set<Integer> rolesSeleccionados = usuario.getRoles()
                    .stream()
                    .map(RolEntity::getIdrol).collect(Collectors.toSet());
            registroUsuarioDto.setRolesSeleccionados(rolesSeleccionados);
            return registroUsuarioDto;
        }

    }

