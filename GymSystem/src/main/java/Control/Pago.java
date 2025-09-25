/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Control;

import java.time.LocalDate;

/**
 * @author Santi
 */
public interface Pago {
    public void pagoEfectivo();
    public void pagoCheque();
    
    // Métodos adicionales para el nuevo sistema
    default LocalDate getFechaPago() {
        return LocalDate.now();
    }
    
    default float getMonto() {
        return 0.0f;
    }
    
    default String getMetodoPago() {
        return "EFECTIVO";
    }
}