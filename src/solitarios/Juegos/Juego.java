//Santiago Pereyra 245198
//Venancio Portillo 276560

package solitarios.Juegos;

import sistema.Jugador;

public abstract class Juego {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private int configuracion;
    private Jugador jugador;
    private int turno;

    public Juego(int configuracion, Jugador jugador) {
        this.configuracion = configuracion;
        this.jugador = jugador;
        this.turno = 1;
    }

    public String getANSI_RESET() {
        return ANSI_RESET;
    }

    public String getANSI_RED() {
        return ANSI_RED;
    }

    public String getANSI_GREEN() {
        return ANSI_GREEN;
    }

    public String getANSI_YELLOW() {
        return ANSI_YELLOW;
    }

    public String getANSI_BLUE() {
        return ANSI_BLUE;
    }

    public int getConfiguracion() {
        return configuracion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getTurno() {
        return turno;
    }
    
    public abstract void jugar();
    
    public abstract int setPuntaje();
    
    public abstract int getPuntaje();
    
    public abstract void crearTablero();
    
    public abstract int validarJugada(String argsStr);
    
    public abstract boolean validarArgumentosJugada(String argsStr);
    
    public abstract boolean finJuego(String args);
    
    public String colorSiguiente(){
        return getColorPorTurno(this.turno + 1);
    }
    
    public String colorAnterior(){
        return getColorPorTurno(this.turno - 1);
    }
    
    public String setColor(String color){
        String hashtagConColor = "";
        switch(color){
            case "R":
                hashtagConColor = this.ANSI_RED + "#" + this.ANSI_RESET;
            break;
            case "A":
                hashtagConColor = this.ANSI_BLUE + "#" + this.ANSI_RESET;
            break;
            case "V":
                hashtagConColor = this.ANSI_GREEN + "#" + this.ANSI_RESET;
            break;
            case "M":
                hashtagConColor = this.ANSI_YELLOW + "#" + this.ANSI_RESET;
            break;
            default:
            break;
        }
        return hashtagConColor;
    }
    
    public String getColorJugadaActual(){        
        return getColorPorTurno(this.turno);
    }
    
    public String getColorPorTurno(int turno){
        String color = "";
            switch(turno%4){
            case 1:
                color = "R";
            break;
            case 2:
                color = "A";
            break;
            case 3:
                color = "V";
            break;
            case 0:
                color = "M";
            break;
            default:
                color = "R";
            break;
        }
        return color;
    }
    
    public void siguienteTurno(){
        this.turno++;
    }
    
    //Comprueba si una posición en una matriz es una ficha    
    public boolean validarEsFicha(String[][] matriz,int x,int y){
        return (matriz[x][y] != null) && (matriz[x][y].contains("R") || matriz[x][y].contains("V") || matriz[x][y].contains("A") || matriz[x][y].contains("M"));
    }
}
