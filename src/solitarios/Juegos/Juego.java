package solitarios.Juegos;

import java.util.HashMap;
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
    
    public abstract boolean jugar();
        
    //TODO:Falta testear
    public String siguienteColor(){
        String color = "";
        switch(this.turno%4){
            case 0:
                color = "R";
            break;
            case 1:
                color = "A";
            break;
            case 2:
                color = "V";
            break;
            case 3:
                color = "M";
            break;
            default:
            break;
        }
        this.turno++;        
        return color;
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
}
