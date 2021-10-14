package solitarios.Juegos;

import sistema.Jugador;

public class Saltar extends Juego{
    //TODO:Validar reglas de fin de juego
    //TODO:Validar jugada
    
    private String[][] matrizJuego;
    //primeras 6 filas sin puntuacion
    //misma fila no puede haber colores iguales en el area base (primeras 6 filas)
    public Saltar(int configuracion, Jugador jugador) {
        super(configuracion,jugador);
        crearTablero();
    }

    //¿te parece una buena solucion para mostrar la matriz con los colores? En memoria guardarla con las letras pero cuando la mostramos, que sean los # con los colores. 
    //Nos va a facilitar para las validaciones. Cuando veas este mensaje avisame y te comento bien la idea.
    public String[][] getMatrizJuego() {
        String[][] s = this.matrizJuego;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (!s[i][j].equals(' ')) {
                    s[i][j] = this.setColor(s[i][j]);
                } 
            }
        }
        return s;
    }
    private void setMatrizJuego(){
    }
    
    public void crearTablero() {
        String[][] s = new String[11][4];        
        //TODO:Configuración predeterminada(No modifiqué nada)
        if(this.getConfiguracion() == 1){
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s[i].length; j++) {
                    if (i >= 7) {
                        s[i][j] = "#";
                    } else {
                        s[i][j] = " ";
                    }
                }
            }
        //TODO:Configuracion aleatoria
        } else if (this.getConfiguracion() == 2){
        }
        
        this.matrizJuego = s;
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
    //TODO: tratar de quitar todas las excepciones de las clases. Que eso se maneje desde la vista.
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

    /*
        Devuelve verdadero si el juego no terminó
    */
    @Override
    public boolean jugar() {
        boolean siguenJugando = true;
        
        
        return siguenJugando;
    }

}
