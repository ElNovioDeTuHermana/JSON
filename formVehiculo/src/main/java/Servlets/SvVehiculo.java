package Servlets;

import Models.Vehiculo;
import Models.VehiculoDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvVehiculo", urlPatterns = {"/SvVehiculo"})
public class SvVehiculo extends HttpServlet {
    //VehiculoDAO vehiculoDAO = new VehiculoDAO();
    
    String url = "http://localhost:8080/APIVehiculo/webresources/generic";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Vehiculo> lista = new ArrayList<>();
        
        String datosJSON = "";
        URL apiURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea;
            StringBuilder respuesta = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();

            
            datosJSON = respuesta.toString();
            System.out.println("Datos recibidos: " + datosJSON);
        } else {
          
            System.out.println("Error en la solicitud: " + responseCode);
        }
        connection.disconnect();
        Gson gson = new Gson();
        
        lista = gson.fromJson(datosJSON, new TypeToken<List<Vehiculo>>(){}.getType());
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaVehiculos", lista);
        response.sendRedirect("mostrar.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        int puertas =  Integer.parseInt(request.getParameter("puertas"));
        
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setColor(color);
        vehiculo.setPuertas(puertas);
        
        URL apiUrl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) apiUrl.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        
        Gson gson = new Gson();
        String vehiculoJson = gson.toJson(vehiculo);
        
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = vehiculoJson.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }
        
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder responses = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responses.append(inputLine);
                }
                
                String respuesta = response.toString();
                System.out.println("Respuesta de la API: " + respuesta);
            }
        } else {
            System.out.println("Error en la solicitud: " + responseCode);
        }
        con.disconnect();
        
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
