
package Control;

import Model.*;
import Util.Lectura;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * @author Santi
 */
public class GestionarPagos {
    private List<PagoMensualidad> pagos;
    private Lectura lectura = new Lectura();
    private int siguienteId;
    private GestionarEstadoCuenta gestionEstado;

    public GestionarPagos(GestionarEstadoCuenta gestionEstado) {
        this.pagos = new ArrayList<>();
        this.siguienteId = 1;
        this.gestionEstado = gestionEstado;
    }

    public PagoMensualidad registrarPago(int clienteId, int cajeroId) {
        System.out.println("=== REGISTRO DE PAGO DE MENSUALIDAD ===");
        
        float monto = lectura.leerFloat("Monto a pagar");
        
        System.out.println("\nMétodos de pago disponibles:");
        System.out.println("1. EFECTIVO");
        System.out.println("2. TARJETA");
        System.out.println("3. CHEQUE");
        System.out.println("4. TRANSFERENCIA");
        
        int opcionMetodo = lectura.leerOpcionMenu("Seleccione método de pago", 1, 4);
        String metodoPago = obtenerMetodoPago(opcionMetodo);
        
        PagoMensualidad pago = new PagoMensualidad(clienteId, monto, metodoPago, cajeroId);
        pago.setIdPago(siguienteId++);
        
        procesarSegunMetodo(pago, metodoPago);
        
        pago.setProcesado(true);
        pagos.add(pago);
        
        gestionEstado.actualizarEstadoConPago(clienteId, pago);
        
        System.out.println("\n Pago registrado exitosamente");
        System.out.println("ID de transacción: " + pago.getIdPago());
        System.out.println("Fecha y hora: " + pago.getFechaPago() + " " + pago.getHoraPago());
        
        return pago;
    }

    private String obtenerMetodoPago(int opcion) {
        switch (opcion) {
            case 1: return "EFECTIVO";
            case 2: return "TARJETA";
            case 3: return "CHEQUE";
            case 4: return "TRANSFERENCIA";
            default: return "EFECTIVO";
        }
    }

    private void procesarSegunMetodo(PagoMensualidad pago, String metodo) {
        switch (metodo) {
            case "EFECTIVO":
                System.out.println(" Procesando pago en efectivo...");
                float recibido = lectura.leerFloat("Monto recibido");
                if (recibido >= pago.getMonto()) {
                    float cambio = recibido - pago.getMonto();
                    if (cambio > 0) {
                        System.out.println(" Cambio a entregar: $" + String.format("%.2f", cambio));
                    }
                } else {
                    System.out.println(" Monto insuficiente");
                    return;
                }
                break;
                
            case "TARJETA":
                System.out.println(" Procesando pago con tarjeta...");
                String numeroTarjeta = lectura.leerString("Últimos 4 dígitos de la tarjeta");
                pago.setNumeroReferencia("****" + numeroTarjeta);
                System.out.println(" Transacción aprobada");
                break;
                
            case "CHEQUE":
                System.out.println(" Procesando pago con cheque...");
                String banco = lectura.leerString("Banco emisor");
                String numeroCheque = lectura.leerString("Número de cheque");
                pago.setNumeroReferencia(banco + "-" + numeroCheque);
                System.out.println(" Cheque recibido - Pendiente de verificación");
                break;
                
            case "TRANSFERENCIA":
                System.out.println(" Procesando transferencia bancaria...");
                String referencia = lectura.leerString("Número de referencia");
                pago.setNumeroReferencia(referencia);
                System.out.println(" Transferencia registrada");
                break;
        }
    }

    public List<PagoMensualidad> obtenerPagosDelDia() {
        return obtenerPagosDelDia(LocalDate.now());
    }

    public List<PagoMensualidad> obtenerPagosDelDia(LocalDate fecha) {
        List<PagoMensualidad> pagosDelDia = new ArrayList<>();
        for (PagoMensualidad pago : pagos) {
            if (pago.getFechaPago().equals(fecha)) {
                pagosDelDia.add(pago);
            }
        }
        return pagosDelDia;
    }

    public List<PagoMensualidad> obtenerPagosPorCajero(int cajeroId) {
        List<PagoMensualidad> pagosCajero = new ArrayList<>();
        for (PagoMensualidad pago : pagos) {
            if (pago.getCajeroId() == cajeroId) {
                pagosCajero.add(pago);
            }
        }
        return pagosCajero;
    }

    public List<PagoMensualidad> obtenerPagosPorMetodo(String metodoPago) {
        List<PagoMensualidad> pagosPorMetodo = new ArrayList<>();
        for (PagoMensualidad pago : pagos) {
            if (pago.getMetodoPago().equals(metodoPago)) {
                pagosPorMetodo.add(pago);
            }
        }
        return pagosPorMetodo;
    }

    public void mostrarHistorialPagosCliente(int clienteId) {
        System.out.println("=== HISTORIAL DE PAGOS - CLIENTE " + clienteId + " ===");
        List<PagoMensualidad> pagosCliente = new ArrayList<>();
        
        for (PagoMensualidad pago : pagos) {
            if (pago.getClienteId() == clienteId) {
                pagosCliente.add(pago);
            }
        }
        
        if (pagosCliente.isEmpty()) {
            System.out.println("No hay pagos registrados para este cliente");
        } else {
            pagosCliente.sort((p1, p2) -> p2.getFechaPago().compareTo(p1.getFechaPago()));
            
            float totalPagado = 0;
            for (PagoMensualidad pago : pagosCliente) {
                System.out.println("- " + pago.getFechaPago() + " | $" + 
                                 String.format("%.2f", pago.getMonto()) + 
                                 " | " + pago.getMetodoPago() + 
                                 " | Cajero: " + pago.getCajeroId());
                totalPagado += pago.getMonto();
            }
            
            System.out.println("\nTotal pagado: $" + String.format("%.2f", totalPagado));
            System.out.println("Cantidad de pagos: " + pagosCliente.size());
        }
    }

    public float calcularTotalRecaudadoDia(LocalDate fecha) {
        float total = 0;
        for (PagoMensualidad pago : obtenerPagosDelDia(fecha)) {
            total += pago.getMonto();
        }
        return total;
    }

    public Map<String, Float> obtenerTotalesPorMetodoDia(LocalDate fecha) {
        Map<String, Float> totales = new HashMap<>();
        for (PagoMensualidad pago : obtenerPagosDelDia(fecha)) {
            String metodo = pago.getMetodoPago();
            totales.put(metodo, totales.getOrDefault(metodo, 0f) + pago.getMonto());
        }
        return totales;
    }

    public List<PagoMensualidad> getTodosPagos() {
        return pagos;
    }

    public PagoMensualidad buscarPagoPorId(int idPago) {
        for (PagoMensualidad pago : pagos) {
            if (pago.getIdPago() == idPago) {
                return pago;
            }
        }
        return null;
    }
    public boolean eliminarPago(int idPago) {
    PagoMensualidad pago = buscarPagoPorId(idPago);
    if (pago == null) {
        System.out.println(" Pago no encontrado");
        return false;
    }
    
    System.out.println("=== ELIMINAR PAGO ===");
    System.out.println("Pago ID: " + pago.getIdPago());
    System.out.println("Cliente: " + pago.getClienteId());
    System.out.println("Monto: $" + String.format("%.2f", pago.getMonto()));
    System.out.println("⚠️ Eliminar un pago puede afectar el acceso del cliente");
    
    boolean confirmar = lectura.leerSiNo("¿Está seguro de eliminar este pago?");
    if (confirmar) {
        pagos.remove(pago);
        System.out.println(" Pago eliminado exitosamente");
        return true;
    }
    return false;
}

public void mostrarTodosLosPagos() {
    System.out.println("=== TODOS LOS PAGOS REGISTRADOS ===");
    if (pagos.isEmpty()) {
        System.out.println("No hay pagos registrados");
        return;
    }
    
    System.out.printf("%-5s %-8s %-10s %-12s %-12s%n", 
                     "ID", "Cliente", "Monto", "Método", "Fecha");
    System.out.println("-".repeat(50));
    
    for (PagoMensualidad pago : pagos) {
        System.out.printf("%-5d %-8d $%-9.2f %-12s %s%n",
            pago.getIdPago(),
            pago.getClienteId(),
            pago.getMonto(),
            pago.getMetodoPago(),
            pago.getFechaPago()
        );
    }
    System.out.println("\nTotal de pagos: " + pagos.size());
}
}