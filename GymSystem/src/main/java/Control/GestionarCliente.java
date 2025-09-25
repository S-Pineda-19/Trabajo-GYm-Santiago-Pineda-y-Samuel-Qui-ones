/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Util.Lectura;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionarCliente {
    private int identificación;
    private String nombre;
    private String dirección;
    private int telefono;
    private int estratoSE;

    private float peso;
    private float altura;
    private String nivelActividad;
    private String objetivoFitness;
    private String lesionesAnteriores;
    private boolean practicaActividadFisica;
    private String actividadFisica;
    private int cantidadAFMinutos;
    private LocalDate fechaRegistro;

    private static List<GestionarCliente> clientes = new ArrayList<>();
    private static Lectura lectura = new Lectura();

    public GestionarCliente() {
        this.fechaRegistro = LocalDate.now();
    }

  

    public float calcularIMC() {
        if (altura > 0) {
            return peso / (altura * altura);
        }
        return 0;
    }

    private static String interpretarIMC(float imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 25) return "Peso normal";
        if (imc < 30) return "Sobrepeso";
        return "Obesidad";
    }

    private static String obtenerNivelActividad(int opcion) {
        switch (opcion) {
            case 1: return "PRINCIPIANTE";
            case 2: return "INTERMEDIO";
            case 3: return "AVANZADO";
            default: return "PRINCIPIANTE";
        }
    }


    public static GestionarCliente crearCliente() {
        GestionarCliente cliente = new GestionarCliente();

        System.out.println("=== REGISTRO DE CLIENTE ===");
        cliente.setIdentificación(lectura.leerInt("Identificación"));
        cliente.setNombre(lectura.leerString("Nombre completo"));
        cliente.setDirección(lectura.leerString("Dirección"));
        cliente.setTelefono(lectura.leerInt("Teléfono"));
        cliente.setEstratoSE(lectura.leerInt("Estrato socioeconómico (1-6)"));

        System.out.println("\n--- DATOS FÍSICOS Y DE ACTIVIDAD ---");
        cliente.setPeso(lectura.leerFloat("Peso actual (kg)"));
        cliente.setAltura(lectura.leerFloat("Altura (metros, ej: 1.75)"));

        System.out.println("Nivel de actividad física:");
        System.out.println("1. PRINCIPIANTE");
        System.out.println("2. INTERMEDIO");
        System.out.println("3. AVANZADO");
        int nivelOpcion = lectura.leerOpcionMenu("Seleccione", 1, 3);
        cliente.setNivelActividad(obtenerNivelActividad(nivelOpcion));

        cliente.setObjetivoFitness(lectura.leerString("Objetivo fitness"));
        cliente.setLesionesAnteriores(lectura.leerString("Lesiones anteriores o limitaciones físicas"));

        boolean realizaAF = lectura.leerSiNo("¿Realiza actividad física actualmente?");
        cliente.setPracticaActividadFisica(realizaAF);

        if (cliente.getPracticaActividadFisica()) {
            cliente.setActividadFisica(lectura.leerString("¿Qué tipo de actividad física realiza?"));
            cliente.setCantidadAFMinutos(lectura.leerInt("¿Cuántos minutos por semana?"));
        }

        float imc = cliente.calcularIMC();
        if (imc > 0) {
            System.out.println("IMC calculado: " + String.format("%.2f", imc));
            System.out.println("Interpretación: " + interpretarIMC(imc));
        }

        clientes.add(cliente);
        System.out.println("Cliente registrado exitosamente");

        return cliente;
    }

    public static void mostrarPerfilCompleto(GestionarCliente cliente) {
        System.out.println("=== PERFIL COMPLETO DEL CLIENTE ===");
        System.out.println("ID: " + cliente.getIdentificación());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("Dirección: " + cliente.getDirección());
        System.out.println("Estrato: " + cliente.getEstratoSE());

        System.out.println("\n--- DATOS FÍSICOS ---");
        System.out.println("Peso: " + cliente.getPeso() + " kg");
        System.out.println("Altura: " + cliente.getAltura() + " m");
        System.out.println("IMC: " + String.format("%.2f", cliente.calcularIMC()));

        System.out.println("\n--- ACTIVIDAD FÍSICA ---");
        System.out.println("Nivel: " + cliente.getNivelActividad());
        System.out.println("Objetivo: " + cliente.getObjetivoFitness());
        System.out.println("Lesiones/Limitaciones: " + cliente.getLesionesAnteriores());
        System.out.println("Practica AF: " + (cliente.getPracticaActividadFisica() ? "Sí" : "No"));

        if (cliente.getPracticaActividadFisica()) {
            System.out.println("Tipo de AF: " + cliente.getActividadFisica());
            System.out.println("Minutos/semana: " + cliente.getCantidadAFMinutos());
        }

        System.out.println("Fecha de registro: " + cliente.getFechaRegistro());
    }

    public static void mostrarTodosLosClientes() {
        System.out.println("=== LISTA DE CLIENTES ===");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        System.out.printf("%-5s %-20s %-8s %-6s %-12s%n",
                "ID", "Nombre", "IMC", "Peso", "Nivel");
        System.out.println("-".repeat(55));

        for (GestionarCliente cliente : clientes) {
            System.out.printf("%-5d %-20s %-8.2f %-6.1f %-12s%n",
                    cliente.getIdentificación(),
                    cliente.getNombre().length() > 20 ? cliente.getNombre().substring(0, 17) + "..." : cliente.getNombre(),
                    cliente.calcularIMC(),
                    cliente.getPeso(),
                    cliente.getNivelActividad()
            );
        }
        System.out.println("\nTotal de clientes: " + clientes.size());
    }

    public static GestionarCliente buscarClientePorId(int clienteId) {
        for (GestionarCliente cliente : clientes) {
            if (cliente.getIdentificación() == clienteId) {
                return cliente;
            }
        }
        return null;
    }

    public static boolean eliminarCliente(int clienteId) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdentificación() == clienteId) {
                GestionarCliente clienteEliminado = clientes.remove(i);
                System.out.println("Cliente eliminado: " + clienteEliminado.getNombre());
                return true;
            }
        }
        System.out.println("Cliente no encontrado con ID: " + clienteId);
        return false;
    }

    public static boolean modificarCliente(int clienteId) {
        GestionarCliente cliente = buscarClientePorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return false;
        }

        System.out.println("=== MODIFICAR CLIENTE ===");
        System.out.println("Cliente actual: " + cliente.getNombre());

        boolean continuar = lectura.leerSiNo("¿Desea continuar con la modificación?");
        if (!continuar) return false;

        System.out.println("Ingrese los nuevos datos (Enter para mantener actual):");

        String nuevoNombre = lectura.leerString("Nombre [" + cliente.getNombre() + "]");
        if (!nuevoNombre.trim().isEmpty()) {
            cliente.setNombre(nuevoNombre);
        }

        boolean actualizarPeso = lectura.leerSiNo("¿Actualizar peso actual: " + cliente.getPeso() + " kg?");
        if (actualizarPeso) {
            cliente.setPeso(lectura.leerFloat("Nuevo peso (kg)"));
        }

        boolean actualizarObjetivo = lectura.leerSiNo("¿Actualizar objetivo fitness?");
        if (actualizarObjetivo) {
            cliente.setObjetivoFitness(lectura.leerString("Nuevo objetivo fitness"));
        }

        System.out.println("Cliente modificado exitosamente");
        System.out.println("Nuevo IMC: " + String.format("%.2f", cliente.calcularIMC()));
        return true;
    }


    public int getIdentificación() {
        return identificación;
    }

    public void setIdentificación(int identificación) {
        this.identificación = identificación;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEstratoSE() {
        return estratoSE;
    }

    public void setEstratoSE(int estratoSE) {
        this.estratoSE = estratoSE;
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

    public String getLesionesAnteriores() {
        return lesionesAnteriores;
    }

    public void setLesionesAnteriores(String lesionesAnteriores) {
        this.lesionesAnteriores = lesionesAnteriores;
    }

    public boolean getPracticaActividadFisica() {
        return practicaActividadFisica;
    }

    public void setPracticaActividadFisica(boolean practicaActividadFisica) {
        this.practicaActividadFisica = practicaActividadFisica;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public int getCantidadAFMinutos() {
        return cantidadAFMinutos;
    }

    public void setCantidadAFMinutos(int cantidadAFMinutos) {
        this.cantidadAFMinutos = cantidadAFMinutos;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public static List<GestionarCliente> getClientes() {
    return clientes;
}

public static int getTotalClientes() {
    return clientes.size();
}
}
