package com.examen.backend.utility;
import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    /**
     * @param input Cadena de entrada.
     * @return Cadena limpia o null si la entrada es nula.
     */
    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder sanitized = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<':
                    sanitized.append("&lt;");
                    break;
                case '>':
                    sanitized.append("&gt;");
                    break;
                case '&':
                    sanitized.append("&amp;");
                    break;
                case '"':
                    sanitized.append("&quot;");
                    break;
                case '\'':
                    sanitized.append("&#x27;");
                    break;
                default:
                    sanitized.append(c);
            }
        }
        return sanitized.toString();
    }

    /**
     * @param email Cadena a validar.
     * @return true si es un correo válido, false en caso contrario.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * @param email Correo electrónico.
     * @return Correo limpio o null si la entrada es nula.
     */
    public static String cleanEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase();
    }
}
