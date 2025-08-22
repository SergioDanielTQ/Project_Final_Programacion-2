<%-- 
    Document   : formProducto
    Created on : 22 ago 2025, 12:46:43 p. m.
    Author     : sergi
--%>

<%@page import="models.Product"%>
<%@page import="services.ProductServiceImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String idParam = request.getParameter("id");
    Product producto = null;
    if (idParam != null) {
        int id = Integer.parseInt(idParam);
        ProductServiceImpl service = new ProductServiceImpl();
        producto = service.getProductById(id);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Editar Producto</h2>
        <form action="ProductServlet" method="post">
            <input type="hidden" name="accion" value="update"/>
            <input type="hidden" name="id" value="<%= producto.getId() %>"/>

            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" name="nombre" value="<%= producto.getNombre() %>" class="form-control" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Descripción:</label>
                <textarea name="descripcion" class="form-control" required><%= producto.getDescripcion() %></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Precio:</label>
                <input type="number" step="0.01" name="precio" value="<%= producto.getPrecio() %>" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-primary">💾 Guardar</button>
            <a href="ProductList.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
