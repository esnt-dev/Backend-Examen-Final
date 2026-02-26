package com.examen.backend.model.service;

import com.examen.backend.model.entity.Usuario;
import com.examen.backend.model.repository.UsuarioREP;
import com.examen.backend.dto.request.UsuarioReqDTO;
import com.examen.backend.dto.response.UsuarioResDTO;

import com.examen.backend.utility.JwtUtil;
import com.examen.backend.utility.SecurityUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class UsuarioSERV {

    private final UsuarioREP usuarioREP;
    private static final Logger LOG = LogManager.getLogger(UsuarioSERV.class);

    public UsuarioSERV(UsuarioREP usuarioREP) {
        this.usuarioREP = usuarioREP;
    }

    /**
     * Función para el login de usuarios
     * Devuelve un DTO de respuesta para no exponer la Entidad.
     */
    public UsuarioResDTO login(UsuarioReqDTO req) throws Exception {
        LOG.info("Intento de inicio de sesión para el email: {}", req.getMail());

        Usuario userDB = usuarioREP.findUserByEmail(req.getMail());

        if (userDB == null) {
            LOG.warn("No existe el usuario con email {}", req.getMail());
            throw new Exception("Credenciales incorrectas.");
        }

        boolean isValid = SecurityUtil.checkPassword(req.getPassword(), userDB.getUsurPassword());

        if (!isValid) {
            LOG.warn("La contraseña no es valida");
            throw new Exception("Credenciales incorrectas.");
        }

        LOG.info("Login exitoso");


        String tokenGenerado = JwtUtil.generateToken(userDB.getUsurMail());

        String base64Image = null;
        if (userDB.getUsurImg() != null) {
            base64Image = Base64.getEncoder().encodeToString(userDB.getUsurImg());
        }

        UsuarioResDTO res = new UsuarioResDTO();
        res.setId(userDB.getUsurId());
        res.setName(userDB.getUsurName());
        res.setMail(userDB.getUsurMail());
        res.setToken(tokenGenerado);
        res.setImgBase64(base64Image);

        return res;
    }
}