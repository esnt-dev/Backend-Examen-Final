package com.examen.backend.model.repository;

import org.springframework.stereotype.Repository;
import com.examen.backend.model.entity.Usuario;


@Repository
public interface UsuarioREP {

    boolean saveUser(Usuario usuarioENT);
    Usuario findUserByEmail(String email);
    boolean existsUserByEmail(String email);

}
