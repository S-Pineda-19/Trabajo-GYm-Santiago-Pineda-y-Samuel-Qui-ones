
package Model;

import Control.Pago;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Santi
 */
public class EstadoCuenta {
    private int clienteId;
    private List<Pago> pagos;
    private float saldoPendiente;
    private LocalDate ultimoPago;
    private boolean accesoActivo;

    public EstadoCuenta(int clienteId) {
        this.clienteId = clienteId;
        this.pagos = new ArrayList<>();
        this.saldoPendiente = 0;
        this.accesoActivo = false;
    }
    
    public int getClienteId() { 
        return clienteId; 
    }
    public void setClienteId(int clienteId) { 
        this.clienteId = clienteId; 
    }
    
    public List<Pago> getPagos() { 
        return pagos; 
    }
    
    public void setPagos(List<Pago> pagos) { 
        this.pagos = pagos; 
    }
    
    public float getSaldoPendiente() { 
        return saldoPendiente; 
    }
    public void setSaldoPendiente(float saldoPendiente) { 
        this.saldoPendiente = saldoPendiente; 
    }
    
    public LocalDate getUltimoPago() { 
        return ultimoPago; 
    }
    public void setUltimoPago(LocalDate ultimoPago) { 
        this.ultimoPago = ultimoPago; 
    }
    
    public boolean isAccesoActivo() { 
        return accesoActivo; 
    }
    public void setAccesoActivo(boolean accesoActivo) { 
        this.accesoActivo = accesoActivo; 
    }

    public void agregarPago(Pago pago) {
        this.pagos.add(pago);
        this.ultimoPago = pago.getFechaPago();
        actualizarEstado();
    }

    private void actualizarEstado() {
        if (ultimoPago != null) {
            LocalDate fechaLimite = ultimoPago.plusMonths(1);
            this.accesoActivo = LocalDate.now().isBefore(fechaLimite) || 
                              LocalDate.now().isEqual(fechaLimite);
        }
    }

    public int getDiasRestantesAcceso() {
        if (ultimoPago == null) {
            return 0;
        }
        LocalDate fechaLimite = ultimoPago.plusMonths(1);
        LocalDate hoy = LocalDate.now();
        
        if (hoy.isAfter(fechaLimite)) {
            return 0;
        }
        return (int) hoy.until(fechaLimite).getDays();
    }

    public boolean necesitaRenovar() {
        return getDiasRestantesAcceso() <= 5;
    }

    public float calcularTotalPagado() {
        float total = 0;
        for (Pago pago : pagos) {
            total += pago.getMonto();
        }
        return total;
    }

    @Override
    public String toString() {
        return "EstadoCuenta{" +
                "clienteId=" + clienteId +
                ", saldoPendiente=" + saldoPendiente +
                ", ultimoPago=" + ultimoPago +
                ", accesoActivo=" + accesoActivo +
                ", diasRestantes=" + getDiasRestantesAcceso() +
                ", totalPagos=" + pagos.size() +
                ", totalPagado=" + calcularTotalPagado() +
                '}';
    }
}