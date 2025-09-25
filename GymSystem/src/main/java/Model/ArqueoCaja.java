
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Santi
 */
public class ArqueoCaja {
    private LocalDate fecha;
    private int cajeroId;
    private List<PagoMensualidad> pagosDelDia;
    private Map<String, Float> totalesPorMetodo;
    private float totalGeneral;
    private int cantidadTransacciones;

    public ArqueoCaja(int cajeroId) {
        this.fecha = LocalDate.now();
        this.cajeroId = cajeroId;
        this.pagosDelDia = new ArrayList<>();
        this.totalesPorMetodo = new HashMap<>();
        this.totalGeneral = 0;
        this.cantidadTransacciones = 0;
    }

    public ArqueoCaja(LocalDate fecha, int cajeroId) {
        this.fecha = fecha;
        this.cajeroId = cajeroId;
        this.pagosDelDia = new ArrayList<>();
        this.totalesPorMetodo = new HashMap<>();
        this.totalGeneral = 0;
        this.cantidadTransacciones = 0;
    }

    public LocalDate getFecha() { 
        return fecha; 
    }
    
    public void setFecha(LocalDate fecha) { 
        this.fecha = fecha; 
    }
    
    public int getCajeroId() { 
        return cajeroId; 
    }
    
    public void setCajeroId(int cajeroId) { 
        this.cajeroId = cajeroId; 
    }
    
    public List<PagoMensualidad> getPagosDelDia() { 
        return pagosDelDia; 
    }
    
    public void setPagosDelDia(List<PagoMensualidad> pagosDelDia) { 
        this.pagosDelDia = pagosDelDia;
        actualizarTotales();
    }
    
    public Map<String, Float> getTotalesPorMetodo() { 
        return totalesPorMetodo; 
    }
    
    public float getTotalGeneral() { 
        return totalGeneral; 
    }
    
    public int getCantidadTransacciones() { 
        return cantidadTransacciones; 
    }

    
    public void agregarPago(PagoMensualidad pago) {
        if (pago.getFechaPago().equals(this.fecha) && pago.getCajeroId() == this.cajeroId) {
            this.pagosDelDia.add(pago);
            actualizarTotales();
        }
    }

    private void actualizarTotales() {
        totalesPorMetodo.clear();
        totalGeneral = 0;
        cantidadTransacciones = pagosDelDia.size();

        for (PagoMensualidad pago : pagosDelDia) {
            String metodo = pago.getMetodoPago();
            float monto = pago.getMonto();
            
            totalesPorMetodo.put(metodo, 
                totalesPorMetodo.getOrDefault(metodo, 0f) + monto);
            totalGeneral += monto;
        }
    }

    public float getTotalPorMetodo(String metodoPago) {
        return totalesPorMetodo.getOrDefault(metodoPago, 0f);
    }

    public int getCantidadPagosPorMetodo(String metodoPago) {
        int cantidad = 0;
        for (PagoMensualidad pago : pagosDelDia) {
            if (pago.getMetodoPago().equals(metodoPago)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean tienePagos() {
        return !pagosDelDia.isEmpty();
    }

    public String generarResumenDetallado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ARQUEO DE CAJA DETALLADO ===\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Cajero ID: ").append(cajeroId).append("\n");
        sb.append("Transacciones: ").append(cantidadTransacciones).append("\n\n");
        
        sb.append("DETALLE POR MÉTODO DE PAGO:\n");
        for (Map.Entry<String, Float> entry : totalesPorMetodo.entrySet()) {
            String metodo = entry.getKey();
            float total = entry.getValue();
            int cantidad = getCantidadPagosPorMetodo(metodo);
            sb.append("- ").append(metodo).append(": $").append(String.format("%.2f", total))
              .append(" (").append(cantidad).append(" transacciones)\n");
        }
        
        sb.append("\nTOTAL GENERAL: $").append(String.format("%.2f", totalGeneral));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ARQUEO DE CAJA ===\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Cajero ID: ").append(cajeroId).append("\n");
        sb.append("Transacciones: ").append(cantidadTransacciones).append("\n\n");
        sb.append("TOTALES POR MÉTODO:\n");
        for (Map.Entry<String, Float> entry : totalesPorMetodo.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": $").append(String.format("%.2f", entry.getValue())).append("\n");
        }
        sb.append("\nTOTAL GENERAL: $").append(String.format("%.2f", totalGeneral));
        return sb.toString();
    }
}