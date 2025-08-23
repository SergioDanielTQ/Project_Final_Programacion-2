package services;

import connections.MySQLConnection;
import models.Usuario;

import java.sql.*;
/**
 * Implementación del servicio de login.
 * 
 * Se conecta a la base de datos y valida si existen credenciales correctas
 * para permitir el acceso al sistema.
 */
public class LoginServiceImpl implements LoginService {

    // Consulta SQL para verificar usuario y contraseña
    private final String SQL_LOGIN = "SELECT id_usuario AS idUsuario, nombre, email, password, rol "
                                   + "FROM usuarios WHERE email = ? AND password = ?";

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = null;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LOGIN)) {
            // Se asignan los valores a la consulta
            stmt.setString(1, email);
            stmt.setString(2, password); 
            // Se ejecuta la consulta y se revisan los resultados
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si encuentra coincidencia, se construye el objeto Usuario
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol")); 
                }
            }

        } catch (SQLException ex) {
            // En caso de error en la conexión o consulta
            ex.printStackTrace();
        }
        // Devuelve el usuario encontrado o null
        return usuario;
    }
}
