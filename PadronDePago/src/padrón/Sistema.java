package padrón;

import Funciones.FuncionesStatic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Length;

public class Sistema {

    Padron usuario;
    ArrayList<Persona> personas = new ArrayList<>();
    Scanner Ssc = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    Funciones.FuncionesStatic funciones;
    String[] arregloGenerado;
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
                    login();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ingresa alguna opcion");
            }

        } while (eleccion != 3);

    }

    public void login() {
        int i = 0;
        do {
            Padron usuarioLogin;
            usuarioLogin = datosLogin();
            if (usuarioLogin != null && generarUser(usuarioLogin.getId())) {
                System.out.println("Bienvenido al padron: " + usuarioLogin.getNombre());
                for (int y = 0; y < arregloGenerado.length; y++) {
                    System.out.println("------>" + arregloGenerado[y]);
                }
                opcionesPadron(usuarioLogin);
                break;
            } else {
                System.out.println("Error en login, revisa tus datos");
            }
            i++;
        } while (i < 3);
    }

    public Padron datosLogin() {
        String contraseñaUser, idUser;
        System.out.println("Ingresa tu id");
        idUser = Ssc.nextLine();
        System.out.println("Ingresa tu contraseña");
        contraseñaUser = Ssc.nextLine();
        if (buscarUser(idUser, contraseñaUser)) {
            return usuario;
        }
        return null;
    }

    public void opcionesPadron(Padron user) {
        eleccion = 0;
        System.out.println("1.- Mostrar");
        System.out.println("2.- Agregar");
        System.out.println("3.- Corregir");
        System.out.println("4.- Salir");
        eleccion = sc.nextInt();
        switch (eleccion) {
            case 1:
                mostrar();
                break;
            case 2:
                login();
                break;
            case 3:
                break;
            case 4:
                System.out.println("Usuario saliendo");
                break;
            default:
                System.out.println("Ingresa alguna opcion");
        }
    }

    public void mostrar() {
        System.out.println("Mostrando resultados");
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
        opcionesPadron(usuario);

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
            personasTemp[i][3] = FuncionesStatic.calMes(personasTemp[i][6]);
            personasTemp[i][4] = FuncionesStatic.calDias(personasTemp[i][6]);
            personasTemp[i][5] = FuncionesStatic.calAño(personasTemp[i][6]);
            datosPersonalesTemp[i][0] = funciones.calRFC(personasTemp[i][0], personasTemp[i][1], personasTemp[i][2], personasTemp[i][5], personasTemp[i][3], personasTemp[i][4]);
            datosPersonalesTemp[i][1] = funciones.calEdad(personasTemp[i][5]);
            datosPersonalesTemp[i][2] = funciones.tipoPersona(datosPersonalesTemp[i][1]);
            datosPersonalesTemp[i][3] = funciones.calASCII(personasTemp[i][0]);
            adicionesTemp[i][0] = funciones.calLetra(personasTemp[i][0]);
            adicionesTemp[i][1] = funciones.calAyuda(datosPersonalesTemp[i][2]);
            adicionesTemp[i][2] = funciones.calImporte(personasTemp[i][0], adicionesTemp[i][1]);
            personas.add(new Persona(personasTemp[i][0], personasTemp[i][1], personasTemp[i][2], personasTemp[i][6], personasTemp[i][3], personasTemp[i][4], personasTemp[i][5], datosPersonalesTemp[i][0]));

            System.out.println(adicionesTemp[i][1]);
            System.out.println(adicionesTemp[i][2]);

        }
        usuario.setNombres(personasTemp);
        BaseDatos(usuario);
        BaseDatosTablas(usuario);
        System.out.println("ID: " + usuario.getId());
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

    public boolean buscarUser(String idUser, String contraseñaUser) {
        Cifrador cifrado = new Cifrador();
        String contraseñaCifrada = cifrado.cifrar(contraseñaUser);
        File archivo;  //manipular un archivo
        FileReader leer; //lector
        String cadena, idUsuario = "", contraseña = "", nombre = "";
        BufferedReader almacenamiento;
        archivo = new File("padron.txt");
        try {
            leer = new FileReader(archivo);
            almacenamiento = new BufferedReader(leer);
            cadena = "";
            //nombre = null;
            do {
                try {
                    cadena = almacenamiento.readLine();
                    idUsuario = cadena;
                    cadena = almacenamiento.readLine();
                    nombre = cadena;
                    cadena = almacenamiento.readLine();
                    contraseña = cadena;

                    if (cadena != null && contraseña.equals(contraseñaCifrada) && idUser.equals(idUsuario)) {
                        Padron userBusqueda = new Padron(nombre, contraseña, idUsuario);
                        leer.close();
                        usuario = userBusqueda;
                        return true;
                    }
                } catch (IOException ex) {
                    System.out.println("error encontrar" + ex);

                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (cadena != null || (idUser.equals(nombre) && contraseña.equals(contraseñaCifrada)));
            try {
                almacenamiento.close();
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario no existe");
        return false;
    }

    public boolean generarUser(String idUser) {
        File archivo;  //manipular un archivo
        FileReader leer; //lector
        String cadena, arrgloPrincipal = "", id = "";
        BufferedReader almacenamiento;
        archivo = new File("padronDatos.txt");
        try {
            leer = new FileReader(archivo);
            almacenamiento = new BufferedReader(leer);
            cadena = "";
            //nombre = null;
            do {
                try {
                    cadena = almacenamiento.readLine();
                    id = cadena;
                    cadena = almacenamiento.readLine();
                    arrgloPrincipal = cadena;
                    if (cadena != null && idUser.equals(id)) {
                        arregloGenerado = arrgloPrincipal.split(",");
                        leer.close();
                        return true;
                        // Padron userBusqueda = new Padron(nombre, contraseña, arrgloPrincipal);

                        // usuario = userBusqueda;
                    }
                } catch (IOException ex) {
                    System.out.println("error encontrar" + ex);

                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (cadena != null || (idUser.equals(arrgloPrincipal)));
            try {
                almacenamiento.close();
                leer.close();
            } catch (IOException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario no existe");
        return false;
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
                linea.println(usuario.getId());
                //linea.write(personasTemp[0][0]);
                //linea.write(",");
                for (int i = 0; i < personasTemp.length; i++) {
                    linea.write(personasTemp[i][n]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 1]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 2]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 3]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 4]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 5]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 6]);
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
                linea.println(" ");
                linea.println(usuario.getId());
                for (int i = 0; i < personasTemp.length; i++) {
                    linea.write(personasTemp[i][n]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 1]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 2]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 3]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 4]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 5]);
                    linea.write(",");
                    linea.write(personasTemp[i][n + 6]);
                    linea.write(",");
                }
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

}
