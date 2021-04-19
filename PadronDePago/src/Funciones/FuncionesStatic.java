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
        return String.valueOf(idTemp);
    }

    public static boolean validarPsRegistro(String c1, String c2) {
        if (c1.equals(c2)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String calMes(String nacimiento){
        String res = nacimiento.substring(2, 4);
        return res;
    }
    public static String calDias(String nacimiento){
        String res = nacimiento.substring(0, 2);
        return res;
    }
    public static String calAño(String nacimiento){
        String res = nacimiento.substring(4, 8);
        return res;
    }
    
}
