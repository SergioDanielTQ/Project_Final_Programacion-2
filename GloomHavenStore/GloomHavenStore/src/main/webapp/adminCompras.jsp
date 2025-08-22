<%-- 
    Document   : adminCompras
    Created on : 20 ago 2025, 6:52:16 p. m.
    Author     : sergi
--%>

<%@page import="java.util.List"%>
<%@page import="models.Compra"%>
<%@page import="services.CompraServiceImpl"%>
<%@page import="services.CompraService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Compras - Administrador</title>
    <link rel="stylesheet" href="resources/styles.css"> 
</head>
<body>
    <h1>Panel de Administración - Compras</h1>

    <%
        CompraService compraService = new CompraServiceImpl();
        List<Compra> compras = compraService.listarComprasPendientes();
    %>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>ID Compra</th>
            <th>ID Usuario</th>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <%
            if (compras != null && !compras.isEmpty()) {
                for (Compra c : compras) {
        %>
        <tr>
            <td><%= c.getIdCompra() %></td>
            <td><%= c.getIdUsuario() %></td>
            <td><%= c.getFecha() %></td>
            <td><%= c.getEstado() %></td>
            <td>
                <form action="AdminCompraServlet" method="post" style="display:inline;">
                    <input type="hidden" name="idCompra" value="<%= c.getIdCompra() %>"/>
                    <input type="hidden" name="accion" value="confirmar"/>
                    <input type="submit" value="✅ Confirmar"/>
                </form>

                <form action="AdminCompraServlet" method="post" style="display:inline;">
                    <input type="hidden" name="idCompra" value="<%= c.getIdCompra() %>"/>
                    <input type="hidden" name="accion" value="rechazar"/>
                    <input type="submit" value="❌ Rechazar"/>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No hay compras pendientes.</td>
        </tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="ProductList.jsp">Volver al Catálogo</a>
</body>
</html>
