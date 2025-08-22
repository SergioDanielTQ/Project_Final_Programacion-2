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

@WebServlet("/CompraServlet")
public class CompraServlet extends HttpServlet {
    private CompraService compraService = new CompraServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        
        List<DetalleCompra> detalles = (List<DetalleCompra>) req.getSession().getAttribute("carrito");

        Compra compra = new Compra();
        compra.setIdUsuario(idUsuario);
        compra.setDetalles(detalles);

        boolean ok = compraService.registrarCompra(compra);
        if (ok) {
            req.getSession().removeAttribute("carrito");
            resp.sendRedirect("ProductList.jsp?msg=Compra registrada, pendiente de aprobación");
        } else {
            resp.sendRedirect("carrito.jsp?error=No se pudo registrar la compra");
        }
    }
}
