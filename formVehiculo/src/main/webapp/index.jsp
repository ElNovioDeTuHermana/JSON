
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
    </head>
    <body>
    <h1>Crear Modelo de Vehículo</h1>
    <form action="SvVehiculo" method="POST">
        <label for="placa">Placa:</label>
        <input type="text" id="placa" name="placa" required><br><br>

        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" name="modelo" required><br><br>

        <label for="color">Color:</label>
        <input type="text" id="color" name="color" required><br><br>

        <label for="puertas">Puertas:</label>
        <input type="number" id="puertas" name="puertas" required><br><br>

        <input type="submit" value="Guardar">
    </form>
    
    <form action="SvVehiculo" method="GET">
        <button type="submit"> Mostrar Vehiculos </button>
    </form>
</body>
</html>
