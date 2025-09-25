/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.*;

/**
 *
 * @author Santi
 */
public class Lectura {
    private Scanner entrada = new Scanner(System.in);
    
    public int leerInt(String mensaje){
        System.out.print(mensaje + ": ");
        while (!entrada.hasNextInt()) {
            System.out.print("Error: Ingrese un número válido. " + mensaje + ": ");
            entrada.next();
        }
        int valor = entrada.nextInt();
        entrada.nextLine();
        return valor;
    }

    public String leerString(String mensaje){
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }
    
    public float leerFloat(String mensaje){
        System.out.print(mensaje + ": ");
        while (!entrada.hasNextFloat()) {
            System.out.print("Error: Ingrese un número decimal válido. " + mensaje + ": ");
            entrada.next();
        }
        float valor = entrada.nextFloat();
        entrada.nextLine();
        return valor;
    }
    
    public Boolean leerBoolean(String mensaje){
        System.out.print(mensaje + " (true/false): ");
        while (!entrada.hasNextBoolean()) {
            System.out.print("Error: Ingrese true o false. " + mensaje + ": ");
            entrada.next();
        }
        boolean valor = entrada.nextBoolean();
        entrada.nextLine();
        return valor;
    }

    public boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerString(mensaje + " (S/N)").toUpperCase();
            if (respuesta.equals("S") || respuesta.equals("SI")) {
                return true;
            } else if (respuesta.equals("N") || respuesta.equals("NO")) {
                return false;
            } else {
                System.out.println("Error: Ingrese S (Sí) o N (No)");
            }
        }
    }

    public int leerOpcionMenu(String mensaje, int minOpcion, int maxOpcion) {
        int opcion;
        do {
            opcion = leerInt(mensaje);
            if (opcion < minOpcion || opcion > maxOpcion) {
                System.out.println("Error: Seleccione una opción entre " + minOpcion + " y " + maxOpcion);
            }
        } while (opcion < minOpcion || opcion > maxOpcion);
        return opcion;
    }

    public void pausar(String mensaje) {
        System.out.println(mensaje);
        System.out.print("Presione Enter para continuar...");
        entrada.nextLine();
    }

    public void cerrar() {
        entrada.close();
    }
}