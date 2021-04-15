package padrón;

public class Persona {

    private String nombre;
    private String ApellidoP;
    private String ApellidoM;
    private String nacimiento;
    private String RFC;

    public Persona(String nombre, String ApellidoP, String ApellidoM, String edad) {
        this.nombre = nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.nacimiento = edad;
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
        return getNombre() + " " + getApellidoP()+ " " + getApellidoM()+" "+getNacimiento();
    }

}
