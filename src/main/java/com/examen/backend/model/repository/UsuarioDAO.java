package com.examen.backend.model.repository;

import com.examen.backend.config.ConexionDB;
import com.examen.backend.model.entity.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO implements UsuarioREP {
    private static final Logger LOG = LogManager.getLogger(UsuarioDAO.class);

    public static final String SQL_INSERT_USER =
            "insert into usuarios (usurname, usurlast, usurmail, usurpassword, usurimg, usurtoken) values (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean saveUser(Usuario user) {
        LOG.info("Inicianado proceso para guardar un usuario");
        PreparedStatement ps = null;
        try (Connection conn = ConexionDB.getConnection()) {

            ps = conn.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getUsurName());
            ps.setString(2, user.getUsurLast());
            ps.setString(3, user.getUsurMail());
            ps.setString(4, user.getUsurPassword());
            ps.setBytes(5, user.getUsurImg());
            ps.setString(6, user.getUsurToken());
            ps.executeUpdate();

            LOG.info("Usuario insertado exitosamente");
            return true;

        } catch (SQLException e) {
            LOG.error("Error al insertar el usuario", e);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOG.error("Error al cerrar el PreparedStatement", e);
                }
            }
        }
    }

    private static final String SQL_SELECT_USER_WHERE =
            "select * from usuarios where usurmail = ? and usuisdeleted = false";

    @Override
    public Usuario findUserByEmail (String email){
        LOG.info("Inicianado busqueda de usuario con email: {}", email);

        Usuario userDB = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = ConexionDB.getConnection()){

            ps = conn.prepareStatement(SQL_SELECT_USER_WHERE);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if  (rs.next()) {
                userDB = new Usuario();
                userDB.setUsurId(rs.getLong("usurid"));
                userDB.setUsurName(rs.getString("usurname"));
                userDB.setUsurLast(rs.getString("usurlast"));
                userDB.setUsurMail(rs.getString("usurmail"));
                userDB.setUsurPassword(rs.getString("usurpassword"));
                userDB.setUsurImg(rs.getBytes("usurimg"));
                userDB.setUsurToken(rs.getString("usurtoken"));
                userDB.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                LOG.info("Usuario encontrado y mapeado exitosamente.");
            } else {
                LOG.warn("Usuario con mail {} no encontrado", email);
            }

        } catch (SQLException e) {
            LOG.error("Error al obtener el usuario", e);
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { LOG.error("Error al cerrar rs", e); }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { LOG.error("Error al cerrar ps", e); }
            }
        }
        return userDB;
    }

    private static final String SQL_SELECT_USER_WHERE_EXITS =
            "select * from usuarios where usurmail = ? and usuisdeleted = false";

     @Override
    public boolean existsUserByEmail (String email){
         LOG.info("Inicianado busqueda de usuario con email: {}", email);

         boolean exitUserDB = false;
         PreparedStatement ps = null;
         ResultSet rs = null;

         try (Connection conn = ConexionDB.getConnection()) {

             ps = conn.prepareStatement(SQL_SELECT_USER_WHERE_EXITS);
             ps.setString(1, email);
             rs = ps.executeQuery();

             if (rs.next()) {
                 return true;
             }

         } catch (SQLException e) {
             LOG.error("Error al obtener el usuario", e);
             throw new RuntimeException(e);
         } finally {
             if (rs != null) {
                 try { rs.close(); } catch (SQLException e) { LOG.error("Error al cerrar rs", e); }
             }
             if (ps != null) {
                 try { ps.close(); } catch (SQLException e) { LOG.error("Error al cerrar ps", e); }
             }
         }
         return exitUserDB;
     }

}
