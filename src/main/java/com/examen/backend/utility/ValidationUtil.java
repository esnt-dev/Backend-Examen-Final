package com.examen.backend.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern CEDULA_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{7,10}$");

    /**
     * Valida si una cadena corresponde a una cédula válida (formato básico).
     * @param cedula Número de cédula.
     * @return true si es válida, false en caso contrario.
     */
    public static boolean isValidCedula(String cedula) {
        if (cedula == null) return false;
        return CEDULA_PATTERN.matcher(cedula).matches();
    }

    /**
     * Valida si una cadena corresponde a un número de teléfono válido.
     * @param phone Número de teléfono.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Valida si una cadena corresponde a una fecha válida en formato ISO (YYYY-MM-DD).
     * @param dateStr Cadena de fecha.
     * @return true si es válida, false en caso contrario.
     */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null) return false;
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Valida si un número es positivo (mayor a cero).
     * Útil para validar límites de créditos, IDs, etc.
     * @param number Número a validar.
     * @return true si es positivo, false en caso contrario.
     */
    public static boolean isPositive(Integer number) {
        return number != null && number > 0;
    }
}
