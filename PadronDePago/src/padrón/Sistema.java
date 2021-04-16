package padrón;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import sun.security.util.Length;

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
            //System.out.println(usuario);
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
            System.out.println("Ingresa tu fecha de nacimiento en formato ddmmaaaa");
            personasTemp[i][6] = Ssc.nextLine();
            personas.add(new Persona(personasTemp[i][0], personasTemp[i][1], personasTemp[i][2], personasTemp[i][6]));
            personasTemp[i][3] = funciones.calMes(personas.get(i));

        }
        usuario.setNombres(personasTemp);
        BaseDatos(usuario);
        BaseDatosTablas(usuario);
        /*for(int j = 0; j <nombresTemp.length; j++){
            for(int x = 0; x<nombresTemp[j].length; x++){
                //System.out.printf(nombresTemp[j][x]+ " \n");
                System.out.println(personas.get(j)+ " ");
            }
        }*/
        for (int x = 0;
                x < personas.size();
                x++) {
            // System.out.println(personas.get(x) + " ");
        }
        

    }

    public void BaseDatos(Padron usuario) {
        File archivo; //manipula el archivo
        FileWriter escribir; // escribir en el archivo
        PrintWriter linea; // 
        archivo = new File("padron.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                linea.println(usuario.getId());
                linea.println(usuario.getNombre());
                linea.println(usuario.getPassword());//Esta cifrada al acceder al método getContraseña
                //System.out.println(usuario.getId());
                //System.out.println(contraseña2);
                //linea.println();
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                linea.println(usuario.getId());
                linea.println(usuario.getNombre());
                linea.println(usuario.getPassword());;
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

    public void BaseDatosTablas(Padron usuario) {
        File archivo; //manipula el archivo
        FileWriter escribir; // escribir en el archivo
        PrintWriter linea; // 
        archivo = new File("padronDatos.txt");
        String[][] personasTemp = usuario.getNombres();
        System.out.println("Arreglo: " + personasTemp[0][0]);
        if (!archivo.exists()) {
            n = 0;
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                //escribir en el archivo
                for (int i = 0; i < personasTemp.length; i++) {
                    linea.write(personasTemp[i][n]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 1]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 2]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 3]);
                    linea.write(",");
                }

                /*linea.write(usuario.getId());
                linea.write(",");
                linea.write(usuario.getNombre());
                linea.write(",");
                linea.write(usuario.getPassword());*///Esta cifrada al acceder al método getContraseña
                //linea.println();
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                for (int i = 0; i < personasTemp.length; i++) {
                    linea.write(personasTemp[i][n]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 1]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 2]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 3]);
                    linea.write(",");
                }
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

}
