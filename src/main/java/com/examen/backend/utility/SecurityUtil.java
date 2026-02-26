package com.examen.backend.utility;
import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;

public class SecurityUtil {

    // Método para encriptar la contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(14));
    }

    // Método para verificar si la contraseña es correcta
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    // Método para generar un token de recuperación
    public static String generateToken(){
        return UUID.randomUUID().toString();
    }

}
