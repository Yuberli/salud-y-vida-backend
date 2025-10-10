package org.cibertec.salud.clinica_salud.service.impl;

import lombok.RequiredArgsConstructor;

import org.cibertec.salud.clinica_salud.dto.UsuarioSecurityDto;
import org.cibertec.salud.clinica_salud.entity.RolEntity;
import org.cibertec.salud.clinica_salud.entity.UsuarioEntity;
import org.cibertec.salud.clinica_salud.service.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UsuarioDetalleServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername (String username)
        throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioService.obtenerUsuarioXNomUsuario(username);
        return getUserDetails(usuario, getAuthorities(usuario.getRoles()));
    }

    private List<GrantedAuthority> getAuthorities(Set<RolEntity> roles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(RolEntity rol : roles) {
            grantedAuthorities.add(
                    new SimpleGrantedAuthority("ROLE_" + rol.getNomrol()));
        }
        return new ArrayList<>(grantedAuthorities);
    }

    public UsuarioSecurityDto getUserDetails(UsuarioEntity usuario, List<GrantedAuthority> grantedAuthorities) {

        UsuarioSecurityDto usuarioSecurityDto = new UsuarioSecurityDto(
                usuario.getNomusuario(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,true,true,
                grantedAuthorities);
        usuarioSecurityDto.setEmail(usuario.getEmail());
        return usuarioSecurityDto;
    }
}
