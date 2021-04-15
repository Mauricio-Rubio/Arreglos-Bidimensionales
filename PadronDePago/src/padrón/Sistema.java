package padrón;

import java.util.Scanner;

public class Sistema {
    Padron usuario;
    Scanner Ssc = new Scanner (System.in);
    Funciones.FuncionesStatic funciones;
    public void inciciar(){
        System.out.println("Ingrese el nombre del padron");
        String nombre = Ssc.nextLine();
        System.out.println("Ingrese su contraseña");
        String ps1 = Ssc.nextLine();
        usuario = new Padron(nombre, ps1);
        usuario.setId(String.valueOf(funciones.generarID(usuario)));
        System.out.println(usuario);
    }
    
}
