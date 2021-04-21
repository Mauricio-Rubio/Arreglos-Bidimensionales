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

    Padron usuarioActivo;
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
                construirArreglos(arregloGenerado);
                opcionesPadron();
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
            return usuarioActivo;
        }
        return null;
    }

    public void opcionesPadron() {
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
                if(corregirPadron() == 'N'){
                    corregirPadron();
                }
                break;
            case 4:
                System.out.println("Usuario saliendo");
                break;
            default:
                System.out.println("Ingresa alguna opcion");
        }
    }

    public void mostrar() {
        System.out.printf("||===||============||============||================||=============================%16s=============================||=====||=====||=======|| \n", "DATOS PERSONALES");
        System.out.printf("||===||============||============||================||===||===||====||================||============||====||============||======||=====||=====||=======|| \n");
        System.out.printf("||===||  %9s || %9s  ||   %10s   ||%3s||%3s||%4s||%16S||     %3s    ||%4s||    %4s    ||%6s||%5s||%5s||%7s||\n", "A.Paterno", "A.Materno", "Nombre", "Mes", "Dia", "Año", "Fecha Nacimiento", "RFC", "Edad", "Tipo", "Código", "Letra", "Ayuda", "Importe");
        System.out.printf("||===||============||============||================||===||===||====||================||============||====||============||======||=====||=====||=======|| \n");
        String[][] personasTemp = usuarioActivo.getNombres();
        String[][] datosPersonalesTemp = usuarioActivo.getDatosPersonales();
        String[][] adicionesTemp = usuarioActivo.getAdiciones();
        for (int i = 0; i < usuarioActivo.getNombres().length; i++) {
            System.out.printf("||%3s||  %9s || %9s  ||   %10s   ||%3s||%3s||%4s||%16S||%12s||%4s||%12s||%6s||%5s||%5s||%7s||\n", (i + 1), personasTemp[i][1], personasTemp[i][2], personasTemp[i][0], personasTemp[i][3], personasTemp[i][4], personasTemp[i][5], personasTemp[i][6], datosPersonalesTemp[i][0], datosPersonalesTemp[i][1], datosPersonalesTemp[i][2], datosPersonalesTemp[i][3], adicionesTemp[i][0], adicionesTemp[i][1], adicionesTemp[i][2]);
            System.out.printf("||===||============||============||================||===||===||====||================||============||====||============||======||=====||=====||=======|| \n");
        }
        opcionesPadron();
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
            usuarioActivo = new Padron(nombre, ps1);
            usuarioActivo.setId(String.valueOf(funciones.generarID(usuarioActivo)));
            System.out.println("Tu nuevo ID es: " + usuarioActivo.getId());
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
        opcionesPadron();

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
            usuarioActivo.setNombres(personasTemp);
            usuarioActivo.setDatosPersonales(datosPersonalesTemp);
            usuarioActivo.setAdiciones(adicionesTemp);
        }
        usuarioActivo.setNombres(personasTemp);
        BaseDatos(usuarioActivo);
        BaseDatosTablas(usuarioActivo);
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
                        usuarioActivo = userBusqueda;
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

    public void construirArreglos(String[] arr) {
        int nPer = (arr.length) / 14;
        int j = 0;
        System.out.println("No. de personas " + nPer);
        String[][] personasTemp = new String[nPer][7];
        String[][] datosPersonalesTemp = new String[nPer][4];
        String[][] adicionesTemp = new String[nPer][3];
        for (int i = 0; i < nPer; i++) {
            personasTemp[i][0] = arregloGenerado[(0 + (i * 14))];
            personasTemp[i][1] = arregloGenerado[(1 + (i * 14))];
            personasTemp[i][2] = arregloGenerado[(2 + (i * 14))];
            personasTemp[i][3] = arregloGenerado[(3 + (i * 14))];
            personasTemp[i][4] = arregloGenerado[(4 + (i * 14))];
            personasTemp[i][5] = arregloGenerado[(5 + (i * 14))];
            personasTemp[i][6] = arregloGenerado[(6 + (i * 14))];
            datosPersonalesTemp[i][0] = arregloGenerado[(7 + (i * 14))];
            datosPersonalesTemp[i][1] = arregloGenerado[(8 + (i * 14))];
            datosPersonalesTemp[i][2] = arregloGenerado[(9 + (i * 14))];
            datosPersonalesTemp[i][3] = arregloGenerado[(10 + (i * 14))];
            adicionesTemp[i][0] = arregloGenerado[(11 + (i * 14))];
            adicionesTemp[i][1] = arregloGenerado[(12 + (i * 14))];
            adicionesTemp[i][2] = arregloGenerado[(13 + (i * 14))];
            personas.add(new Persona(personasTemp[i][0], personasTemp[i][1], personasTemp[i][2], personasTemp[i][6], personasTemp[i][3], personasTemp[i][4], personasTemp[i][5], datosPersonalesTemp[i][0]));
        }

        usuarioActivo.setNombres(personasTemp);
        usuarioActivo.setDatosPersonales(datosPersonalesTemp);
        usuarioActivo.setAdiciones(adicionesTemp);

        /*for(int i = 0; i<nPer; i++){
            
            for(int j = 0; j<arr.length; j++){
                personasTemp[i][0] = 
            }
        }*/
    }

    public void corregirPadron(Padron usr) {
        char conti = ' ';
        char conti1 = ' ';
        int elec = 0;
        String[][] personasTemp = usuarioActivo.getNombres();
        String[][] datosPersonalesTemp = usuarioActivo.getDatosPersonales();
        String[][] adicionesTemp = usuarioActivo.getAdiciones();
        Scanner charS = new Scanner(System.in);
        do {
            System.out.println("Ingrese el numero de la persona a corregir");
            elec = (sc.nextInt() - 1);
            System.out.println("Ingrese el o los nombres de pila ");
            String nombre = Ssc.nextLine();
            System.out.println("Ingrese el apellido paterno");
            String apellidoTemp = Ssc.nextLine();
            System.out.println("Ingrese el apellido materno");
            String apellido2Temp = Ssc.nextLine();
            System.out.println("Ingresa tu fecha de nacimiento en formato ddmmaaaa");
            String nacimiento = Ssc.nextLine();
            System.out.println("Esta seguro de continuar (S/N)");
            conti = charS.next().charAt(0);
            System.out.println(conti);
            if (conti == 'N') {
                System.out.println("Desea salir al menu (S/N)");
                conti1 = charS.next().charAt(0);
                if (conti1 == 'S') {
                    opcionesPadron();
                } else {
                    corregirPadron(usuarioActivo);
                }
            } else {
                personasTemp[elec][0] = nombre;
                personasTemp[elec][1] = apellidoTemp;
                personasTemp[elec][2] = apellido2Temp;
                personasTemp[elec][6] = nacimiento;
                personasTemp[elec][3] = FuncionesStatic.calMes(personasTemp[elec][6]);
                personasTemp[elec][4] = FuncionesStatic.calDias(personasTemp[elec][6]);
                personasTemp[elec][5] = FuncionesStatic.calAño(personasTemp[elec][6]);
                datosPersonalesTemp[elec][0] = funciones.calRFC(personasTemp[elec][0], personasTemp[elec][1], personasTemp[elec][2], personasTemp[elec][5], personasTemp[elec][3], personasTemp[elec][4]);
                datosPersonalesTemp[elec][1] = funciones.calEdad(personasTemp[elec][5]);
                datosPersonalesTemp[elec][2] = funciones.tipoPersona(datosPersonalesTemp[elec][1]);
                datosPersonalesTemp[elec][3] = funciones.calASCII(personasTemp[elec][0]);
                adicionesTemp[elec][0] = funciones.calLetra(personasTemp[elec][0]);
                adicionesTemp[elec][1] = funciones.calAyuda(datosPersonalesTemp[elec][2]);
                adicionesTemp[elec][2] = funciones.calImporte(personasTemp[elec][0], adicionesTemp[elec][1]);
                usuarioActivo.setNombres(personasTemp);
                usuarioActivo.setDatosPersonales(datosPersonalesTemp);
                usuarioActivo.setAdiciones(adicionesTemp);
                opcionesPadron();
            }
        } while (conti != 'N');
    }

    public char corregirPadron() {
        char conti;
        char conti1;
        int elec;
        String[][] personasTemp = usuarioActivo.getNombres();
        String[][] datosPersonalesTemp = usuarioActivo.getDatosPersonales();
        String[][] adicionesTemp = usuarioActivo.getAdiciones();
        Scanner charS = new Scanner(System.in);
        String nombre, apellidoTemp, apellido2Temp, nacimiento;
        System.out.println("Ingrese el numero de la persona a corregir");
        elec = (sc.nextInt() - 1);
        System.out.println("Ingrese el o los nombres de pila ");
        nombre = Ssc.nextLine();
        System.out.println("Ingrese el apellido paterno");
        apellidoTemp = Ssc.nextLine();
        System.out.println("Ingrese el apellido materno");
        apellido2Temp = Ssc.nextLine();
        System.out.println("Ingresa tu fecha de nacimiento en formato ddmmaaaa");
        nacimiento = Ssc.nextLine();
        System.out.println("Esta seguro de continuar (S/N)");
        conti = charS.next().charAt(0);
        System.out.println(conti);
        if (conti == 'S' || conti == 's') {
            personasTemp[elec][0] = nombre;
            personasTemp[elec][1] = apellidoTemp;
            personasTemp[elec][2] = apellido2Temp;
            personasTemp[elec][6] = nacimiento;
            personasTemp[elec][3] = FuncionesStatic.calMes(personasTemp[elec][6]);
            personasTemp[elec][4] = FuncionesStatic.calDias(personasTemp[elec][6]);
            personasTemp[elec][5] = FuncionesStatic.calAño(personasTemp[elec][6]);
            datosPersonalesTemp[elec][0] = funciones.calRFC(personasTemp[elec][0], personasTemp[elec][1], personasTemp[elec][2], personasTemp[elec][5], personasTemp[elec][3], personasTemp[elec][4]);
            datosPersonalesTemp[elec][1] = funciones.calEdad(personasTemp[elec][5]);
            datosPersonalesTemp[elec][2] = funciones.tipoPersona(datosPersonalesTemp[elec][1]);
            datosPersonalesTemp[elec][3] = funciones.calASCII(personasTemp[elec][0]);
            adicionesTemp[elec][0] = funciones.calLetra(personasTemp[elec][0]);
            adicionesTemp[elec][1] = funciones.calAyuda(datosPersonalesTemp[elec][2]);
            adicionesTemp[elec][2] = funciones.calImporte(personasTemp[elec][0], adicionesTemp[elec][1]);
            usuarioActivo.setNombres(personasTemp);
            usuarioActivo.setDatosPersonales(datosPersonalesTemp);
            usuarioActivo.setAdiciones(adicionesTemp);
            System.out.println("Operacion exitosa");
            opcionesPadron();
        } else {
            System.out.println("Desea salir al menu principal: (S/N)");
            conti1 = charS.next().charAt(0);
            if(conti1 == 'S'){
                System.out.println("Regresando al menu");
                opcionesPadron();
            }else{
                System.out.println("Intena otra vez");
                return 'N';
            }
        }
        return 'S';
    }

    public void pedirNuevosPadron() {

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
        String[][] datosPersonalesTemp = usuario.getDatosPersonales();
        String[][] adicionesTemp = usuario.getAdiciones();
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
                    linea.write(personasTemp[i][0]);
                    linea.write(",");
                    linea.write(personasTemp[i][1]);
                    linea.write(",");
                    linea.write(personasTemp[i][2]);
                    linea.write(",");
                    linea.write(personasTemp[i][3]);
                    linea.write(",");
                    linea.write(personasTemp[i][4]);
                    linea.write(",");
                    linea.write(personasTemp[i][5]);
                    linea.write(",");
                    linea.write(personasTemp[i][6]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][0]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][1]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][2]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][3]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][0]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][1]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][2]);
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
                    linea.write(personasTemp[i][0]);
                    linea.write(",");
                    linea.write(personasTemp[i][1]);
                    linea.write(",");
                    linea.write(personasTemp[i][2]);
                    linea.write(",");
                    linea.write(personasTemp[i][3]);
                    linea.write(",");
                    linea.write(personasTemp[i][4]);
                    linea.write(",");
                    linea.write(personasTemp[i][5]);
                    linea.write(",");
                    linea.write(personasTemp[i][6]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][0]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][1]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][2]);
                    linea.write(",");
                    linea.write(datosPersonalesTemp[i][3]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][0]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][1]);
                    linea.write(",");
                    linea.write(adicionesTemp[i][2]);
                    linea.write(",");
                }
                linea.close();
                escribir.close();
            } catch (Exception e) {
            }
        }
    }

}
