package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
// Servlet encargado del cierre de sesión.
// Su función es eliminar la sesión activa y regresar al usuario a la página de inicio.
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtiene la sesión actual (si existe) y se invalida para cerrar la sesión del usuario.
        HttpSession session = req.getSession(false); 
        if (session != null) {
            session.invalidate(); 
        }

        // Una vez cerrada la sesión, se redirige al inicio.
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}

