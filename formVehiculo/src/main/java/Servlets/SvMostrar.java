
package Servlets;

import Models.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvMostrar", urlPatterns = {"/SvMostrar"})
public class SvMostrar extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        int puertas = Integer.parseInt(request.getParameter("puertas"));
        
        Vehiculo vehiculo = new Vehiculo(id, placa, modelo, color, puertas);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("vehiculoEditar", vehiculo);
        response.sendRedirect("editar.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
