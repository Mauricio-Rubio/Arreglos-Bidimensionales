package padrón;

import java.util.Scanner;

public class Padron {
    private String nombre;
    private String id;
    private String password;
    Scanner sc = new Scanner(System.in);
    Scanner Ssc = new Scanner(System.in);
    
    public Padron(String nombre, String password){
        this.password = password;
        this.nombre = nombre;
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
    public String toString(){
        return "Nombre "+getNombre()+" id: "+getId()+" passw "+getPassword();
    }
    
}
