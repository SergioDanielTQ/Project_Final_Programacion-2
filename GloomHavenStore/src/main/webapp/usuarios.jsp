<%-- 
    Document   : Usuarios
    Created on : 9 ago 2025, 1:12:33 p. m.
    Author     : sergi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Lista de Usuarios</h1>
    <a href="${pageContext.request.contextPath}/usuarios?action=form" class="btn btn-primary mb-3">Nuevo Usuario</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Username</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${usuariosList}">
            <tr>
                <th scope="row">${u.getIdUsuario()}</th>
                <td>${u.getNombre()}</td>
                <td>${u.getEmail()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/usuarios?action=form&id=${u.getIdUsuario()}" class="btn btn-sm btn-warning">Editar</a>
                    <a href="${pageContext.request.contextPath}/usuarios?action=delete&id=${u.getIdUsuario()}" class="btn btn-sm btn-danger" onclick="return confirm('¿Estás seguro de que quieres eliminar este usuario?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="index.jsp">Volver al Inicio</a>
</div>
</body>
</html>