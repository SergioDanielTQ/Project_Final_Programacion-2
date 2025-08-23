package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import models.Compra;
import models.DetalleCompra;
import services.CompraService;
import services.CompraServiceImpl;
import java.util.List;

// Servlet encargado de procesar una compra.
// Se activa cuando el usuario confirma el carrito y finaliza la compra.
@WebServlet("/CompraServlet")
public class CompraServlet extends HttpServlet {
    private CompraService compraService = new CompraServiceImpl();

    @Override
    // Método que recibe la petición POST al confirmar la compra.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. Se obtiene el ID del usuario desde el formulario.
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        
        // 2. Se recupera el carrito (lista de detalles de compra) almacenado en la sesión.
        List<DetalleCompra> detalles = (List<DetalleCompra>) req.getSession().getAttribute("carrito");

        // 3. Se crea un objeto "Compra" que agrupa la información de la compra:
        //    - Usuario que compra
        //    - Detalles de los productos
        Compra compra = new Compra();
        compra.setIdUsuario(idUsuario);
        compra.setDetalles(detalles);

        // 4. Se registra la compra a través del servicio (se guarda en la base de datos).
        boolean ok = compraService.registrarCompra(compra);

        // 5. Si la compra se registró correctamente:
        //    - Se elimina el carrito de la sesión
        //    - Se redirige al listado de productos con un mensaje de éxito
        if (ok) {
            req.getSession().removeAttribute("carrito");
            resp.sendRedirect("ProductList.jsp?msg=Compra registrada, pendiente de aprobación");
        } 
        // 6. Si hubo un error, se redirige al carrito con un mensaje de error.
        else {
            resp.sendRedirect("carrito.jsp?error=No se pudo registrar la compra");
        }
    }
}
