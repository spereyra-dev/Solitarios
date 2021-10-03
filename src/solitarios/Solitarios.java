package solitarios;

import solitarios.Saltar.Saltar;

public class Solitarios {
    public static void main(String[] args) {
        Saltar salto = new Saltar();       
        char[][] tablero = salto.crearTablero(11, 4);
        salto.dibujarTablero(tablero);      
        System.out.println(salto.fichasEnMismaFila(tablero, 7));
    }
    
}
