<%-- 
    Document   : carrito
    Created on : 20 ago 2025, 6:55:41 p. m.
    Author     : sergi
--%>

<%@page import="java.util.List"%>
<%@page import="models.Product"%>
<%@page import="models.DetalleCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <link rel="stylesheet" href="resources/styles.css"> 
</head>
<body>
    <h1>🛒 Carrito de Compras</h1>

    <%
        List<DetalleCompra> carrito = (List<DetalleCompra>) session.getAttribute("carrito");
        double total = 0;
    %>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>ID Producto</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        <%
            if (carrito != null && !carrito.isEmpty()) {
                for (DetalleCompra d : carrito) {
                    total += d.getSubtotal();
        %>
        <tr>
            <td><%= d.getIdProducto() %></td>
            <td><%= d.getCantidad() %></td>
            <td>$<%= d.getSubtotal() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">Tu carrito está vacío.</td>
        </tr>
        <%
            }
        %>
    </table>

    <h3>Total: $<%= total %></h3>

    <%
        if (carrito != null && !carrito.isEmpty()) {
            Integer idUsuario = (Integer) session.getAttribute("idUsuario"); 
            if (idUsuario != null) {
    %>
        <form action="CompraServlet" method="post">
            <input type="hidden" name="idUsuario" value="<%= idUsuario %>"/>
            <input type="submit" value="✅ Confirmar Compra"/>
        </form>
    <%
            } else {
    %>
        <p style="color:red;">Debes iniciar sesión para comprar.</p>
        <a href="login.jsp">Iniciar Sesión</a>
    <%
            }
        }
    %>

    <br>
    <a href="ProductList.jsp">⬅ Volver al Catálogo</a>
</body>
</html>
