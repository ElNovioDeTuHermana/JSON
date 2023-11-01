/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Controllers.PersistenceController;
import java.util.List;

/**
 *
 * @author djdan
 */
public class VehiculoDAO {
    PersistenceController persistenceController = new PersistenceController();
    
    public void crearVehiculo(Vehiculo vehiculo){
        persistenceController.crearVehiculo(vehiculo);
    }
    
    public List<Vehiculo> traerVehiculos(){
        return persistenceController.traerVehiculos();
    }
    
    public void editarVehiculo(Vehiculo vehiculo){
        persistenceController.actualizarVehiculo(vehiculo);
    }
}
