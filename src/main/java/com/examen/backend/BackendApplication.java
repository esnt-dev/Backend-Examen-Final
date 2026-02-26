package com.examen.backend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class BackendApplication {

    private static final Logger LOG = LogManager.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        LOG.info("API Rest Inicializada");
    }

    @Bean
    public CommandLineRunner checkConnectionDB(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                LOG.info("Verificando conexión a la base de datos...");
                jdbcTemplate.execute("SELECT 1");
                LOG.info("Conexión a la base de datos exitosa (SELECT 1 ejecutado correctamente).");
            } catch (Exception e) {
                LOG.error("Error al conectar con la base de datos: {}", e.getMessage());
            }
        };
    }
}
