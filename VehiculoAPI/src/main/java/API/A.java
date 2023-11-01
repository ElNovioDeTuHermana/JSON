
package API;

import Models.Vehiculo;
import Models.VehiculoDAO;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("generic")
public class A {

    @Context
    private UriInfo context;

    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    
    public A() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerListaDeVehiculos() {
        // Aquí debes implementar la lógica para obtener la lista de vehículos desde tu aplicación.
        List<Vehiculo> vehiculos = vehiculoDAO.traerVehiculos();
        Gson gson = new Gson();
        String vehiculosJson = gson.toJson(vehiculos);
        return vehiculosJson;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El vehículo no puede ser nulo").build();
        }
        
        vehiculoDAO.crearVehiculo(vehiculo);

        return Response.status(Response.Status.OK).entity("Vehículo agregado exitosamente").build();
    }

    
    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarVehiculo(@PathParam("id") int id, Vehiculo vehiculo) {
        
        if (vehiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Los datos del vehículo son inválidos").build();
        }

        vehiculoDAO.editarVehiculo(vehiculo);

        return Response.status(Response.Status.OK).entity("Vehículo editado exitosamente").build();
    }
}
