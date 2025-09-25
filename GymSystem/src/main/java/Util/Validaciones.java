
package Util;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 *
 * @author Santi
 */
public class Validaciones {
    
    public static boolean validarIdentificacion(int identificacion) {
        return identificacion > 0 && String.valueOf(identificacion).length() >= 6;
    }
    
    public static boolean validarTelefono(int telefono) {
        String tel = String.valueOf(telefono);
        return tel.length() >= 7 && tel.length() <= 10;
    }
    
    public static boolean validarEstrato(int estrato) {
        return estrato >= 1 && estrato <= 6;
    }
    
    public static boolean validarPeso(float peso) {
        return peso > 0 && peso <= 300; // Rango razonable en kg
    }
    
    public static boolean validarAltura(float altura) {
        return altura > 0.5 && altura <= 2.5; // Rango razonable en metros
    }
    
    public static boolean validarMonto(float monto) {
        return monto > 0 && monto <= 1000000; // Límite razonable
    }
    
    public static boolean validarFecha(LocalDate fecha) {
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }
    
    public static String limpiarTexto(String texto) {
        if (texto == null) return "";
        return texto.trim().replaceAll("\\s+", " ");
    }
    
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 2) return false;
        // Solo letras, espacios y algunos caracteres especiales
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean validarDireccion(String direccion) {
        return direccion != null && direccion.trim().length() >= 5;
    }

    public static boolean validarCodigoProducto(int codigo) {
        return codigo > 0 && codigo <= 999999;
    }

    public static boolean validarMetodoPago(String metodo) {
        if (metodo == null) return false;
        String[] metodosValidos = {"EFECTIVO", "TARJETA", "CHEQUE", "TRANSFERENCIA"};
        for (String valido : metodosValidos) {
            if (valido.equals(metodo.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean validarNivelActividad(String nivel) {
        if (nivel == null) return false;
        String[] nivelesValidos = {"PRINCIPIANTE", "INTERMEDIO", "AVANZADO"};
        for (String valido : nivelesValidos) {
            if (valido.equals(nivel.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String generarMensajeError(String campo, String razon) {
        return "❌ Error en " + campo + ": " + razon;
    }

    public static boolean validarRango(int valor, int min, int max) {
        return valor >= min && valor <= max;
    }

    public static boolean validarRango(float valor, float min, float max) {
        return valor >= min && valor <= max;
    }

    public static boolean validarNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}