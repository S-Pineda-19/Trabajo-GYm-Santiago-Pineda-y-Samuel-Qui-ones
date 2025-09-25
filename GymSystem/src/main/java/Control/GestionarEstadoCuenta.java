
package Control;

import Model.*;
import Util.Lectura;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Santi
 */
public class GestionarEstadoCuenta {
    private Map<Integer, EstadoCuenta> estadosCuenta;
    private Lectura lectura = new Lectura();

    public GestionarEstadoCuenta() {
        this.estadosCuenta = new HashMap<>();
    }

    public EstadoCuenta obtenerEstadoCuenta(int clienteId) {
        return estadosCuenta.computeIfAbsent(clienteId, k -> new EstadoCuenta(clienteId));
    }

    public void mostrarEstadoCuenta(int clienteId) {
        EstadoCuenta estado = obtenerEstadoCuenta(clienteId);
        
        System.out.println("=== ESTADO DE CUENTA - CLIENTE ID: " + clienteId + " ===");
        System.out.println("Saldo pendiente: $" + String.format("%.2f", estado.getSaldoPendiente()));
        System.out.println("Último pago: " + (estado.getUltimoPago() != null ? estado.getUltimoPago() : "Ninguno"));
        System.out.println("Acceso activo: " + (estado.isAccesoActivo() ? " SÍ" : " NO"));
        
        if (!estado.isAccesoActivo()) {
            System.out.println("ATENCIÓN: El cliente NO tiene acceso al gimnasio");
            System.out.println("Debe realizar el pago de mensualidad");
        } else {
            int diasRestantes = estado.getDiasRestantesAcceso();
            System.out.println("El cliente tiene acceso activo al gimnasio");
            System.out.println("Días restantes: " + diasRestantes);
            
            if (estado.necesitaRenovar()) {
                System.out.println("Próximo a vencer - Notificar al cliente");
            }
        }
        
        System.out.println("\nHistorial de pagos (" + estado.getPagos().size() + " pagos):");
        if (estado.getPagos().isEmpty()) {
            System.out.println("  No hay pagos registrados");
        } else {
            System.out.println("  Total pagado: $" + String.format("%.2f", estado.calcularTotalPagado()));
            for (Pago pago : estado.getPagos()) {
                System.out.println("  - " + pago);
            }
        }
    }

    public boolean verificarAccesoGimnasio(int clienteId) {
        EstadoCuenta estado = obtenerEstadoCuenta(clienteId);
        return estado.isAccesoActivo();
    }

    public void actualizarEstadoConPago(int clienteId, PagoMensualidad pago) {
        EstadoCuenta estado = obtenerEstadoCuenta(clienteId);
        Pago pagoParaEstado = new PagoImpl(pago.getMonto(), pago.getFechaPago(), pago.getMetodoPago());
        estado.agregarPago(pagoParaEstado);
        
        System.out.println("Estado de cuenta actualizado para cliente " + clienteId);
    }

    public void mostrarClientesConAccesoPorVencer() {
        System.out.println("=== CLIENTES CON ACCESO POR VENCER ===");
        boolean hayVencimientos = false;
        
        for (Map.Entry<Integer, EstadoCuenta> entry : estadosCuenta.entrySet()) {
            EstadoCuenta estado = entry.getValue();
            if (estado.necesitaRenovar() && estado.isAccesoActivo()) {
                System.out.println("?Cliente " + entry.getKey() + 
                                 " - Vence en " + estado.getDiasRestantesAcceso() + " días");
                hayVencimientos = true;
            }
        }
        
        if (!hayVencimientos) {
            System.out.println("No hay clientes con acceso próximo a vencer");
        }
    }

    public void mostrarClientesSinAcceso() {
        System.out.println("=== CLIENTES SIN ACCESO AL GIMNASIO ===");
        boolean haySinAcceso = false;
        
        for (Map.Entry<Integer, EstadoCuenta> entry : estadosCuenta.entrySet()) {
            EstadoCuenta estado = entry.getValue();
            if (!estado.isAccesoActivo()) {
                System.out.println("Cliente " + entry.getKey() + 
                                 " - Último pago: " + estado.getUltimoPago());
                haySinAcceso = true;
            }
        }
        
        if (!haySinAcceso) {
            System.out.println("Todos los clientes tienen acceso activo");
        }
    }

    private static class PagoImpl implements Pago {
        private float monto;
        private LocalDate fechaPago;
        private String metodoPago;

        public PagoImpl(float monto, LocalDate fechaPago, String metodoPago) {
            this.monto = monto;
            this.fechaPago = fechaPago;
            this.metodoPago = metodoPago;
        }

        public float getMonto() { 
            return monto; 
        }
        public LocalDate getFechaPago() {
            return fechaPago;
        }
        public String getMetodoPago() {
            return metodoPago;
        }

        @Override
        public void pagoEfectivo() {
            System.out.println("Pago procesado en efectivo: $" + monto);
        }

        @Override
        public void pagoCheque() {
            System.out.println("Pago procesado con cheque: $" + monto);
        }

        @Override
        public String toString() {
            return fechaPago + " - $" + String.format("%.2f", monto) + " (" + metodoPago + ")";
        }
    }

    public Map<Integer, EstadoCuenta> getTodosLosEstados() {
        return estadosCuenta;
    }

    public int contarClientesActivos() {
        int activos = 0;
        for (EstadoCuenta estado : estadosCuenta.values()) {
            if (estado.isAccesoActivo()) {
                activos++;
            }
        }
        return activos;
    }

    public int contarClientesInactivos() {
        int inactivos = 0;
        for (EstadoCuenta estado : estadosCuenta.values()) {
            if (!estado.isAccesoActivo()) {
                inactivos++;
            }
        }
        return inactivos;
    }
}