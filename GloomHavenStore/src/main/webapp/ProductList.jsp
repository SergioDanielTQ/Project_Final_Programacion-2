<%-- 
    Document   : ProductList
    Created on : 20 ago 2025, 6:09:04 p. m.
    Author     : sergi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Productos</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container mt-4">
            <h1>Lista de Productos</h1>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Acción</th>
                    </tr>
                </thead>
                <tbody>
                   <c:forEach var="p" items="${productList}">
    <tr>
        <th scope="row">${p.id}</th>
        <td>${p.nombre}</td>
        <td>${p.descripcion}</td>
        <td>$${p.precio}</td>
        <td>
            
            <form action="CarritoServlet" method="post" class="d-flex">
                <input type="hidden" name="idProducto" value="${p.id}"/>
                <input type="number" name="cantidad" value="1" min="1" class="form-control me-2" style="width: 80px;"/>
                <button type="submit" class="btn btn-success">Agregar 🛒</button>
            </form>

            
            <c:if test="${sessionScope.rol == 'admin'}">
                <a href="formProducto.jsp?id=${p.id}" class="btn btn-warning btn-sm ms-2">✏ Editar</a>
                <a href="${pageContext.request.contextPath}/product?accion=eliminar&id=${p.id}" class="btn btn-danger btn-sm ms-2"
                   onclick="return confirm('¿Seguro que deseas eliminar este producto?')">🗑 Eliminar</a>
            </c:if>
        </td>
    </tr>
</c:forEach>
          
                </tbody>
            </table>

            <div class="mt-3">
                <a href="carrito.jsp" class="btn btn-primary">Ver Carrito 🛒</a>
            </div>
        </div>
    </body>
</html>
