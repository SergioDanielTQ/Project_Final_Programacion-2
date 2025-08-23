package services;

import connections.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Product;
// Implementación del servicio de productos
// Se encarga de realizar las operaciones CRUD contra la base de datos
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() {
        // Lista donde se guardarán los productos obtenidos
        List<Product> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
         // Uso de try-with-resources para abrir la conexión y ejecutar la consulta
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             // Se recorren los resultados y se crean objetos Producto
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion")); 
                p.setPrecio(rs.getDouble("precio"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        Product p = null;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             // Se reemplaza el parámetro con el id recibido
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si existe un producto con ese id, se crea el objeto
                    p = new Product();
                    p.setId(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;// Devuelve el producto o null si no existe
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio) VALUES (?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             // Se asignan los valores del objeto producto
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getDescripcion());
            ps.setDouble(3, product.getPrecio());
             // Se ejecuta la inserción y se devuelve true si se insertó al menos 1 fila
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;// Devuelve false si ocurre un error
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=? WHERE id_producto=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             // Se actualizan los valores en base al id del product
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getDescripcion());
            ps.setDouble(3, product.getPrecio());
            ps.setInt(4, product.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM productos WHERE id_producto=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Se elimina el producto según el id
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
public boolean marcarAgotado(int id) {
    String sql = "UPDATE productos SET estado = 'agotado' WHERE id_producto = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        // Se marca el producto como "agotado"
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

@Override
public boolean marcarDisponible(int id) {
    String sql = "UPDATE productos SET estado = 'disponible' WHERE id_producto = ?";
    try (Connection conn = MySQLConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        // Se marca el producto como "disponible"
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

}

