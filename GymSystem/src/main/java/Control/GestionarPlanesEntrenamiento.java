
package Control;

import Model.*;
import Util.Lectura;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Santi
 */
public class GestionarPlanesEntrenamiento {
    private Lectura lectura = new Lectura();
    private List<PlanEntrenamiento> planes;
    private int siguienteId;

    public GestionarPlanesEntrenamiento() {
        this.planes = new ArrayList<>();
        this.siguienteId = 1;
    }

    public PlanEntrenamiento crearPlanEntrenamiento(int clienteId, int entrenadorId) {
        System.out.println("=== CREACIÓN DE PLAN DE ENTRENAMIENTO ===");
        
        PlanEntrenamiento plan = new PlanEntrenamiento();
        plan.setIdPlan(siguienteId++);
        plan.setClienteId(clienteId);
        plan.setEntrenadorId(entrenadorId);
        
        plan.setObjetivo(lectura.leerString("Objetivo del plan (Ej: Pérdida de peso, Ganancia muscular, etc.)"));
        plan.setObservaciones(lectura.leerString("Observaciones especiales"));
        
        boolean agregarEjercicios = lectura.leerSiNo("¿Desea agregar ejercicios ahora?");
        
        if (agregarEjercicios) {
            agregarEjercicios(plan);
        }
        
        planes.add(plan);
        System.out.println("Plan creado exitosamente con ID: " + plan.getIdPlan());
        return plan;
    }

    private void agregarEjercicios(PlanEntrenamiento plan) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- AGREGAR EJERCICIO ---");
            Ejercicio ejercicio = new Ejercicio();
            
            ejercicio.setNombre(lectura.leerString("Nombre del ejercicio"));
            ejercicio.setGrupoMuscular(lectura.leerString("Grupo muscular"));
            ejercicio.setSeries(lectura.leerInt("Número de series"));
            ejercicio.setRepeticiones(lectura.leerInt("Número de repeticiones"));
            ejercicio.setPeso(lectura.leerFloat("Peso (kg) - 0 si no aplica"));
            ejercicio.setTiempoDescanso(lectura.leerInt("Tiempo de descanso (segundos)"));
            ejercicio.setObservaciones(lectura.leerString("Observaciones del ejercicio"));
            
            plan.agregarEjercicio(ejercicio);
            System.out.println(" Ejercicio agregado: " + ejercicio.getNombre());
            
            continuar = lectura.leerSiNo("¿Agregar otro ejercicio?");
        }
    }

    public List<PlanEntrenamiento> obtenerPlanesDelEntrenador(int entrenadorId) {
        List<PlanEntrenamiento> planesEntrenador = new ArrayList<>();
        for (PlanEntrenamiento plan : planes) {
            if (plan.getEntrenadorId() == entrenadorId) {
                planesEntrenador.add(plan);
            }
        }
        return planesEntrenador;
    }

    public PlanEntrenamiento obtenerPlanDelCliente(int clienteId) {
        for (PlanEntrenamiento plan : planes) {
            if (plan.getClienteId() == clienteId && plan.isActivo()) {
                return plan;
            }
        }
        return null;
    }

    public void mostrarTodosLosPlanes() {
        System.out.println("=== TODOS LOS PLANES DE ENTRENAMIENTO ===");
        if (planes.isEmpty()) {
            System.out.println("No hay planes registrados.");
            return;
        }
        
        for (PlanEntrenamiento plan : planes) {
            System.out.println("\n" + plan);
            System.out.println("Ejercicios incluidos:");
            if (plan.getEjercicios().isEmpty()) {
                System.out.println("  - No hay ejercicios asignados");
            } else {
                for (Ejercicio ejercicio : plan.getEjercicios()) {
                    System.out.println("  - " + ejercicio);
                }
            }
        }
    }

    public void mostrarPlanesEntrenador(int entrenadorId) {
        System.out.println("=== PLANES DEL ENTRENADOR ID: " + entrenadorId + " ===");
        List<PlanEntrenamiento> planesEntrenador = obtenerPlanesDelEntrenador(entrenadorId);
        
        if (planesEntrenador.isEmpty()) {
            System.out.println("El entrenador no tiene planes asignados.");
            return;
        }
        
        for (PlanEntrenamiento plan : planesEntrenador) {
            System.out.println("\n" + plan);
            System.out.println("Ejercicios: " + plan.getEjercicios().size());
            System.out.println("Estado: " + (plan.estaVigente() ? "VIGENTE" : "VENCIDO"));
        }
    }

    public void agregarEjercicioAPlan(int planId) {
        PlanEntrenamiento plan = buscarPlanPorId(planId);
        if (plan == null) {
            System.out.println(" Plan no encontrado");
            return;
        }
        
        System.out.println("Agregando ejercicio al plan: " + plan.getObjetivo());
        Ejercicio ejercicio = new Ejercicio();
        
        ejercicio.setNombre(lectura.leerString("Nombre del ejercicio"));
        ejercicio.setGrupoMuscular(lectura.leerString("Grupo muscular"));
        ejercicio.setSeries(lectura.leerInt("Número de series"));
        ejercicio.setRepeticiones(lectura.leerInt("Número de repeticiones"));
        ejercicio.setPeso(lectura.leerFloat("Peso (kg) - 0 si no aplica"));
        ejercicio.setTiempoDescanso(lectura.leerInt("Tiempo de descanso (segundos)"));
        ejercicio.setObservaciones(lectura.leerString("Observaciones del ejercicio"));
        
        plan.agregarEjercicio(ejercicio);
        System.out.println(" Ejercicio agregado exitosamente");
    }

    public PlanEntrenamiento buscarPlanPorId(int planId) {
        for (PlanEntrenamiento plan : planes) {
            if (plan.getIdPlan() == planId) {
                return plan;
            }
        }
        return null;
    }

    public void desactivarPlan(int planId) {
        PlanEntrenamiento plan = buscarPlanPorId(planId);
        if (plan != null) {
            plan.setActivo(false);
            System.out.println(" Plan desactivado");
        } else {
            System.out.println(" Plan no encontrado");
        }
    }

    public List<PlanEntrenamiento> obtenerPlanesVigentes() {
        List<PlanEntrenamiento> vigentes = new ArrayList<>();
        for (PlanEntrenamiento plan : planes) {
            if (plan.estaVigente()) {
                vigentes.add(plan);
            }
        }
        return vigentes;
    }

    public List<PlanEntrenamiento> getPlanes() {
        return planes;
    }
    public boolean eliminarPlan(int planId) {
    PlanEntrenamiento plan = buscarPlanPorId(planId);
    if (plan == null) {
        System.out.println(" Plan no encontrado");
        return false;
    }
    
    System.out.println("=== ELIMINAR PLAN ===");
    System.out.println("Plan: " + plan.getObjetivo());
    System.out.println("Cliente ID: " + plan.getClienteId());
    System.out.println("Ejercicios: " + plan.getEjercicios().size());
    
    boolean confirmar = lectura.leerSiNo("¿Está seguro de eliminar este plan?");
    if (confirmar) {
        planes.remove(plan);
        System.out.println(" Plan eliminado exitosamente");
        return true;
    }
    return false;
}

public boolean eliminarEjercicioDePlan(int planId) {
    PlanEntrenamiento plan = buscarPlanPorId(planId);
    if (plan == null) {
        System.out.println(" Plan no encontrado");
        return false;
    }
    
    if (plan.getEjercicios().isEmpty()) {
        System.out.println("️ El plan no tiene ejercicios");
        return false;
    }
    
    System.out.println("=== EJERCICIOS DEL PLAN ===");
    List<Ejercicio> ejercicios = plan.getEjercicios();
    for (int i = 0; i < ejercicios.size(); i++) {
        System.out.println((i + 1) + ". " + ejercicios.get(i).getNombre());
    }
    
    int indice = lectura.leerOpcionMenu("Seleccione ejercicio a eliminar", 1, ejercicios.size()) - 1;
    Ejercicio ejercicioEliminado = ejercicios.remove(indice);
    
    System.out.println(" Ejercicio eliminado: " + ejercicioEliminado.getNombre());
    return true;
}

public boolean modificarPlan(int planId) {
    PlanEntrenamiento plan = buscarPlanPorId(planId);
    if (plan == null) {
        System.out.println(" Plan no encontrado");
        return false;
    }
    
    System.out.println("=== MODIFICAR PLAN ===");
    System.out.println("Plan actual: " + plan.getObjetivo());
    
    String nuevoObjetivo = lectura.leerString("Nuevo objetivo [" + plan.getObjetivo() + "]");
    if (!nuevoObjetivo.trim().isEmpty()) {
        plan.setObjetivo(nuevoObjetivo);
    }
    
    String nuevasObservaciones = lectura.leerString("Nuevas observaciones [" + plan.getObservaciones() + "]");
    if (!nuevasObservaciones.trim().isEmpty()) {
        plan.setObservaciones(nuevasObservaciones);
    }
    
    System.out.println(" Plan modificado exitosamente");
    return true;
}
}