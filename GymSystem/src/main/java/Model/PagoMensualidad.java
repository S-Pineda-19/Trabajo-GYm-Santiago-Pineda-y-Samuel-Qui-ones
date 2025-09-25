
package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 * @author Santi
 */
public class PagoMensualidad {
    private int idPago;
    private int clienteId;
    private float monto;
    private LocalDate fechaPago;
    private LocalTime horaPago;
    private String metodoPago; // 
    private String numeroReferencia;
    private int cajeroId;
    private boolean procesado;

    public PagoMensualidad() {
        this.fechaPago = LocalDate.now();
        this.horaPago = LocalTime.now();
        this.procesado = false;
    }

    public PagoMensualidad(int clienteId, float monto, String metodoPago, int cajeroId) {
        this();
        this.clienteId = clienteId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.cajeroId = cajeroId;
    }

    public PagoMensualidad(int idPago, int clienteId, float monto, LocalDate fechaPago, 
                          LocalTime horaPago, String metodoPago, String numeroReferencia, 
                          int cajeroId, boolean procesado) {
        this.idPago = idPago;
        this.clienteId = clienteId;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.horaPago = horaPago;
        this.metodoPago = metodoPago;
        this.numeroReferencia = numeroReferencia;
        this.cajeroId = cajeroId;
        this.procesado = procesado;
    }

   
    public int getIdPago() { 
        return idPago; 
    }
    public void setIdPago(int idPago) { 
        this.idPago = idPago; 
    }
    
    public int getClienteId() { 
        return clienteId; 
    }
    public void setClienteId(int clienteId) { 
        this.clienteId = clienteId; 
    }
    
    public float getMonto() { 
        return monto; 
    }
    public void setMonto(float monto) { 
        this.monto = monto; 
    }
    
    public LocalDate getFechaPago() { 
        return fechaPago; 
    }
    public void setFechaPago(LocalDate fechaPago) { 
        this.fechaPago = fechaPago; 
    }
    
    public LocalTime getHoraPago() { 
        return horaPago; 
    }
    public void setHoraPago(LocalTime horaPago) { 
        this.horaPago = horaPago; 
    }
    
    public String getMetodoPago() { 
        return metodoPago; 
    }
    public void setMetodoPago(String metodoPago) { 
        this.metodoPago = metodoPago; 
    }
    
    public String getNumeroReferencia() { 
        return numeroReferencia; 
    }
    public void setNumeroReferencia(String numeroReferencia) { 
        this.numeroReferencia = numeroReferencia; 
    }
    
    public int getCajeroId() { 
        return cajeroId; 
    }
    public void setCajeroId(int cajeroId) { 
        this.cajeroId = cajeroId; 
    }
    
    public boolean isProcesado() { 
        return procesado; 
    }
    public void setProcesado(boolean procesado) { 
        this.procesado = procesado; 
    }

    public boolean esPagoDelDia() {
        return fechaPago.isEqual(LocalDate.now());
    }

    public boolean esPagoDelMes(int año, int mes) {
        return fechaPago.getYear() == año && fechaPago.getMonthValue() == mes;
    }

    public String getHoraFormateada() {
        return horaPago.toString();
    }

    @Override
    public String toString() {
        return "PagoMensualidad{" +
                "idPago=" + idPago +
                ", clienteId=" + clienteId +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                ", horaPago=" + horaPago +
                ", metodoPago='" + metodoPago + '\'' +
                ", numeroReferencia='" + numeroReferencia + '\'' +
                ", cajeroId=" + cajeroId +
                ", procesado=" + procesado +
                '}';
    }
}