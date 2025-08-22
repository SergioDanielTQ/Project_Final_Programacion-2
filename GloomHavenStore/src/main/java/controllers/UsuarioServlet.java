package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;
import services.UsuarioService;
import services.UsuarioServiceImpl;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "form":
                    showForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    listUsers(req, resp);
                    break;
            }
        } else {
            listUsers(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("create".equals(action)) {
            saveUser(req, resp);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.listar();
        req.setAttribute("usuariosList", usuarios); 
        getServletContext().getRequestDispatcher("/usuarios.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Optional<Usuario> usuario = usuarioService.porId(id);
            usuario.ifPresent(value -> req.setAttribute("usuario", value));
        }
        getServletContext().getRequestDispatcher("/formUsuario.jsp").forward(req, resp);
    }

    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String rol = req.getParameter("rol"); 

        Usuario usuario = new Usuario();
        if (idParam != null && !idParam.isEmpty()) {
            usuario.setIdUsuario(Integer.parseInt(idParam));
        }
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(pass);
        usuario.setRol(rol); 

        usuarioService.guardar(usuario);
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        usuarioService.eliminar(id);
        resp.sendRedirect(req.getContextPath() + "/usuarios");
    }
}
