
<%@page import="java.util.List"%>
<%@page import="Models.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/mostrar.css">
    </head>
    <body>
    <h1>Listado de Modelos de Vehículos</h1>

    <table border="1">
        <tr>
            <th>Placa</th>
            <th>Modelo</th>
            <th>Color</th>
            <th>Puertas</th>
            <th>Editar</th>
        </tr>
        <%
          List<Vehiculo> lista = (List) request.getSession().getAttribute("listaVehiculos");
          int cont = 1;
          for(Vehiculo vehiculo : lista){
          
            
        %>
        <tr>
            <td><%= vehiculo.getPlaca() %></td>
            <td><%= vehiculo.getModelo() %></td>
            <td><%= vehiculo.getColor() %></td>
            <td><%= vehiculo.getPuertas() %></td>
            <td><form action="SvMostrar" method="POST">
                    <input type="text" name="id" value="<%= vehiculo.getId() %>" style="display: none;">
                    <input type="text" name="placa" value="<%= vehiculo.getPlaca() %>" style="display: none;"> 
                    <input type="text" name="modelo" value="<%= vehiculo.getModelo() %>" style="display: none;"> 
                    <input type="text" name="color" value="<%= vehiculo.getColor() %>" style="display: none;"> 
                    <input type="text" name="puertas" value="<%= vehiculo.getPuertas() %>" style="display: none;">
                    <input type="submit" value="Editar">
                </form>
            </td>
        </tr>
        <% } %>
        <!-- Agrega más filas según tus modelos de vehículos -->
    </table>

    <br>
    
    <form action="SvActualizar" method="GET">
        <input type="submit" value="Crear Vehiculo"> 
    </form>   
</body>
</html>
