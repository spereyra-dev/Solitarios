package solitarios.Juegos;

public class Saltar extends Juego{
    
    private char[][] matrizJuego;
    //Jugadores
    //primeras 6 filas sin puntuacion
    //misma fila no puede haber colores iguales en el area base (primeras 6 filas)
    public Saltar() {
        this.matrizJuego = crearTablero(11, 4);
    }

    public int fichasEnMismaFila(char[][] matrizJuego, int posicion) {
        int cantidad = 0;
        for (int i = 0; i < matrizJuego[posicion].length; i++) {
            if (matrizJuego[posicion][i] == '#') {
                cantidad++;
            }
        }
        return cantidad;
    }        
    
    public void saltar (char[][] matrizJuego,int posicionX, int posicionY) throws Exception{
        int fichasASaltar = fichasEnMismaFila(matrizJuego,posicionX);
        if (matrizJuego[posicionX][posicionY+fichasASaltar] == '#'){
            matrizJuego[posicionX][posicionY]=' ';
            matrizJuego[posicionX][posicionY+fichasASaltar] = '#';
        }else if (fichasASaltar==1){ //Tenes que hacer tu solucion de mierda jaskdjka, es decir revisar si es la ficha más adelantada
            
        }else{
            throw new RuntimeException("El espacio a saltar no está disponible");
        }        
    }

    public char[][] crearTablero(int filas, int columnas) {
        char[][] s = new char[filas][columnas];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (i >= 7) {
                    s[i][j] = '#';
                } else {
                    s[i][j] = ' ';
                }
            }
        }
        return s;
    }

    public void dibujarTablero(char[][] matrizJuego) {
        for (int i = 0; i < matrizJuego.length; i++) {
            System.out.println("    +-+-+-+-+");
            switch (i) {
                case 0:
                    System.out.print("60  ");
                    break;
                case 1:
                    System.out.print("40  ");
                    break;
                case 2:
                    System.out.print("30  ");
                    break;
                case 3:
                    System.out.print("20  ");
                    break;
                case 4:
                    System.out.print("10  ");
                    break;
                default:
                    System.out.print("    ");
                    break;
            }
            for (int j = 0; j < matrizJuego[i].length; j++) {

                System.out.print("|");
                //#
                System.out.print(matrizJuego[i][j]);

                if (j == matrizJuego[i].length - 1) {
                    System.out.print("|");
                }
            }
            if (i == matrizJuego.length - 1) {
                System.out.println();
                System.out.println("    +-+-+-+-+");
                System.out.print("     1 2 3 4");
            }
            System.out.println();
        }
    }

    /*
        Devuelve verdadero si el juego no terminó
    */
    @Override
    public boolean jugar() {
        boolean siguenJugando = true;
        
        
        return siguenJugando;
    }

}
