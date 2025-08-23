package services;

import java.util.List;
import models.Product;
/**
 * Interfaz que define las operaciones del servicio de productos.
 * 
 * Contiene los métodos básicos para gestionar los productos dentro del sistema:
 * obtener, agregar, actualizar, eliminar y cambiar su estado.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);  
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    boolean marcarAgotado(int id);
    boolean marcarDisponible(int id);
}
