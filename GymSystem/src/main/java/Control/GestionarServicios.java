/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Servicio;
import Util.Lectura;
/**
 *
 * @author Santi
 */
public class GestionarServicios {
    private Lectura lectura = new Lectura();
    
    public Servicio crearServicio(){
        Servicio servicio = new Servicio();
        System.out.println("A continuación ingrese los datos del Servicio.");
        servicio.setCodigo(lectura.leerInt("Ingrese el código"));
        servicio.setNombre(lectura.leerString("Nombre"));
        servicio.setDescripcion(lectura.leerString("Descripción"));
        servicio.setDuracion(lectura.leerString("Duración del servicio"));
        
        System.out.println(" Servicio creado exitosamente");
        return servicio;
    }
}