
package Controllers;

import Models.Vehiculo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenceController {
    
    VehiculoJpaController vehiculoJpa = new VehiculoJpaController();
    
    public void crearVehiculo (Vehiculo vehiculo){
        vehiculoJpa.create(vehiculo);
    }
    
    public List<Vehiculo> traerVehiculos(){
        return vehiculoJpa.findVehiculoEntities();
    }
    
    public void actualizarVehiculo(Vehiculo vehiculo){
        try {
            vehiculoJpa.edit(vehiculo);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
