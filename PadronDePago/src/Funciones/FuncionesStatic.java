package Funciones;

import java.util.ArrayList;
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

    public static String tipoPersona(String edad) {
        int res = Integer.valueOf(edad);
        String Sres = "";
        if (res >= 60) {
            Sres = "Adulto Mayor";
        } else if (res > 21 && res < 60) {
            Sres = "Adulto";
        } else {
            Sres = "Estudiante";
        }
        return Sres;
    }

    public static String calASCII(String nombre) {
        char Sres = nombre.toUpperCase().charAt(0);
        int res = Sres;
        String Ssres = String.valueOf(res);
        return Ssres;
    }

    public static String calLetra(String nombre) {
        char Sres = nombre.toUpperCase().charAt(0);
        String Ssres = " ";
        if (Sres != 'R') {
            Ssres = String.valueOf(Sres) + "*";
        } else {
            Ssres = String.valueOf(Sres);
        }
        return Ssres;
    }

    public static String calAyuda(String tipoEdad) {
        String res = "";
        if (tipoEdad.equals("Adulto Mayor")) {
            res = "Si";
        } else if(tipoEdad.equals("Estudiante")){
            res = "Si";
        }else{
            res = "No";
        }
        return res;
    }

    public static String calImporte(String nombre, String ayuda) {
        String res = "";
        if (nombre.charAt(0) == 'R' || nombre.charAt(0) == 'r') {
            res = "0";
        } else if(ayuda == "Si"){
            res = "4000";
        }else{
            res = "500";
        }
        return res;
    }
    
    public static String [ ] [ ] conArrlNombre(ArrayList<ArrayList<String>> arrPrincipal){
        String [ ] [ ] res = new String[arrPrincipal.size()][7];
        for(int i = 0; i<arrPrincipal.size(); i++){
            res [i][0] = arrPrincipal.get(i).get(0);
            res [i][1] = arrPrincipal.get(i).get(1);
            res [i][2] = arrPrincipal.get(i).get(2);
            res [i][3] = arrPrincipal.get(i).get(3);
            res [i][4] = arrPrincipal.get(i).get(4);
            res [i][5] = arrPrincipal.get(i).get(5);
            res [i][6] = arrPrincipal.get(i).get(6);
        }
        return res;
    }
    public static String [ ] [ ] conArrlDatos(ArrayList<ArrayList<String>> arrPrincipal){
        String [ ] [ ] res = new String[arrPrincipal.size()][4];
        for(int i = 0; i<arrPrincipal.size(); i++){
            res [i][0] = arrPrincipal.get(i).get(7);
            res [i][1] = arrPrincipal.get(i).get(8);
            res [i][2] = arrPrincipal.get(i).get(9);
            res [i][3] = arrPrincipal.get(i).get(10);
        }
        return res;
    }
    public static String [ ] [ ] conArrlAddiciones(ArrayList<ArrayList<String>> arrPrincipal){
        String [ ] [ ] res = new String[arrPrincipal.size()][3];
        for(int i = 0; i<arrPrincipal.size(); i++){
            res [i][0] = arrPrincipal.get(i).get(11);
            res [i][1] = arrPrincipal.get(i).get(12);
            res [i][2] = arrPrincipal.get(i).get(13);
        }
        return res;
    }
    

}
