package com.examen.backend.model.repository;

import com.examen.backend.model.entity.Usuario;


public interface UsuarioREP {

    boolean saveUser(Usuario usuarioENT);
    Usuario findUserByEmail(String email);
    boolean existsUserByEmail(String email);

}
