
package Model;

import java.time.LocalDate;

/**
 * 
 * @author Santi
 */
public class ClienteInf extends Cliente {
    private float peso;
    private float altura;
    private String nivelActividad;
    private String objetivoFitness;
    private String lesionesAnteriores;
    private LocalDate fechaRegistro;

    
    public ClienteInf() {
        super();
        this.fechaRegistro = LocalDate.now();
    }

    public ClienteInf(float peso, float altura, String nivelActividad, String objetivoFitness, 
                           String lesionesAnteriores, int estratoSE, String trabajaEn, 
                           Boolean practicaActividadFisica, String actividadFisica, int cantidadAFMinutos, 
                           int identificación, String nombre, String dirección, int telefono) {
        super(estratoSE, trabajaEn, practicaActividadFisica, actividadFisica, cantidadAFMinutos, 
              identificación, nombre, dirección, telefono);
        
        this.peso = peso;
        this.altura = altura;
        this.nivelActividad = nivelActividad;
        this.objetivoFitness = objetivoFitness;
        this.lesionesAnteriores = lesionesAnteriores;
        this.fechaRegistro = LocalDate.now();
    }

    
    public float getPeso() { 
        return peso; 
    }
    
    public void setPeso(float peso) { 
        this.peso = peso; 
    }
    
    public float getAltura() { 
        return altura; 
    }
    public void setAltura(float altura) { 
        this.altura = altura; 
    }
    
    public String getNivelActividad() { 
        return nivelActividad; 
    }
    public void setNivelActividad(String nivelActividad) { 
        this.nivelActividad = nivelActividad; 
    }
    
    public String getObjetivoFitness() { 
        return objetivoFitness; 
    }
    
    public void setObjetivoFitness(String objetivoFitness) { 
        this.objetivoFitness = objetivoFitness; 
    }
    
    public String getLesionesAnteriores() { return lesionesAnteriores; }
    public void setLesionesAnteriores(String lesionesAnteriores) { 
        this.lesionesAnteriores = lesionesAnteriores; 
    }
    
    public LocalDate getFechaRegistro() { 
        return fechaRegistro; 
    }

    
    public float calcularIMC() {
        if (altura > 0) {
            return peso / (altura * altura);
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClienteExtendido{");
        sb.append(super.toString());
        sb.append(", peso=").append(peso);
        sb.append(", altura=").append(altura);
        sb.append(", IMC=").append(String.format("%.2f", calcularIMC()));
        sb.append(", nivelActividad='").append(nivelActividad).append('\'');
        sb.append(", objetivoFitness='").append(objetivoFitness).append('\'');
        sb.append(", lesionesAnteriores='").append(lesionesAnteriores).append('\'');
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append('}');
        return sb.toString();
    }
}