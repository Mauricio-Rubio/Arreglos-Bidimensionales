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

    public static String calMes(String nacimiento) {
        String res = nacimiento.substring(2, 4);
        return res;
    }

    public static String calDias(String nacimiento) {
        String res = nacimiento.substring(0, 2);
        return res;
    }

    public static String calAño(String nacimiento) {
        String res = nacimiento.substring(4, 8);
        return res;
    }

    public static String calEdad(String año) {
        int res = Integer.parseInt(año);
        res = 2021 - res;
        String Sres = String.valueOf(res);
        return Sres;
    }

    public static String calRFC(String nombre, String ApellidoP, String ApellidoM, String año, String mes, String dia) {
        String res = ApellidoP.toUpperCase().substring(0, 2) + ApellidoM.substring(0, 1) + nombre.substring(0, 1) + año.substring(2, 4) + mes + dia;
        return res;
    }
    
    public static String tipoPersona(String edad){
        int res = Integer.valueOf(edad);
        String Sres = "";
        if(res >= 60){
            Sres = "Adulto Mayor";
        }else if(res > 21 && res < 60){
            Sres = "Adulto";
        }else{
            Sres = "Estudiante";
        }
        return Sres;
    }

}
