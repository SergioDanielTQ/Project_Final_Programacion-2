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
// Servlet encargado de gestionar los productos.
// Permite listar, agregar, editar, eliminar y cambiar el estado (disponible/agotado)
@WebServlet({"/product", "/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        System.out.println("[ProductServlet][GET] accion=" + accion);
        
        // Si no se pasa ninguna acción -> se muestran todos los productos
        if (accion == null) {
            List<Product> productList = service.getAllProducts();
            request.setAttribute("productList", productList);
            getServletContext().getRequestDispatcher("/ProductList.jsp").forward(request, response);
        
        // Editar producto
        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product producto = service.getProductById(id); 
            request.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/formProducto.jsp").forward(request, response);
            
        // Eliminar producto
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean ok = service.deleteProduct(id);
            System.out.println("[ProductServlet] eliminar id=" + id + " ok=" + ok);
            response.sendRedirect(request.getContextPath() + "/product");
        // Marcar producto como agotado
        } else if ("agotado".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean ok = service.marcarAgotado(id);
            System.out.println("[ProductServlet] marcarAgotado id=" + id + " ok=" + ok);
            response.sendRedirect(request.getContextPath() + "/product");
        // Marcar producto como disponible
        } else if ("disponible".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean ok = service.marcarDisponible(id);
            System.out.println("[ProductServlet] marcarDisponible id=" + id + " ok=" + ok);
            response.sendRedirect(request.getContextPath() + "/product");
        // Si la acción no es reconocida -> volver al listado
        } else {
            response.sendRedirect(request.getContextPath() + "/product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        System.out.println("[ProductServlet][POST] accion=" + accion);
        // Actualizar producto existente
        if ("update".equals(accion)) {
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
            System.out.println("[ProductServlet] update id=" + id + " ok=" + actualizado);
            response.sendRedirect(request.getContextPath() + "/product");
         // Agregar nuevo producto
        } else if ("add".equals(accion)) {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));

            Product p = new Product();
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);

            boolean ok = service.addProduct(p);
            System.out.println("[ProductServlet] add ok=" + ok);
            response.sendRedirect(request.getContextPath() + "/product");
        // Acción no válida -> regresar al listado
        } else {
            response.sendRedirect(request.getContextPath() + "/product");
        }
    }
}
