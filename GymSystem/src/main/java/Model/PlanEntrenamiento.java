
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Santi
 */
public class PlanEntrenamiento {
    private int idPlan;
    private int clienteId;
    private int entrenadorId;
    private LocalDate fechaCreacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Ejercicio> ejercicios;
    private String objetivo;
    private String observaciones;
    private boolean activo;

    public PlanEntrenamiento() {
        this.ejercicios = new ArrayList<>();
        this.fechaCreacion = LocalDate.now();
        this.activo = true;
    }

    public PlanEntrenamiento(int idPlan, int clienteId, int entrenadorId, String objetivo) {
        this();
        this.idPlan = idPlan;
        this.clienteId = clienteId;
        this.entrenadorId = entrenadorId;
        this.objetivo = objetivo;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = LocalDate.now().plusDays(30);
    }

    public int getIdPlan() { 
        return idPlan; 
    }
    public void setIdPlan(int idPlan) { 
        this.idPlan = idPlan;
    }
    
    public int getClienteId() {
        return clienteId; 
    }
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId; 
    }
    
    public int getEntrenadorId() { 
        return entrenadorId;
    }
    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }
    
    public LocalDate getFechaCreacion() { 
        return fechaCreacion; 
    }
    public void setFechaCreacion(LocalDate fechaCreacion) { 
        this.fechaCreacion = fechaCreacion; 
    }
    
    public LocalDate getFechaInicio() { 
        return fechaInicio; 
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin; 
    }
    
    public List<Ejercicio> getEjercicios() {
        return ejercicios; 
    }
    public void setEjercicios(List<Ejercicio> ejercicios) { 
        this.ejercicios = ejercicios;
    }
    
    public String getObjetivo() {
        return objetivo; 
    }
    public void setObjetivo(String objetivo) { 
        this.objetivo = objetivo;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones; 
    }
    
    public boolean isActivo() {
        return activo; 
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        this.ejercicios.add(ejercicio);
    }

    public void removerEjercicio(Ejercicio ejercicio) {
        this.ejercicios.remove(ejercicio);
    }

    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return activo && (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) 
               && (hoy.isEqual(fechaFin) || hoy.isBefore(fechaFin));
    }

    public int getDiasRestantes() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(fechaFin)) {
            return 0;
        }
        return (int) hoy.until(fechaFin).getDays();
    }

    @Override
    public String toString() {
        return "PlanEntrenamiento{" +
                "idPlan=" + idPlan +
                ", clienteId=" + clienteId +
                ", entrenadorId=" + entrenadorId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", objetivo='" + objetivo + '\'' +
                ", activo=" + activo +
                ", ejercicios=" + ejercicios.size() +
                ", diasRestantes=" + getDiasRestantes() +
                '}';
    }
}