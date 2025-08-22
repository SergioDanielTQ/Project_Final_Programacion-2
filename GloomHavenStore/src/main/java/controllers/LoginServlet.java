package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import models.Usuario;
import services.LoginService;
import services.LoginServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Usuario u = loginService.login(email, password);

        if (u != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario", u.getNombre());      
            session.setAttribute("idUsuario", u.getIdUsuario()); 
            session.setAttribute("rol", u.getRol());             

           
          if ("admin".equals(u.getRol())) {
            resp.sendRedirect("adminCompras.jsp");
          } else {
             
             resp.sendRedirect(req.getContextPath() + "/product");
            }
        } else {
            resp.sendRedirect("login.jsp?error=Credenciales incorrectas");
        }
    }
}
