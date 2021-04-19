package padrón;

public class Persona {

    private String nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String nacimiento;
    private String RFC;
    private String mes;
    private String dia;
    private String año;

    public Persona(String nombre, String ApellidoP, String ApellidoM, String edad) {
        this.nombre = nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.nacimiento = edad;
    }

    public Persona(String nombre, String ApellidoP, String ApellidoM, String nacimiento, String mes, String dia, String año) {
        this.nombre = nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.nacimiento = nacimiento;
        this.mes = mes;
        this.dia = dia;
        this.año = año;
    }

    public Persona(String nombre, String ApellidoP, String ApellidoM) {
        this.nombre = nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String ApellidoP) {
        this.ApellidoP = ApellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String ApellidoM) {
        this.ApellidoM = ApellidoM;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String edad) {
        this.nacimiento = edad;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", ApellidoP=" + ApellidoP + ", ApellidoM=" + ApellidoM + ", nacimiento=" + nacimiento + ", RFC=" + RFC + ", mes=" + mes + ", dia=" + dia + ", anio=" + año + '}';
    }


}
