package padrón;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    Padron usuario;
    ArrayList<Persona> personas = new ArrayList<>();
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
        System.out.println("Complete lo siguientes datos");
        System.out.println("Ingresa el numero de personas a capturar");
        int nPer = sc.nextInt();
        String[][] personasTemp = new String[nPer][7];
        String[][] datosPersonalesTemp = new String[nPer][4];
        String[][] adicionesTemp = new String[nPer][3];
        for (int i = 0; i < nPer; i++) {
            System.out.println("Ingrese el o los nombres de pila ");
            personasTemp[i][0] = Ssc.nextLine();
            System.out.println("Ingrese el apellido paterno");
            personasTemp[i][1] = Ssc.nextLine();
            System.out.println("Ingrese el apellido materno");
            personasTemp[i][2] = Ssc.nextLine();
            Persona userTemp;
            System.out.println("Ingresa tu fecha de nacimiento en formato ddmmaa");
            personasTemp [i][6] = Ssc.nextLine();
            personas.add(new Persona(personasTemp[i][0], personasTemp[i][1], personasTemp[i][2], personasTemp [i][6]));

        }
        /*for(int j = 0; j <nombresTemp.length; j++){
            for(int x = 0; x<nombresTemp[j].length; x++){
                //System.out.printf(nombresTemp[j][x]+ " \n");
                System.out.println(personas.get(j)+ " ");
            }
        }*/
        for (int x = 0; x < personas.size(); x++) {
            System.out.println(personas.get(x) + " ");
        }

    }

}
