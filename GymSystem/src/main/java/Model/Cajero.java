
package Model;

/**
 * 
 * @author Santi
 */
public class Cajero extends Empleado {
    private String turno;
    private boolean activo;

    public Cajero() {
        super();
        this.activo = true;
    }

    public Cajero(String turno, boolean activo, int salario, int antiguedad, String cargo, 
                 int identificación, String nombre, String dirección, int telefono) {
        super(salario, antiguedad, cargo, identificación, nombre, dirección, telefono);
        this.turno = turno;
        this.activo = activo;
    }

    // Getters y Setters
    public String getTurno() { 
        return turno; 
    }
    
    public void setTurno(String turno) { 
        this.turno = turno; 
    }
    
    public boolean isActivo() { 
        return activo; 
    }
    
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    // Métodos de negocio
    public void activarCajero() {
        this.activo = true;
    }

    public void desactivarCajero() {
        this.activo = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cajero{");
        sb.append("ID=").append(getIdentificación());
        sb.append(", nombre='").append(getNombre()).append('\'');
        sb.append(", turno='").append(turno).append('\'');
        sb.append(", activo=").append(activo);
        sb.append(", antiguedad=").append(getAntiguedad());
        sb.append('}');
        return sb.toString();
    }
}