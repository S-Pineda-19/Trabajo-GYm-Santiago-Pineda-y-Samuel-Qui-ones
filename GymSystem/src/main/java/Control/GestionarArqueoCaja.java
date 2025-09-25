
package Control;

import Model.*;
import Util.Lectura;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 * @author Santi
 */
public class GestionarArqueoCaja {
    private GestionarPagos gestionPagos;
    private Lectura lectura = new Lectura();

    public GestionarArqueoCaja(GestionarPagos gestionPagos) {
        this.gestionPagos = gestionPagos;
    }

    public ArqueoCaja generarArqueoDiario(int cajeroId) {
        return generarArqueoDiario(cajeroId, LocalDate.now());
    }

    public ArqueoCaja generarArqueoDiario(int cajeroId, LocalDate fecha) {
        System.out.println("=== GENERANDO ARQUEO DE CAJA ===");
        System.out.println("Cajero ID: " + cajeroId);
        System.out.println("Fecha: " + fecha);

        ArqueoCaja arqueo = new ArqueoCaja(cajeroId);
        arqueo.setFecha(fecha);
        
        List<PagoMensualidad> todosLosPagosDelDia = gestionPagos.obtenerPagosDelDia(fecha);
        List<PagoMensualidad> pagosCajero = new ArrayList<>();
        
        for (PagoMensualidad pago : todosLosPagosDelDia) {
            if (pago.getCajeroId() == cajeroId) {
                pagosCajero.add(pago);
                arqueo.agregarPago(pago);
            }
        }

        if (pagosCajero.isEmpty()) {
            System.out.println("No hay transacciones registradas para este cajero en la fecha seleccionada");
        } else {
            System.out.println("Arqueo generado con " + pagosCajero.size() + " transacciones");
        }

        return arqueo;
    }

    public void mostrarArqueoCaja(ArqueoCaja arqueo) {
        System.out.println(arqueo.toString());
        
        if (arqueo.tienePagos()) {
            System.out.println("\nDETALLE DE TRANSACCIONES:");
            System.out.println("=" .repeat(60));
            System.out.printf("%-8s %-10s %-12s %-15s %-10s%n", 
                             "Cliente", "Monto", "Método", "Referencia", "Hora");
            System.out.println("-".repeat(60));
            
            for (PagoMensualidad pago : arqueo.getPagosDelDia()) {
                System.out.printf("%-8d $%-9.2f %-12s %-15s %s%n",
                    pago.getClienteId(),
                    pago.getMonto(),
                    pago.getMetodoPago(),
                    (pago.getNumeroReferencia() != null ? pago.getNumeroReferencia() : "N/A"),
                    pago.getHoraPago()
                );
            }
            
            System.out.println("-".repeat(60));
            System.out.println("TOTAL: $" + String.format("%.2f", arqueo.getTotalGeneral()));
        }
    }

    public void generarReporteDiario() {
        int cajeroId = lectura.leerInt("Ingrese ID del cajero");
        
        boolean otraFecha = lectura.leerSiNo("¿Desea generar el reporte para otra fecha?");
        LocalDate fecha = LocalDate.now();
        
        if (otraFecha) {
            int dia = lectura.leerInt("Día");
            int mes = lectura.leerInt("Mes");
            int año = lectura.leerInt("Año");
            try {
                fecha = LocalDate.of(año, mes, dia);
            } catch (Exception e) {
                System.out.println("Fecha inválida, usando fecha actual");
                fecha = LocalDate.now();
            }
        }

        ArqueoCaja arqueo = generarArqueoDiario(cajeroId, fecha);
        mostrarArqueoCaja(arqueo);
        
        if (arqueo.tienePagos()) {
            boolean verDetalle = lectura.leerSiNo("¿Desea ver el reporte detallado?");
            if (verDetalle) {
                System.out.println("\n" + arqueo.generarResumenDetallado());
            }
        }
    }

    public void generarReporteConsolidado() {
        boolean otraFecha = lectura.leerSiNo("¿Desea generar el reporte para otra fecha?");
        LocalDate fecha = LocalDate.now();
        
        if (otraFecha) {
            int dia = lectura.leerInt("Día");
            int mes = lectura.leerInt("Mes");
            int año = lectura.leerInt("Año");
            try {
                fecha = LocalDate.of(año, mes, dia);
            } catch (Exception e) {
                System.out.println("Fecha inválida, usando fecha actual");
                fecha = LocalDate.now();
            }
        }

        List<PagoMensualidad> todosLosPagos = gestionPagos.obtenerPagosDelDia(fecha);
        
        System.out.println("=== REPORTE CONSOLIDADO DEL DÍA ===");
        System.out.println("Fecha: " + fecha);
        
        if (todosLosPagos.isEmpty()) {
            System.out.println("No hay transacciones registradas para esta fecha");
            return;
        }
        
        Map<String, Float> totalesPorMetodo = new HashMap<>();
        Map<Integer, Integer> transaccionesPorCajero = new HashMap<>();
        Map<Integer, Float> montosPorCajero = new HashMap<>();
        float totalGeneral = 0;
        
        for (PagoMensualidad pago : todosLosPagos) {
            String metodo = pago.getMetodoPago();
            totalesPorMetodo.put(metodo, 
                totalesPorMetodo.getOrDefault(metodo, 0f) + pago.getMonto());
            
           
            int cajeroId = pago.getCajeroId();
            transaccionesPorCajero.put(cajeroId,
                transaccionesPorCajero.getOrDefault(cajeroId, 0) + 1);
            montosPorCajero.put(cajeroId,
                montosPorCajero.getOrDefault(cajeroId, 0f) + pago.getMonto());
            
            totalGeneral += pago.getMonto();
        }
        
        System.out.println("\nTOTALES POR MÉTODO DE PAGO:");
        for (Map.Entry<String, Float> entry : totalesPorMetodo.entrySet()) {
            System.out.println("- " + entry.getKey() + ": $" + 
                             String.format("%.2f", entry.getValue()));
        }
        
        System.out.println("\nRESUMEN POR CAJERO:");
        for (Map.Entry<Integer, Integer> entry : transaccionesPorCajero.entrySet()) {
            int cajeroId = entry.getKey();
            int transacciones = entry.getValue();
            float monto = montosPorCajero.get(cajeroId);
            System.out.println("- Cajero " + cajeroId + ": " + transacciones + 
                             " transacciones - $" + String.format("%.2f", monto));
        }
        
        System.out.println("\nRESUMEN GENERAL:");
        System.out.println("Total de transacciones: " + todosLosPagos.size());
        System.out.println("Total recaudado: $" + String.format("%.2f", totalGeneral));
        System.out.println("Promedio por transacción: $" + 
                         String.format("%.2f", totalGeneral / todosLosPagos.size()));
        System.out.println("Cajeros activos: " + transaccionesPorCajero.size());
    }

    public void compararArqueoConEfectivo() {
        int cajeroId = lectura.leerInt("ID del cajero para verificar");
        LocalDate fecha = LocalDate.now();
        
        ArqueoCaja arqueo = generarArqueoDiario(cajeroId, fecha);
        
        if (!arqueo.tienePagos()) {
            System.out.println("No hay transacciones para comparar");
            return;
        }
        
        float efectivoEnSistema = arqueo.getTotalPorMetodo("EFECTIVO");
        
        System.out.println("=== VERIFICACIÓN DE EFECTIVO ===");
        System.out.println("Efectivo según sistema: $" + String.format("%.2f", efectivoEnSistema));
        
        if (efectivoEnSistema > 0) {
            float efectivoFisico = lectura.leerFloat("Efectivo físico contado");
            float diferencia = efectivoFisico - efectivoEnSistema;
            
            System.out.println("Diferencia: $" + String.format("%.2f", diferencia));
            
            if (Math.abs(diferencia) < 0.01) {
                System.out.println("Arqueo cuadrado - Sin diferencias");
            } else if (diferencia > 0) {
                System.out.println("Sobrante en caja: $" + String.format("%.2f", diferencia));
            } else {
                System.out.println("Faltante en caja: $" + String.format("%.2f", Math.abs(diferencia)));
            }
        } else {
            System.out.println("No hay transacciones en efectivo para verificar");
        }
    }
}