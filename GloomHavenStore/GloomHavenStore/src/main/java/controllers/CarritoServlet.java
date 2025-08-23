package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.DetalleCompra;
import services.ProductService;
import services.ProductServiceImpl;
import models.Product;
// Servlet encargado de manejar el carrito de compras.
// Su función es agregar productos seleccionados por el usuario al carrito 
// que se guarda en la sesión.
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    // Método que recibe las peticiones POST (cuando se agrega un producto al carrito).
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Se obtienen los parámetros del formulario: ID del producto y cantidad.
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        // 2. Se busca el producto en la base de datos a través del servicio.
        Product p = productService.getProductById(idProducto);
        // 3. Se obtiene el carrito desde la sesión. 
        //    Si no existe todavía, se crea uno nuevo (lista vacía).
        HttpSession session = req.getSession();
        List<DetalleCompra> carrito = (List<DetalleCompra>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        // 4. Se verifica si el producto ya está en el carrito:
        //    - Si está, se actualiza la cantidad y el subtotal.
        //    - Si no, se crea un nuevo detalle y se agrega al carrito.     
        boolean existe = false;
        for (DetalleCompra d : carrito) {
            if (d.getIdProducto() == idProducto) {
                d.setCantidad(d.getCantidad() + cantidad);
                d.setSubtotal(d.getCantidad() * p.getPrecio());
                existe = true;
                break;
            }
        }

       
        if (!existe) {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdProducto(idProducto);
            detalle.setCantidad(cantidad);
            detalle.setSubtotal(cantidad * p.getPrecio());
            carrito.add(detalle);
        }
        // 5. Se guarda nuevamente el carrito en la sesión.
        session.setAttribute("carrito", carrito);
         // 6. Finalmente se redirige al listado de productos con un mensaje de confirmación.
        resp.sendRedirect("ProductList.jsp?msg=Producto agregado al carrito");
    }
}
