package org.cibertec.salud.clinica_salud.service;
import org.cibertec.salud.clinica_salud.dto.RegistroUsuarioDto;
import org.cibertec.salud.clinica_salud.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService  {

        List<UsuarioEntity> obtenerUsuarios();
        UsuarioEntity obtenerUsuarioXIdUsuario (Integer idusuario);
        UsuarioEntity obtenerUsuarioXNomUsuario (String nomusuario);
        void registrarUsuario (RegistroUsuarioDto usuarioDto);
        RegistroUsuarioDto obtenerRegistroUsuarioXIdUsuario(Integer idusuario);
}
