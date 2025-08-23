package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import models.Usuario;
import services.LoginService;
import services.LoginServiceImpl;
// Servlet encargado del inicio de sesión.
// Valida las credenciales del usuario y según su rol lo redirige a la vista correspondiente.
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtienen las credenciales enviadas desde el formulario.
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        // Se valida el usuario en el sistema mediante el servicio de login.
        Usuario u = loginService.login(email, password);

        if (u != null) {
            // Si las credenciales son correctas, se guarda información en la sesión
            // y se redirige dependiendo del rol del usuario.
            HttpSession session = req.getSession();
            session.setAttribute("usuario", u.getNombre());      
            session.setAttribute("idUsuario", u.getIdUsuario()); 
            session.setAttribute("rol", u.getRol());             

           
          if ("admin".equals(u.getRol())) {
            resp.sendRedirect("adminCompras.jsp"); // Vista para administradores
          } else {
             
             resp.sendRedirect(req.getContextPath() + "/product");// Vista para usuarios normales
            }
        } else {
            // Si las credenciales son incorrectas, se regresa al login con un error.
            resp.sendRedirect("login.jsp?error=Credenciales incorrectas");
        }
    }
}
