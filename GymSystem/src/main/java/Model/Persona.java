/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Santi
 */
public class Persona {
    private int identificación;
    private String nombre;
    private String dirección;
    private int telefono;

    public Persona() {
    }
    

    public Persona(int identificación, String nombre, String dirección, int telefono) {
        this.identificación = identificación;
        this.nombre = nombre;
        this.dirección = dirección;
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdentificación() {
        return identificación;
    }

    public void setIdentificación(int identificación) {
        this.identificación = identificación;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{");
        sb.append("identificaci\u00f3n=").append(identificación);
        sb.append(", nombre=").append(nombre);
        sb.append(", direcci\u00f3n=").append(dirección);
        sb.append(", telefono=").append(telefono);
        sb.append('}');
        return sb.toString();
    }
}