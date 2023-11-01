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

@WebServlet(urlPatterns = {"/SvActualizar"})
public class SvActualizar extends HttpServlet {

    //VehiculoDAO vehiculoDAO = new VehiculoDAO();
    String apiUrl = "http://localhost:8080/APIVehiculo/webresources/generic/editar/";
    String url2 = "http://localhost:8080/APIVehiculo/webresources/generic";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        int puertas =  Integer.parseInt(request.getParameter("puertas"));
        
        HttpSession misesion = request.getSession();
        Vehiculo vehiculo = (Vehiculo) misesion.getAttribute("vehiculoEditar");
        
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setColor(color);
        vehiculo.setPuertas(puertas);
        
        URL url = new URL(apiUrl+vehiculo.getId());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        
        Gson gson = new Gson();
        String vehiculoJSON = gson.toJson(vehiculo);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = vehiculoJSON.getBytes("UTF-8");
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
        
        List<Vehiculo> lista = new ArrayList<>();
        String datosJSON = "";
        URL apiURLl = new URL(url2);
        HttpURLConnection connection = (HttpURLConnection) apiURLl.openConnection();
        connection.setRequestMethod("GET");
        int responseCodes = connection.getResponseCode();
        if (responseCodes == HttpURLConnection.HTTP_OK) {
            
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
        
        lista = gson.fromJson(datosJSON, new TypeToken<List<Vehiculo>>(){}.getType());
        
        misesion.setAttribute("listaVehiculos", lista);
        response.sendRedirect("mostrar.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
