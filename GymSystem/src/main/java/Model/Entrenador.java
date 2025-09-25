
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Santi
 */
public class Entrenador extends Empleado {
    private String especialidad;
    private String certificaciones;
    private List<Integer> clientesAsignados;

    public Entrenador() {
        super();
        this.clientesAsignados = new ArrayList<>();
    }

    public Entrenador(String especialidad, String certificaciones, int salario, int antiguedad, 
                     String cargo, int identificación, String nombre, String dirección, int telefono) {
        super(salario, antiguedad, cargo, identificación, nombre, dirección, telefono);
        this.especialidad = especialidad;
        this.certificaciones = certificaciones;
        this.clientesAsignados = new ArrayList<>();
    }

    public String getEspecialidad() { 
        return especialidad; 
    }
    
    public void setEspecialidad(String especialidad) { 
        this.especialidad = especialidad; 
    }
    
    public String getCertificaciones() { 
        return certificaciones; 
    }
    
    public void setCertificaciones(String certificaciones) { 
        this.certificaciones = certificaciones; 
    }
    
    public List<Integer> getClientesAsignados() { 
        return clientesAsignados; 
    }
    
    public void setClientesAsignados(List<Integer> clientesAsignados) {
        this.clientesAsignados = clientesAsignados;
    }

    // Métodos de negocio
    public void asignarCliente(int clienteId) { 
        if (!this.clientesAsignados.contains(clienteId)) {
            this.clientesAsignados.add(clienteId); 
        }
    }

    public void removerCliente(int clienteId) {
        this.clientesAsignados.remove(Integer.valueOf(clienteId));
    }

    public boolean tieneCliente(int clienteId) {
        return this.clientesAsignados.contains(clienteId);
    }

    public int getCantidadClientes() {
        return this.clientesAsignados.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entrenador{");
        sb.append("ID=").append(getIdentificación());
        sb.append(", nombre='").append(getNombre()).append('\'');
        sb.append(", especialidad='").append(especialidad).append('\'');
        sb.append(", certificaciones='").append(certificaciones).append('\'');
        sb.append(", antiguedad=").append(getAntiguedad());
        sb.append(", clientesAsignados=").append(clientesAsignados.size());
        sb.append('}');
        return sb.toString();
    }
}