package Funciones;

import java.util.Random;
import padrón.*;

public class FuncionesStatic {

    public static String generarID(Padron padron) {
        Random rnd = new Random();
        int idAleatorio = (int) (rnd.nextDouble() * 100 + 100);
        String nombre = String.valueOf(padron.getNombre().charAt(0));
        String psw = String.valueOf(padron.getPassword().charAt(0));
        String idTemp = (nombre.toUpperCase() + psw + String.valueOf(idAleatorio));
        System.out.println(padron.getPassword().charAt(0));
        return String.valueOf(idTemp);
    }

    public static boolean validarPsRegistro(String c1, String c2) {
        if (c1.equals(c2)) {
            return true;
        } else {
            return false;
        }
    }
}
