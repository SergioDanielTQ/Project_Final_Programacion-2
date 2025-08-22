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

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));

        Product p = productService.getProductById(idProducto);

        HttpSession session = req.getSession();
        List<DetalleCompra> carrito = (List<DetalleCompra>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        
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

        session.setAttribute("carrito", carrito);

        resp.sendRedirect("ProductList.jsp?msg=Producto agregado al carrito");
    }
}
