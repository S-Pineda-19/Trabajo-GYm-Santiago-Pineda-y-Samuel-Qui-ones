
package Model;

/**
 * 
 * @author Santi
 */
public class Ejercicio {
    private int idEjercicio;
    private String nombre;
    private String grupoMuscular;
    private int series;
    private int repeticiones;
    private float peso;
    private int tiempoDescanso; // en segundos
    private String observaciones;

    public Ejercicio() {}

    public Ejercicio(String nombre, String grupoMuscular, int series, int repeticiones) {
        this.nombre = nombre;
        this.grupoMuscular = grupoMuscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = 0;
        this.tiempoDescanso = 60; // 60 segundos por defecto
    }

    public Ejercicio(String nombre, String grupoMuscular, int series, int repeticiones, 
                    float peso, int tiempoDescanso, String observaciones) {
        this.nombre = nombre;
        this.grupoMuscular = grupoMuscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.tiempoDescanso = tiempoDescanso;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdEjercicio() { return idEjercicio; }
    public void setIdEjercicio(int idEjercicio) { this.idEjercicio = idEjercicio; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getGrupoMuscular() { return grupoMuscular; }
    public void setGrupoMuscular(String grupoMuscular) { this.grupoMuscular = grupoMuscular; }
    
    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series; }
    
    public int getRepeticiones() { return repeticiones; }
    public void setRepeticiones(int repeticiones) { this.repeticiones = repeticiones; }
    
    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }
    
    public int getTiempoDescanso() { return tiempoDescanso; }
    public void setTiempoDescanso(int tiempoDescanso) { this.tiempoDescanso = tiempoDescanso; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // Métodos de utilidad
    public String getTiempoDescansoFormateado() {
        int minutos = tiempoDescanso / 60;
        int segundos = tiempoDescanso % 60;
        if (minutos > 0) {
            return minutos + ":" + String.format("%02d", segundos) + " min";
        } else {
            return segundos + " seg";
        }
    }

    public int getVolumenTotal() {
        return series * repeticiones;
    }

    public float getVolumenConPeso() {
        return series * repeticiones * peso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" - ").append(series).append("x").append(repeticiones);
        if (peso > 0) {
            sb.append(" @").append(peso).append("kg");
        }
        sb.append(" (").append(grupoMuscular).append(")");
        sb.append(" - Descanso: ").append(getTiempoDescansoFormateado());
        return sb.toString();
    }
}