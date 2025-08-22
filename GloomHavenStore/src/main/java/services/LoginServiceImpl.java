package services;

import connections.MySQLConnection;
import models.Usuario;

import java.sql.*;

public class LoginServiceImpl implements LoginService {

    
    private final String SQL_LOGIN = "SELECT id_usuario AS idUsuario, nombre, email, password, rol "
                                   + "FROM usuarios WHERE email = ? AND password = ?";

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = null;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LOGIN)) {

            stmt.setString(1, email);
            stmt.setString(2, password); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setRol(rs.getString("rol")); 
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }
}
