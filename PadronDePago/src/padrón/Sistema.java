package padrón;

import java.util.Scanner;

public class Sistema {

    Padron usuario;
    Scanner Ssc = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    Funciones.FuncionesStatic funciones;
    Cifrador cifrar = new Cifrador();
    int eleccion = 0;
    int n = 0;

    public void menu() {
        do {
            System.out.println(" ");
            System.out.println("SISTEMA DE REGISTRO DE PADRON");
            System.out.println("--------------------------------------");
            System.out.println("1.- Para crear un nuevo Padron");
            System.out.println("2.- Para ingresar a un Padron");
            System.out.println("3.- Salir");
            eleccion = sc.nextInt();
            switch (eleccion) {
                case 1:
                    crearPadron();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ingresa alguna opcion");
            }

        } while (eleccion != 3);

    }

    public void crearPadron() {
        System.out.println("Ingrese el nombre del padron");
        String nombre = Ssc.nextLine();
        System.out.println("Ingrese su contraseña");
        String ps1 = Ssc.nextLine();
        ps1 = cifrar.cifrar(ps1);
        System.out.println("Ingrese su contraseña");
        String ps2 = Ssc.nextLine();
        ps2 = cifrar.cifrar(ps2);
        if (funciones.validarPsRegistro(ps1, ps2)) {
            System.out.println("Contraseña coincide");
            usuario = new Padron(nombre, ps1);
            usuario.setId(String.valueOf(funciones.generarID(usuario)));
            System.out.println(usuario);
            completarPadron();
        } else {
            System.out.println("Revisa tu contraseña");
            n++;
            /**
             * Este segundo if, nos ayudará a controlar la recursividad de este
             * método, evitándonos crear un bucle.
             */
            if (n < 3) {
                crearPadron();
            } else {
                return;
            }
        }

    }

    public void completarPadron() {

    }

}
