package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import services.CompraService;
import services.CompraServiceImpl;


// Servlet que maneja las acciones de administración de compras.
// Su función principal es cambiar el estado de una compra (confirmar o rechazar).
@WebServlet("/AdminCompraServlet")
public class AdminCompraServlet extends HttpServlet {
    // Se utiliza un servicio para manejar la lógica de negocio relacionada con las compras
    private CompraService compraService = new CompraServiceImpl();

    @Override
    // Método que responde a peticiones POST (cuando se envía un formulario desde la vista).
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // 1. Se obtienen los parámetros enviados desde el formulario (ID de la compra y acción).
        int idCompra = Integer.parseInt(req.getParameter("idCompra"));
        String accion = req.getParameter("accion"); 
        // 2. Según la acción recibida, se define el nuevo estado de la compra.
        //    - "confirmar" -> estado pasa a "confirmada"
        //    - cualquier otro valor -> estado pasa a "rechazada"
        String nuevoEstado = accion.equals("confirmar") ? "confirmada" : "rechazada";
        // 3. Se actualiza el estado en la base de datos a través del servicio.
        compraService.actualizarEstadoCompra(idCompra, nuevoEstado);
        // 4. Una vez procesado, se redirige al administrador de compras (vista JSP).
        resp.sendRedirect("adminCompras.jsp");
    }
}
