package services;

import java.util.List;
import models.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);  
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
}
