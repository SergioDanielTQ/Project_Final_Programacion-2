package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import services.CompraService;
import services.CompraServiceImpl;



@WebServlet("/AdminCompraServlet")
public class AdminCompraServlet extends HttpServlet {
    private CompraService compraService = new CompraServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCompra = Integer.parseInt(req.getParameter("idCompra"));
        String accion = req.getParameter("accion"); 

        String nuevoEstado = accion.equals("confirmar") ? "confirmada" : "rechazada";
        compraService.actualizarEstadoCompra(idCompra, nuevoEstado);

        resp.sendRedirect("adminCompras.jsp");
    }
}
