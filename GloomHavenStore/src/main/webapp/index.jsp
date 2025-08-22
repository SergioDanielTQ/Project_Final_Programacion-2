<%-- 
    Document   : index
    Created on : 20 ago 2025, 7:48:51 p. m.
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tienda de Juegos de Mesa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card shadow p-4">
            <h1 class="text-center">🎲 Bienvenido a la Tienda de Juegos de Mesa 🎲</h1>
            <p class="text-center text-muted">Proyecto Final - Programación II</p>

            <hr>

            <%
                HttpSession sesion = request.getSession(false);
                String usuario = null;
                String rol = null;

                if (sesion != null) {
                    usuario = (String) sesion.getAttribute("usuario");
                    rol = (String) sesion.getAttribute("rol"); 
                }
            %>

            <div class="text-center">
                <%
                    if (usuario != null) {
                %>
                    <h4>Hola, <%= usuario %> 👋</h4>
                    <p>Rol: <b><%= rol %></b></p>
                    
                    <div class="d-flex justify-content-center gap-3 mt-3">
                        <a href="ProductList.jsp" class="btn btn-primary">🛍 Ver Catálogo</a>
                        <a href="carrito.jsp" class="btn btn-success">🛒 Ver Carrito</a>

                        <%
                            if ("admin".equals(rol)) {
                        %>
                            <a href="adminCompras.jsp" class="btn btn-warning">⚙️ Administrar Compras</a>
                            <a href="usuarios.jsp" class="btn btn-secondary">👥 Gestionar Usuarios</a>
                        <%
                            }
                        %>

                        <a href="LogoutServlet" class="btn btn-danger">🚪 Cerrar Sesión</a>
                    </div>
                <%
                    } else {
                %>
                    <p class="text-center">Debes iniciar sesión para continuar.</p>
                    <a href="login.jsp" class="btn btn-primary">🔑 Iniciar Sesión</a>
                    <a href="formUsuario.jsp" class="btn btn-outline-success">📝 Registrarse</a>
                <%
                    }
                %>
            </div>
        </div>
    </div>

</body>
</html>

