package com.examen.backend.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConexionDB {
    private static final Logger LOG = LogManager.getLogger(ConexionDB.class);

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String user;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.driver-class-name}")
    private static String driver;

    public static Connection getConnection() throws SQLException {
        LOG.info("Iniciando conexión a la base de datos: {}", url);
        try {
            Class.forName(driver);
            LOG.info("Conexión a la base de datos exitosa");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOG.error("Error al conectarse a la base de datos: {}", e.getMessage());
            throw new SQLException("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            LOG.error("Driver no encontrado: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection conn) throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOG.info("Conexión a la base de datos cerrada correctamente");
            }
        } catch (SQLException e) {
            LOG.error("Error al intentar cerrar la conexión a la base de datos");
            throw new SQLException("Error" + e.getMessage());
        } finally {
            conn = null;
        }
    }

}
