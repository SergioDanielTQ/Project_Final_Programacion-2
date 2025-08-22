package controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import services.ProductService;
import services.ProductServiceImpl;

@WebServlet({"/product", "/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            // Mostrar lista de productos
            List<Product> productList = service.getAllProducts();
            request.setAttribute("productList", productList);
            getServletContext().getRequestDispatcher("/ProductList.jsp").forward(request, response);

        } else if ("editar".equals(accion)) {
            // Mostrar formulario de edición
            int id = Integer.parseInt(request.getParameter("id"));
            Product producto = service.getProductById(id);
            request.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/formProducto.jsp").forward(request, response);

        } else if ("eliminar".equals(accion)) {
            // Eliminar producto
            int id = Integer.parseInt(request.getParameter("id"));
            service.deleteProduct(id);
            response.sendRedirect("ProductServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("update".equals(accion)) {
            // Editar producto
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));

            Product p = new Product();
            p.setId(id);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);

            boolean actualizado = service.updateProduct(p);

            if (actualizado) {
                response.sendRedirect("ProductServlet");
            } else {
                response.sendRedirect("formProducto.jsp?id=" + id + "&error=true");
            }
        } else if ("add".equals(accion)) {
            // Agregar producto
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));

            Product p = new Product();
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);

            service.addProduct(p);
            response.sendRedirect("ProductServlet");
        }
    }
}
