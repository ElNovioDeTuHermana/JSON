
package Models;

import Controllers.VehiculoJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djdan
 */
public class VehiculoDAO {
    VehiculoJpaController persistenceController = new VehiculoJpaController();
    
    public void crearVehiculo(Vehiculo vehiculo){
        persistenceController.create(vehiculo);
    }
    
    public List<Vehiculo> traerVehiculos(){
        return persistenceController.findVehiculoEntities();
    }
    
    public void editarVehiculo(Vehiculo vehiculo){
        try {
            persistenceController.edit(vehiculo);
        } catch (Exception ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
