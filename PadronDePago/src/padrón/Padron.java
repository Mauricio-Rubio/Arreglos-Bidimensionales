package padrón;

import java.util.Scanner;

public class Padron {

    private String nombre;
    private String id;
    private String password;
    Scanner sc = new Scanner(System.in);
    Scanner Ssc = new Scanner(System.in);
    private String[][] nombres;
    private String[][] datosPersonales;
    private String[][] adiciones;

    public Padron(String nombre, String password) {
        this.password = password;
        this.nombre = nombre;
    }
    public Padron(String nombre, String password, String id) {
        this.password = password;
        this.id = id;
        this.nombre = nombre;
    }
    public Padron() {
        this.nombre = "Null";
        this.password = "null";
    }

    public String[][] getNombres() {
        return nombres;
    }

    public void setNombres(String[][] nombres) {
        this.nombres = nombres;
    }

    public String[][] getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(String[][] datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public String[][] getAdiciones() {
        return adiciones;
    }

    public void setAdiciones(String[][] adiciones) {
        this.adiciones = adiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public String toString() {
        return "Nombre " + getNombre() + " id: " + getId() + " passw " + getPassword();
    }

}
