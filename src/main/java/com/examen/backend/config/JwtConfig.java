package com.examen.backend.config;

import com.examen.backend.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Configuration
public class JwtConfig {

    private static final Logger LOG = LogManager.getLogger(JwtConfig.class);

    @Value("${JWT_SECRET}")
    private String secretKey;

    @PostConstruct
    public void initJwtUtil() {
        LOG.info("Inicializando JWTUtil");

        try {
            JwtUtil.init(secretKey);
            LOG.info("JWTUtil Inicializado");
        } catch(Exception e) {
            LOG.error("No se puede inicializar JWTUtil");
            throw new RuntimeException(e);
        }

    }

}