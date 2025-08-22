<%-- 
    Document   : login
    Created on : 20 ago 2025, 6:10:57 p. m.
    Author     : sergi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Login</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Iniciar sesión</h1>

            <% String error = request.getParameter("error"); %>
            <% if(error != null){ %>
                <div class="alert alert-danger">Usuario o contraseña incorrectos</div>
            <% } %>

            <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                <div class="mb-3">
                    <label for="email" class="form-label">Email de Usuario</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                     <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                </div>
            </form>
        </div>
    </body>
</html>
    