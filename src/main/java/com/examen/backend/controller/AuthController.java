package com.examen.backend.controller;

import com.examen.backend.model.service.UsuarioSERV;
import com.examen.backend.dto.request.UsuarioReqDTO;
import com.examen.backend.dto.response.UsuarioResDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioSERV userService;

    /**
     * Endpoint para iniciar sesión
     * POST: http://localhost:8080/api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioReqDTO req) {
        try {
            UsuarioResDTO res = userService.login(req);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
