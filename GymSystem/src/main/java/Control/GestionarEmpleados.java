/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Empleado;
import Util.Lectura;
/**
 *
 * @author Santi
 */
public class GestionarEmpleados {
    private Lectura lectura = new Lectura();
    
    public Empleado crearEmpleado(){
        Empleado empleado = new Empleado();
        System.out.println("A continuacion los datos del empleado:");
        empleado.setIdentificación(lectura.leerInt("Identificacion"));
        empleado.setNombre(lectura.leerString("Nombre"));
        empleado.setDirección(lectura.leerString("Direccion"));
        empleado.setTelefono(lectura.leerInt("Telefono"));
        empleado.setAntiguedad(lectura.leerInt("Numero de años en la empresa"));
        empleado.setCargo(lectura.leerString("Cargo en la empresa"));
        empleado.setSalario(lectura.leerInt("Salario"));
        return empleado;
    }
}