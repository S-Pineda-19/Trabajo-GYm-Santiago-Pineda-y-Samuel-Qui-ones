/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Santi
 */
public class Servicio extends Producto {
    
    private String duracion;
    
    public Servicio() {
    }

    public Servicio(String duracion, int codigo, String nombre, String descripcion) {
        super(codigo, nombre, descripcion);
        this.duracion = duracion;
    }

    public Servicio(int codigo, String nombre, String descripcion) {
        super(codigo, nombre, descripcion);
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Servicio{");
        sb.append("codigo=").append(getCodigo());
        sb.append(", nombre='").append(getNombre()).append('\'');
        sb.append(", descripcion='").append(getDescripcion()).append('\'');
        sb.append(", duracion='").append(duracion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}