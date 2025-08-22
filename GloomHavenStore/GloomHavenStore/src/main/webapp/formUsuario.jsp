<%-- 
    Document   : formUsuario
    Created on : 20 ago 2025, 6:10:40 p. m.
    Author     : sergi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card shadow p-4">
            <h2 class="mb-4">Registro / Edición de Usuario</h2>

            <%
                Usuario usuario = (Usuario) request.getAttribute("usuario");
                String action = (usuario != null) ? "update" : "create";
            %>

            <form action="<%= request.getContextPath() %>/usuarios" method="post">
                <input type="hidden" name="action" value="create">
                <input type="hidden" name="id" value="<%= (usuario != null) ? usuario.getIdUsuario() : "" %>"/>

                <div class="mb-3">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control" 
                           value="<%= (usuario != null) ? usuario.getNombre() : "" %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" 
                           value="<%= (usuario != null) ? usuario.getEmail() : "" %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="password" class="form-control" 
                </div>

                <div class="mb-3">
                    <label class="form-label">Rol</label>
                    <select name="rol" class="form-select" required>
                        <option value="cliente" <%= (usuario != null && "cliente".equals(usuario.getRol())) ? "selected" : "" %>>Cliente</option>
                        <option value="admin" <%= (usuario != null && "admin".equals(usuario.getRol())) ? "selected" : "" %>>Administrador</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">💾 Guardar</button>
                <a href="<%= request.getContextPath() %>/usuarios" class="btn btn-secondary">↩ Volver a la lista</a>
            </form>
        </div>
    </div>

</body>
</html>
