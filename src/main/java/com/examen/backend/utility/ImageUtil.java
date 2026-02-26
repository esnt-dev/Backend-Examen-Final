package com.examen.backend.utility;
import java.util.Base64;

public class ImageUtil {

    /**
     * @param img Arreglo de bytes de la imagen.
     * @return Cadena codificada en Base64 o null si el arreglo es vacío/nulo.
     */
    public static String encodeToBase64(byte[] img) {
        if (img == null || img.length == 0) return null;
        return Base64.getEncoder().encodeToString(img);
    }

    /**
     * @param base64Str Cadena en formato Base64.
     * @return Arreglo de bytes decodificado o null si la cadena es vacía/nula.
     */
    public static byte[] decodeFromBase64(String base64Str) {
        if (base64Str == null || base64Str.isEmpty()) return null;
        if (base64Str.contains(",")) {
            base64Str = base64Str.substring(base64Str.indexOf(",") + 1);
        }
        return Base64.getDecoder().decode(base64Str);
    }
}
