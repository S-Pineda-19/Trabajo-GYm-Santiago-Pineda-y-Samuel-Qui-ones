
package Control;

import Model.*;
import Util.Lectura;
import java.util.*;

/**
 * @author Santi
 */
public class GestionarEntrenadores {
    private List<Entrenador> entrenadores;
    private Lectura lectura = new Lectura();
    private GestionarPlanesEntrenamiento gestionPlanes;

    public GestionarEntrenadores(GestionarPlanesEntrenamiento gestionPlanes) {
        this.entrenadores = new ArrayList<>();
        this.gestionPlanes = gestionPlanes;
    }

    public Entrenador crearEntrenador() {
        System.out.println("=== REGISTRO DE ENTRENADOR ===");
        
        Entrenador entrenador = new Entrenador();
        
        entrenador.setIdentificación(lectura.leerInt("Identificación"));
        entrenador.setNombre(lectura.leerString("Nombre completo"));
        entrenador.setDirección(lectura.leerString("Dirección"));
        entrenador.setTelefono(lectura.leerInt("Teléfono"));
        entrenador.setAntiguedad(lectura.leerInt("Años de experiencia"));
        entrenador.setCargo("ENTRENADOR");
        entrenador.setSalario(lectura.leerInt("Salario"));
        
        entrenador.setEspecialidad(lectura.leerString("Especialidad (Ej: Fuerza, Cardio, Funcional)"));
        entrenador.setCertificaciones(lectura.leerString("Certificaciones"));
        
        entrenadores.add(entrenador);
        System.out.println("Entrenador registrado exitosamente");
        
        return entrenador;
    }

    public void asignarClienteAEntrenador(int entrenadorId, int clienteId) {
        Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
        if (entrenador != null) {
            entrenador.asignarCliente(clienteId);
            System.out.println("Cliente " + clienteId + " asignado al entrenador " + entrenadorId);
        } else {
            System.out.println("Entrenador no encontrado");
        }
    }

    public Entrenador buscarEntrenadorPorId(int id) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getIdentificación() == id) {
                return entrenador;
            }
        }
        return null;
    }

    public void mostrarClientesDelEntrenador(int entrenadorId) {
        Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
        if (entrenador == null) {
            System.out.println("Entrenador no encontrado");
            return;
        }
        
        System.out.println("=== CLIENTES DEL ENTRENADOR: " + entrenador.getNombre() + " ===");
        List<Integer> clientes = entrenador.getClientesAsignados();
        
        if (clientes.isEmpty()) {
            System.out.println("No tiene clientes asignados");
        } else {
            System.out.println("Clientes asignados (" + clientes.size() + "):");
            for (Integer clienteId : clientes) {
                System.out.println("- Cliente ID: " + clienteId);
            }
        }
    }

    public void mostrarPlanesDelEntrenador(int entrenadorId) {
        if (gestionPlanes != null) {
            gestionPlanes.mostrarPlanesEntrenador(entrenadorId);
        } else {
            System.out.println("Sistema de planes no disponible");
        }
    }

    public void crearPlanParaCliente(int entrenadorId, int clienteId) {
        Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
        if (entrenador == null) {
            System.out.println("Entrenador no encontrado");
            return;
        }
        
        if (!entrenador.getClientesAsignados().contains(clienteId)) {
            boolean asignar = lectura.leerSiNo("El cliente no está asignado a este entrenador. ¿Desea asignarlo?");
            if (asignar) {
                asignarClienteAEntrenador(entrenadorId, clienteId);
            } else {
                return;
            }
        }
        
        if (gestionPlanes != null) {
            gestionPlanes.crearPlanEntrenamiento(clienteId, entrenadorId);
        } else {
            System.out.println("Sistema de planes no disponible");
        }
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void mostrarTodosLosEntrenadores() {
        System.out.println("=== LISTA DE ENTRENADORES ===");
        if (entrenadores.isEmpty()) {
            System.out.println("No hay entrenadores registrados");
            return;
        }
        
        for (Entrenador entrenador : entrenadores) {
            System.out.println("\n" + entrenador);
            System.out.println("Especialidad: " + entrenador.getEspecialidad());
            System.out.println("Certificaciones: " + entrenador.getCertificaciones());
            System.out.println("Clientes asignados: " + entrenador.getClientesAsignados().size());
        }
    }

    public void removerClienteDeEntrenador(int entrenadorId, int clienteId) {
        Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
        if (entrenador != null) {
            entrenador.removerCliente(clienteId);
            System.out.println("Cliente " + clienteId + " removido del entrenador " + entrenadorId);
        } else {
            System.out.println("Entrenador no encontrado");
        }
    }

    public List<Entrenador> buscarEntrenadoresPorEspecialidad(String especialidad) {
        List<Entrenador> resultado = new ArrayList<>();
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getEspecialidad() != null && 
                entrenador.getEspecialidad().toLowerCase().contains(especialidad.toLowerCase())) {
                resultado.add(entrenador);
            }
        }
        return resultado;
    }
    public void eliminarEntrenador(int entrenadorId) {
    Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
    if (entrenador == null) {
        System.out.println(" Entrenador no encontrado");
        return;
    }
    
    System.out.println("=== ELIMINAR ENTRENADOR ===");
    System.out.println("Entrenador: " + entrenador.getNombre());
    
    // Verificar si tiene clientes asignados
    if (!entrenador.getClientesAsignados().isEmpty()) {
        System.out.println("️ El entrenador tiene " + entrenador.getCantidadClientes() + " clientes asignados");
        boolean forzar = lectura.leerSiNo("¿Desea eliminar de todas formas?");
        if (!forzar) {
            return;
        }
    }
    
    boolean confirmar = lectura.leerSiNo("¿Está seguro de eliminar este entrenador?");
    if (confirmar) {
        entrenadores.remove(entrenador);
        System.out.println(" Entrenador eliminado: " + entrenador.getNombre());
    }
}

public boolean modificarEntrenador(int entrenadorId) {
    Entrenador entrenador = buscarEntrenadorPorId(entrenadorId);
    if (entrenador == null) {
        System.out.println(" Entrenador no encontrado");
        return false;
    }
    
    System.out.println("=== MODIFICAR ENTRENADOR ===");
    System.out.println("Entrenador: " + entrenador.getNombre());
    
    boolean actualizar = lectura.leerSiNo("¿Desea actualizar los datos?");
    if (!actualizar) return false;
    
    String nuevaEspecialidad = lectura.leerString("Nueva especialidad [" + entrenador.getEspecialidad() + "]");
    if (!nuevaEspecialidad.trim().isEmpty()) {
        entrenador.setEspecialidad(nuevaEspecialidad);
    }
    
    String nuevasCertificaciones = lectura.leerString("Nuevas certificaciones [" + entrenador.getCertificaciones() + "]");
    if (!nuevasCertificaciones.trim().isEmpty()) {
        entrenador.setCertificaciones(nuevasCertificaciones);
    }
    
    System.out.println(" Entrenador modificado exitosamente");
    return true;
}
}