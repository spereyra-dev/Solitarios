package sistema;
public class Jugador {
    private String nombre;
    private String alias;
    private int edad;
    private String color;
    
    //compare
    //unicidad del alias

    public Jugador(String nombre, String alias, int edad) {
        this.nombre = nombre;
        this.alias = alias;
        this.edad = edad;
    }
}
