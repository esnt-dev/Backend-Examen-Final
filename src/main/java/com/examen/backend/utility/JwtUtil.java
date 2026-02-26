package com.examen.backend.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

public final class JwtUtil {

    private static final Logger LOG = LogManager.getLogger(JwtUtil.class);

    private static Key SECRET_KEY;
    private static final long EXPIRATION_TIME_MS = 43200000; // 12 horas

    private JwtUtil() {}

    /**
     * Inicializa el JwtUtil con la clave secreta.
     * Este método debe ser llamado una vez al inicio de la aplicación.
     * @param secretString La clave secreta en formato Base64.
     */
    public static void init(String secretString) {
        if (secretString == null || secretString.trim().isEmpty()) {
            LOG.error("La clave secreta de JWT no puede ser nula o vacía. Revisa la propiedad 'jwt.secret'.");
            throw new IllegalArgumentException("La clave secreta de JWT no puede ser nula o vacía.");
        }
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretString);
            SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
            LOG.info("JwtUtil inicializado correctamente.");
        } catch (IllegalArgumentException e) {
            LOG.error("Error al decodificar la clave secreta Base64: {}", e.getMessage());
            throw new RuntimeException("Error al inicializar JwtUtil: Clave secreta inválida", e);
        }
    }

    private static void checkInitialized() {
        if (SECRET_KEY == null) {
            LOG.error("JwtUtil no ha sido inicializado. La clave secreta es nula.");
            throw new IllegalStateException("JwtUtil no ha sido inicializado. Asegúrate de que la configuración de JWT se carga correctamente.");
        }
    }

    /**
     * Genera un token JWT para un usuario usando su email.
     * @param email El email del usuario.
     * @return El token JWT generado como String.
     */
    public static String generateToken(String email) {
        checkInitialized();
        LOG.info("Generando token para el usuario: {}", email);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extrae el email (subject) del token JWT.
     * @param token El token JWT.
     * @return El email contenido en el token.
     */
    public static String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     * @param token El token JWT.
     * @return La fecha de expiración.
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Valida un token JWT.
     * Verifica que el token pertenezca al usuario y no haya expirado.
     * @param token El token JWT a validar.
     * @param email El email a comparar.
     * @return true si el token es válido, false en caso contrario.
     */
    public static boolean validateToken(String token, String email) {
        checkInitialized();
        try {
            final String emailFromToken = getEmailFromToken(token);
            return (email.equals(emailFromToken) && !isTokenExpired(token));
        } catch (Exception e) {
            LOG.warn("Intento de validación de token fallido: {}", e.getMessage());
            return false;
        }
    }

    private static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getAllClaimsFromToken(String token) {
        checkInitialized();
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            LOG.warn("El token ha expirado: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            LOG.error("Token JWT no soportado: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            LOG.error("Token JWT malformado: {}", e.getMessage());
            throw e;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            LOG.error("Firma del token inválida: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            LOG.error("Argumento del token inválido: {}", e.getMessage());
            throw e;
        }
    }
}
