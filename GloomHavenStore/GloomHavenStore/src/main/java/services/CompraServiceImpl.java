package services;

import connections.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Compra;

// Implementa la interfaz CompraService, conectándose a MySQL
public class CompraServiceImpl implements CompraService {

    @Override
    public boolean registrarCompra(Compra compra) {
        // SQL para registrar la compra principal y sus detalles
        String sqlCompra = "INSERT INTO compras (id_usuario, estado) VALUES (?, 'pendiente')";
        String sqlDetalle = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, subtotal) VALUES (?,?,?,?)";

        try (Connection conn = MySQLConnection.getConnection()) {
             // Desactiva autocommit para controlar la transacción
            conn.setAutoCommit(false);

            // Inserta la compra y recupera el id generado
            PreparedStatement psCompra = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
            psCompra.setInt(1, compra.getIdUsuario());
            psCompra.executeUpdate();

            ResultSet rs = psCompra.getGeneratedKeys();
            int idCompra = 0;
            if (rs.next()) idCompra = rs.getInt(1);

            // Inserta cada detalle de la compra
            for (var d : compra.getDetalles()) {
                PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
                psDetalle.setInt(1, idCompra);
                psDetalle.setInt(2, d.getIdProducto());
                psDetalle.setInt(3, d.getCantidad());
                psDetalle.setDouble(4, d.getSubtotal());
                psDetalle.executeUpdate();
            }
            // Confirma la transacción
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Compra> listarComprasPendientes() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE estado = 'pendiente'";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             // Recorre los resultados y construye objetos Compra
            while (rs.next()) {
                Compra c = new Compra();
                c.setIdCompra(rs.getInt("id_compra"));
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setFecha(rs.getTimestamp("fecha"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean actualizarEstadoCompra(int idCompra, String estado) {
        String sql = "UPDATE compras SET estado = ? WHERE id_compra = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idCompra);
             // Retorna true si al menos una fila fue actualizada
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
