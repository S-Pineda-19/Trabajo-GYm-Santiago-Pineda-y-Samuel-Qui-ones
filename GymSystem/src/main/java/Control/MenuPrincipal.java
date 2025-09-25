package Control;

import Model.*;
import Util.Lectura;
import java.util.List;

/**
 * @author Santiago y Samuel Rodriguez
 */
public class MenuPrincipal {

    private Lectura lectura = new Lectura();
    private GestionarCliente gestionClientes;
    private GestionarEntrenadores gestionEntrenadores;
    private GestionarPlanesEntrenamiento gestionPlanes;
    private GestionarEstadoCuenta gestionEstado;
    private GestionarPagos gestionPagos;
    private GestionarArqueoCaja gestionArqueo;
    private GestionarProductos gestionProductos;
    private GestionarServicios gestionServicios;
    private GestionarEmpleados gestionEmpleados;

    public MenuPrincipal() {
        inicializarGestores();
    }

    private void inicializarGestores() {
        this.gestionPlanes = new GestionarPlanesEntrenamiento();
        this.gestionClientes = new GestionarCliente();
        this.gestionEntrenadores = new GestionarEntrenadores(gestionPlanes);
        this.gestionEstado = new GestionarEstadoCuenta();
        this.gestionPagos = new GestionarPagos(gestionEstado);
        this.gestionArqueo = new GestionarArqueoCaja(gestionPagos);
        this.gestionProductos = new GestionarProductos();
        this.gestionServicios = new GestionarServicios();
        this.gestionEmpleados = new GestionarEmpleados();
    }

    public void mostrarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            mostrarBanner();
            mostrarOpcionesMenu();

            int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 8);

            switch (opcion) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuEntrenadores();
                    break;
                case 3:
                    menuPlanesEntrenamiento();
                    break;
                case 4:
                    menuPagosYEstados();
                    break;
                case 5:
                    menuArqueoCaja();
                    break;
                case 6:
                    menuProductosServicios();
                    break;
                case 7:
                    menuReportes();
                    break;
                case 8:
                    menuEmpleados();
                    break;
                case 0:
                    continuar = false;
                    System.out.println(" ¡Gracias por usar el Sistema de Gestión de Gimnasio!");
                    System.out.println("¡Hasta pronto!");
                    break;
            }

            if (continuar) {
                lectura.pausar("\n Operación completada.");
            }
        }
    }

    private void mostrarBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("️  SISTEMA DE GESTIÓN DE GIMNASIO  ️");
        System.out.println("=".repeat(60));
    }

    private void mostrarOpcionesMenu() {
        System.out.println("1.  Gestión de Clientes");
        System.out.println("2.  Gestión de Entrenadores");
        System.out.println("3.  Gestión de Planes de Entrenamiento");
        System.out.println("4.  Gestión de Pagos y Estados de Cuenta");
        System.out.println("5. ️  Arqueo de Caja");
        System.out.println("6.  Gestión de Productos y Servicios");
        System.out.println("7.  Reportes");
        System.out.println("8.  Gestión de Empleados");
        System.out.println("0.  Salir");
        System.out.println("=".repeat(60));
    }

    private void menuClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1.  Registrar nuevo cliente");
        System.out.println("2. ️ Ver todos los clientes");
        System.out.println("3.  Buscar cliente por ID");
        System.out.println("4. ️ Modificar cliente");
        System.out.println("5.  Eliminar cliente");
        System.out.println("6.  Verificar acceso al gimnasio");
        System.out.println("7.  Ver estado de cuenta de cliente");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 7);

        switch (opcion) {
            case 1:
                gestionClientes.crearCliente();
                break;
            case 2:
                gestionClientes.mostrarTodosLosClientes();
                break;
            case 3:
                int clienteIdBuscar = lectura.leerInt("ID del cliente a buscar");
                GestionarCliente cliente = gestionClientes.buscarClientePorId(clienteIdBuscar);
                if (cliente != null) {
                    gestionClientes.mostrarPerfilCompleto(cliente);
                } else {
                    System.out.println(" Cliente no encontrado");
                }
                break;
            case 4:
                int clienteIdModificar = lectura.leerInt("ID del cliente a modificar");
                gestionClientes.modificarCliente(clienteIdModificar);
                break;
            case 5:
                int clienteIdEliminar = lectura.leerInt("ID del cliente a eliminar");
                boolean confirmacion = lectura.leerSiNo("⚠️ ¿Está seguro de eliminar este cliente? Esta acción no se puede deshacer");
                if (confirmacion) {
                    gestionClientes.eliminarCliente(clienteIdEliminar);
                }
                break;
            case 6:
                int clienteIdAcceso = lectura.leerInt("ID del cliente a verificar");
                boolean tieneAcceso = gestionEstado.verificarAccesoGimnasio(clienteIdAcceso);
                System.out.println("Cliente " + clienteIdAcceso
                        + (tieneAcceso ? " TIENE ACCESO" : "  NO TIENE ACCESO") + " al gimnasio");
                break;
            case 7:
                int clienteIdEstado = lectura.leerInt("ID del cliente");
                gestionEstado.mostrarEstadoCuenta(clienteIdEstado);
                break;
        }
    }

    private void menuEntrenadores() {
        System.out.println("\n=== GESTIÓN DE ENTRENADORES ===");
        System.out.println("1.  Registrar nuevo entrenador");
        System.out.println("2.️  Ver todos los entrenadores");
        System.out.println("3.️  Modificar entrenador");
        System.out.println("4.️  Eliminar entrenador");
        System.out.println("5.  Asignar cliente a entrenador");
        System.out.println("6.  Ver clientes de un entrenador");
        System.out.println("7.  Ver planes de un entrenador");
        System.out.println("8.  Crear plan para cliente");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 8);

        switch (opcion) {
            case 1:
                gestionEntrenadores.crearEntrenador();
                break;
            case 2:
                gestionEntrenadores.mostrarTodosLosEntrenadores();
                break;
            case 3:
                int entrenadorIdModificar = lectura.leerInt("ID del entrenador a modificar");
                gestionEntrenadores.modificarEntrenador(entrenadorIdModificar);
                break;
            case 4:
                int entrenadorIdEliminar = lectura.leerInt("ID del entrenador a eliminar");
                gestionEntrenadores.eliminarEntrenador(entrenadorIdEliminar);
                break;
            case 5:
                int entrenadorId = lectura.leerInt("ID del entrenador");
                int clienteId = lectura.leerInt("ID del cliente");
                gestionEntrenadores.asignarClienteAEntrenador(entrenadorId, clienteId);
                break;
            case 6:
                int entrenadorIdClientes = lectura.leerInt("ID del entrenador");
                gestionEntrenadores.mostrarClientesDelEntrenador(entrenadorIdClientes);
                break;
            case 7:
                int entrenadorIdPlanes = lectura.leerInt("ID del entrenador");
                gestionEntrenadores.mostrarPlanesDelEntrenador(entrenadorIdPlanes);
                break;
            case 8:
                int entrenadorIdPlan = lectura.leerInt("ID del entrenador");
                int clienteIdPlan = lectura.leerInt("ID del cliente");
                gestionEntrenadores.crearPlanParaCliente(entrenadorIdPlan, clienteIdPlan);
                break;
        }
    }

    private void menuPlanesEntrenamiento() {
        System.out.println("\n=== GESTIÓN DE PLANES DE ENTRENAMIENTO ===");
        System.out.println("1. Crear nuevo plan de entrenamiento");
        System.out.println("2.️ Ver todos los planes");
        System.out.println("3. Ver plan de un cliente específico");
        System.out.println("4. Ver planes de un entrenador");
        System.out.println("5.️ Modificar plan existente");
        System.out.println("6. Agregar ejercicio a un plan");
        System.out.println("7.️ Eliminar ejercicio de un plan");
        System.out.println("8. Desactivar un plan");
        System.out.println("9.️ Eliminar plan completamente");
        System.out.println("0. Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 9);

        switch (opcion) {
            case 1:
                int clienteId = lectura.leerInt("ID del cliente");
                int entrenadorId = lectura.leerInt("ID del entrenador");
                gestionPlanes.crearPlanEntrenamiento(clienteId, entrenadorId);
                break;
            case 2:
                gestionPlanes.mostrarTodosLosPlanes();
                break;
            case 3:
                int clienteIdPlan = lectura.leerInt("ID del cliente");
                PlanEntrenamiento plan = gestionPlanes.obtenerPlanDelCliente(clienteIdPlan);
                if (plan != null) {
                    System.out.println("\n=== PLAN DEL CLIENTE ===");
                    System.out.println(plan);
                    System.out.println("\nEjercicios:");
                    if (plan.getEjercicios().isEmpty()) {
                        System.out.println("  - No hay ejercicios asignados");
                    } else {
                        for (Ejercicio ejercicio : plan.getEjercicios()) {
                            System.out.println("  - " + ejercicio);
                        }
                    }
                } else {
                    System.out.println("❌ El cliente no tiene un plan activo");
                }
                break;
            case 4:
                int entrenadorIdPlanes = lectura.leerInt("ID del entrenador");
                gestionPlanes.mostrarPlanesEntrenador(entrenadorIdPlanes);
                break;
            case 5:
                int planIdModificar = lectura.leerInt("ID del plan a modificar");
                gestionPlanes.modificarPlan(planIdModificar);
                break;
            case 6:
                int planId = lectura.leerInt("ID del plan");
                gestionPlanes.agregarEjercicioAPlan(planId);
                break;
            case 7:
                int planIdEjercicio = lectura.leerInt("ID del plan");
                gestionPlanes.eliminarEjercicioDePlan(planIdEjercicio);
                break;
            case 8:
                int planIdDesactivar = lectura.leerInt("ID del plan a desactivar");
                gestionPlanes.desactivarPlan(planIdDesactivar);
                break;
            case 9:
                int planIdEliminar = lectura.leerInt("ID del plan a eliminar");
                gestionPlanes.eliminarPlan(planIdEliminar);
                break;
        }
    }

    private void menuPagosYEstados() {
        System.out.println("\n=== GESTIÓN DE PAGOS Y ESTADOS DE CUENTA ===");
        System.out.println("1.  Registrar pago de mensualidad");
        System.out.println("2.️  Ver todos los pagos");
        System.out.println("3.️  Eliminar pago");
        System.out.println("4.  Ver estado de cuenta de cliente");
        System.out.println("5.  Verificar acceso al gimnasio");
        System.out.println("6.  Ver historial de pagos de cliente");
        System.out.println("7.  Clientes con acceso por vencer");
        System.out.println("8.  Clientes sin acceso");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 8);

        switch (opcion) {
            case 1:
                int clienteId = lectura.leerInt("ID del cliente");
                int cajeroId = lectura.leerInt("ID del cajero");
                gestionPagos.registrarPago(clienteId, cajeroId);
                break;
            case 2:
                gestionPagos.mostrarTodosLosPagos();
                break;
            case 3:
                int idPagoEliminar = lectura.leerInt("ID del pago a eliminar");
                gestionPagos.eliminarPago(idPagoEliminar);
                break;
            case 4:
                int clienteIdEstado = lectura.leerInt("ID del cliente");
                gestionEstado.mostrarEstadoCuenta(clienteIdEstado);
                break;
            case 5:
                int clienteIdAcceso = lectura.leerInt("ID del cliente");
                boolean tieneAcceso = gestionEstado.verificarAccesoGimnasio(clienteIdAcceso);
                System.out.println("Cliente " + clienteIdAcceso
                        + (tieneAcceso ? "  TIENE ACCESO" : "  NO TIENE ACCESO") + " al gimnasio");
                break;
            case 6:
                int clienteIdHistorial = lectura.leerInt("ID del cliente");
                gestionPagos.mostrarHistorialPagosCliente(clienteIdHistorial);
                break;
            case 7:
                gestionEstado.mostrarClientesConAccesoPorVencer();
                break;
            case 8:
                gestionEstado.mostrarClientesSinAcceso();
                break;
        }
    }

    private void menuArqueoCaja() {
        System.out.println("\n=== ARQUEO DE CAJA ===");
        System.out.println("1.  Generar arqueo diario por cajero");
        System.out.println("2.  Reporte consolidado del día");
        System.out.println("3.  Comparar arqueo con efectivo físico");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 3);

        switch (opcion) {
            case 1:
                gestionArqueo.generarReporteDiario();
                break;
            case 2:
                gestionArqueo.generarReporteConsolidado();
                break;
            case 3:
                gestionArqueo.compararArqueoConEfectivo();
                break;
        }
    }

    private void menuProductosServicios() {
        System.out.println("\n=== GESTIÓN DE PRODUCTOS Y SERVICIOS ===");
        System.out.println("1.  Registrar nuevo producto");
        System.out.println("2.  Registrar nuevo servicio");
        System.out.println("0. ️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 2);

        switch (opcion) {
            case 1:
                gestionProductos.crearProducto();
                break;
            case 2:
                gestionServicios.crearServicio();
                break;
        }
    }

    private void menuEmpleados() {
        System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
        System.out.println("1.  Registrar nuevo empleado");
        System.out.println("2.️  Ver lista de entrenadores");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 2);

        switch (opcion) {
            case 1:
                gestionEmpleados.crearEmpleado();
                break;
            case 2:
                gestionEntrenadores.mostrarTodosLosEntrenadores();
                break;
        }
    }

    private void menuReportes() {
        System.out.println("\n=== REPORTES ===");
        System.out.println("1.  Resumen de estados de cuenta");
        System.out.println("2.  Resumen de entrenadores");
        System.out.println("3.  Planes de entrenamiento vigentes");
        System.out.println("4.  Clientes sin acceso al gimnasio");
        System.out.println("5.  Recaudación del día");
        System.out.println("6.  Estadísticas generales");
        System.out.println("0.️  Volver al menú principal");

        int opcion = lectura.leerOpcionMenu("Seleccione una opción", 0, 6);

        switch (opcion) {
            case 1:
                mostrarResumenEstadosCuenta();
                break;
            case 2:
                gestionEntrenadores.mostrarTodosLosEntrenadores();
                break;
            case 3:
                mostrarPlanesVigentes();
                break;
            case 4:
                gestionEstado.mostrarClientesSinAcceso();
                break;
            case 5:
                gestionArqueo.generarReporteConsolidado();
                break;
            case 6:
                mostrarEstadisticasGenerales();
                break;
        }
    }

    private void mostrarResumenEstadosCuenta() {
        System.out.println("=== RESUMEN DE ESTADOS DE CUENTA ===");
        var estados = gestionEstado.getTodosLosEstados();

        if (estados.isEmpty()) {
            System.out.println("No hay estados de cuenta registrados");
            return;
        }

        int clientesActivos = gestionEstado.contarClientesActivos();
        int clientesInactivos = gestionEstado.contarClientesInactivos();

        System.out.println(" Estadísticas de clientes:");
        System.out.println(" Clientes con acceso activo: " + clientesActivos);
        System.out.println(" Clientes sin acceso: " + clientesInactivos);
        System.out.println(" Total de clientes: " + estados.size());

        System.out.println("\n Alertas:");
        gestionEstado.mostrarClientesConAccesoPorVencer();

        if (clientesInactivos > 0) {
            System.out.println("\n Clientes sin acceso:");
            gestionEstado.mostrarClientesSinAcceso();
        }
    }

    private void mostrarPlanesVigentes() {
        System.out.println("=== PLANES DE ENTRENAMIENTO VIGENTES ===");
        List<PlanEntrenamiento> planesVigentes = gestionPlanes.obtenerPlanesVigentes();

        if (planesVigentes.isEmpty()) {
            System.out.println("No hay planes de entrenamiento vigentes");
            return;
        }

        System.out.println("Total de planes vigentes: " + planesVigentes.size());
        System.out.println();

        for (PlanEntrenamiento plan : planesVigentes) {
            System.out.println(" ️ " + plan);
            System.out.println("   Ejercicios: " + plan.getEjercicios().size());
            System.out.println("   Días restantes: " + plan.getDiasRestantes());
        }
    }

    private void mostrarEstadisticasGenerales() {
        System.out.println("=== ESTADÍSTICAS GENERALES ===");

        // Estadísticas de clientes
        var estados = gestionEstado.getTodosLosEstados();
        int clientesActivos = gestionEstado.contarClientesActivos();
        int clientesInactivos = gestionEstado.contarClientesInactivos();

        System.out.println(" CLIENTES:");
        System.out.println(" Total registrados: " + gestionClientes.getClientes().size());
        System.out.println(" Con acceso activo: " + clientesActivos);
        System.out.println(" Sin acceso: " + clientesInactivos);

        // Estadísticas de entrenadores
        List<Entrenador> entrenadores = gestionEntrenadores.getEntrenadores();
        System.out.println("\n ENTRENADORES:");
        System.out.println(" Total registrados: " + entrenadores.size());

        int totalClientesAsignados = 0;
        for (Entrenador entrenador : entrenadores) {
            totalClientesAsignados += entrenador.getCantidadClientes();
        }
        System.out.println("- Clientes asignados: " + totalClientesAsignados);

        // Estadísticas de planes
        List<PlanEntrenamiento> planes = gestionPlanes.getPlanes();
        List<PlanEntrenamiento> planesVigentes = gestionPlanes.obtenerPlanesVigentes();

        System.out.println("\n PLANES DE ENTRENAMIENTO:");
        System.out.println(" Total creados: " + planes.size());
        System.out.println(" Vigentes: " + planesVigentes.size());
        System.out.println(" Finalizados: " + (planes.size() - planesVigentes.size()));

        // Estadísticas de pagos del día
        var pagosHoy = gestionPagos.obtenerPagosDelDia();
        float recaudacionHoy = gestionPagos.calcularTotalRecaudadoDia(java.time.LocalDate.now());

        System.out.println("\n RECAUDACIÓN HOY:");
        System.out.println(" Transacciones: " + pagosHoy.size());
        System.out.println(" Total recaudado: $" + String.format("%.2f", recaudacionHoy));
        if (!pagosHoy.isEmpty()) {
            System.out.println(" Promedio por transacción: $"
                    + String.format("%.2f", recaudacionHoy / pagosHoy.size()));
        }
    }

    // Método main para ejecutar el sistema
    public static void main(String[] args) {
        System.out.println("️ ¡Bienvenido al Sistema de Gestión de Gimnasio COMPLETO! ️");
        System.out.println(" Versión con funciones de CREAR, LEER, ACTUALIZAR y ELIMINAR");
        System.out.println("Desarrollado para gestionar clientes, entrenamientos y pagos");
        System.out.println("Iniciando sistema...\n");

        try {
            MenuPrincipal menu = new MenuPrincipal();
            menu.mostrarMenuPrincipal();
        } catch (Exception e) {
            System.err.println(" Error en el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}