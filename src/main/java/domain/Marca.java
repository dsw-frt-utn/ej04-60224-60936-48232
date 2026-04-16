package domain;

public class Marca {
    private String Nombre;
    private String Pais;

    public Marca(String Nombre, String Pais) {
        this.Nombre = Nombre;
        this.Pais = Pais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }
    
    @Override
    public String toString() {
        return Nombre + " " + Pais;
    }
}

