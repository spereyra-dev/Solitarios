package sistema;
import java.util.List;

public class Jugador {
    private String nombre;
    private String alias;
    private int edad;
    private int [][] fichas;
    
    //compare
    //unicidad del alias
    //Identificar que fichas es de cada jugador

    public Jugador(String nombre, String alias, int edad) {
        this.nombre = nombre;
        this.alias = alias;
        this.edad = edad;
        this.fichas = new int[400][2];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getAlias().equals((String)obj);
    }
}
