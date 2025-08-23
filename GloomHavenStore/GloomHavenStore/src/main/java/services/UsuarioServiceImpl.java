package services;

import connections.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.Usuario;

// implementación del servicio de usuarios (crud)
public class UsuarioServiceImpl implements UsuarioService {
    // método para listar todos los usuarios
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario AS idUsuario, nombre, email, password, rol FROM usuarios";
        // se abre la conexión y se ejecuta la consulta
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             // se recorren los resultados y se crean objetos usuario
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    // método para buscar un usuario por su id
    @Override
    public Optional<Usuario> porId(int id) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario AS idUsuario, nombre, email, password, rol FROM usuarios WHERE id_usuario = ?";
         // se usa preparedstatement para buscar un usuario por id
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(usuario);
    }
     // método para guardar (insertar o actualizar) un usuario
    @Override
    public void guardar(Usuario usuario) {
        String sql;
        boolean actualizar = usuario.getIdUsuario() > 0;
         // si el usuario ya existe se hace update, sino se inserta
        if (actualizar) {
            sql = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, rol = ? WHERE id_usuario = ?";
        } else {
            sql = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";
        }

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());
            stmt.setString(4, usuario.getRol());

            if (actualizar) {
                stmt.setInt(5, usuario.getIdUsuario());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // método para eliminar un usuario por su id
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
         // se ejecuta un delete en base al id recibido
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
}
