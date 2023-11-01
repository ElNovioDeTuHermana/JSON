<%-- 
    Document   : editar
    Created on : 27 oct 2023, 2:00:37
    Author     : djdan
--%>

<%@page import="Models.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
    </head>
    <body>
        
        <%
          Vehiculo vehiculo = (Vehiculo) request.getSession().getAttribute("vehiculoEditar");        
        %>
    <h1>Editar Vehiculo</h1>
    <form action="SvActualizar" method="POST">
        <label for="placa">Placa:</label>
        <input type="text" id="placa" name="placa" value="<%= vehiculo.getPlaca() %>"  required><br><br>

        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo" value="<%= vehiculo.getModelo() %>" required><br><br>

        <label for="color">Color:</label>
        <input type="text" id="color" name="color" value="<%= vehiculo.getColor() %>" required><br><br>

        <label for="puertas">Puertas:</label>
        <input type="number" id="puertas" name="puertas" value="<%= vehiculo.getPuertas() %>" required><br><br>

        <input type="submit" value="Guardar">
    </form>
    
    <form action="SvVehiculo" method="GET">
        <button type="submit"> Mostrar Vehiculos </button>
    </form>
</body>
</html>
