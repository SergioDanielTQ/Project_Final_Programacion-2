<%-- 
    Document   : index
    Created on : 20 ago 2025, 7:48:51 p. m.
    Author     : sergi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

            <div class="text-center">
                <!-- Si hay sesión activa -->
                <c:if test="${not empty sessionScope.usuario}">
                    <h4>Hola, ${sessionScope.usuario} 👋</h4>
                    <p>Rol: <b>${sessionScope.rol}</b></p>

                    <div class="d-flex justify-content-center gap-3 mt-3">
                        <a href="ProductServlet" class="btn btn-primary">🛍 Ver Catálogo</a>
                        <a href="carrito.jsp" class="btn btn-success">🛒 Ver Carrito</a>

                        <!-- Opciones solo para admin -->
                        <c:if test="${sessionScope.rol == 'admin'}">
                            <a href="adminCompras.jsp" class="btn btn-warning">⚙️ Administrar Compras</a>
                            <a href="usuarios.jsp" class="btn btn-secondary">👥 Gestionar Usuarios</a>
                        </c:if>

                        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">🚪 Cerrar Sesión</a>
                    </div>
                </c:if>

                <!-- Si NO hay sesión -->
                <c:if test="${empty sessionScope.usuario}">
                    <p class="text-center">Debes iniciar sesión para continuar.</p>
                    <a href="login.jsp" class="btn btn-primary">🔑 Iniciar Sesión</a>
                    <a href="formUsuario.jsp" class="btn btn-outline-success">📝 Registrarse</a>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="text-center text-muted mt-5">
        <p>&copy; 2025 Tienda de Juegos de Mesa | Proyecto Programación II</p>
    </footer>

</body>
</html>
