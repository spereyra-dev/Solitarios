package solitarios.Juegos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import sistema.Jugador;

public class Rectangulo extends Juego {
    
    private String[][] matrizJuego;
    private ArrayList<Point> topes;
    private int cantidadRectangulos;   
    private String [][] rectanguloAnterior;
    private String [][] rectanguloJugada;
    
    public Rectangulo(int configuracion, Jugador jugador) {
        super(configuracion,jugador);
        this.cantidadRectangulos = 0;
        this.rectanguloAnterior = new String[20][20];
        this.rectanguloJugada = new String[20][20];
        crearTablero();
    }   
    
    public int getCantidadRectangulos() {
        return cantidadRectangulos;
    }
     
    public String[][] getMatrizJuego() {
        String[][] s = new String[20][20];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (validarEsFicha(this.matrizJuego, i, j)) {
                    s[i][j] = this.setColor(this.matrizJuego[i][j]);
                } else {
                    s[i][j] = this.matrizJuego[i][j];                    
                }
            }
        }
        return s;
    }
    
    private void setMatrizJuego(){
        for (int i = 0; i < this.rectanguloJugada.length; i++) {
            for (int j = 0; j < this.rectanguloJugada[0].length; j++) {
                if(validarEsFicha(this.rectanguloJugada, i, j)){
                    this.matrizJuego[i][j] = this.getColorJugadaActual();
                }
            }
            
        }
    }
    
    private void setRectanguloJugada(int x, int y, int cantidadFilas, int cantidadColumnas){
        String[][] rectangulo = new String[20][20];
        
        for (int i = 0; i < rectangulo.length; i++) {
            for (int j = 0; j < rectangulo.length; j++) {
                if(i>=x && j>=y && i <= (x+cantidadFilas) && j <= (y+cantidadColumnas)){
                    rectangulo[i][j] = getColorJugadaActual();
                }
            }
        }
        this.rectanguloJugada = rectangulo;
    }
    
    private void setRectanguloAnterior(){
        this.rectanguloAnterior = rectanguloJugada;
    }
    
    @Override
    public void crearTablero() {
        String[][] tablero = new String[20][20];
        ArrayList<Point> topes = new ArrayList<>();
        //Configuración predeterminada
        if(this.getConfiguracion() == 1){
            topes.add(new Point(0,2));
            topes.add(new Point(2,2));
            topes.add(new Point(3,4));
            topes.add(new Point(5,15));
            topes.add(new Point(8,9));
            topes.add(new Point(8,10));
            topes.add(new Point(11,9));
            topes.add(new Point(11,19));
            topes.add(new Point(12,17));
            topes.add(new Point(13,16));
            topes.add(new Point(14,5));
            topes.add(new Point(14,10));
            topes.add(new Point(14,19));
            topes.add(new Point(15,16));
            topes.add(new Point(16,4));
            topes.add(new Point(17,3));
            topes.add(new Point(17,10));
            topes.add(new Point(18,2));
            topes.add(new Point(18,13));
            topes.add(new Point(19,14));
        //Configuracion aleatoria
        } else if (this.getConfiguracion() == 2){
            Random randomX = new Random();
            Random randomY = new Random();
            while(topes.size()<20){
                int numeroRandomX = randomX.nextInt(20);
                int numeroRandomY = randomY.nextInt(20);
                Point topeNuevo = new Point(numeroRandomX,numeroRandomY);
                if(!topes.contains(topeNuevo)){
                    topes.add(topeNuevo);
                }
            }
        }
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                Point puntoEnTablero =  new Point(i,j);
                if (topes.contains(puntoEnTablero)) {
                    tablero[i][j] = "*";
                } else {
                    tablero[i][j] = "-";
                }
            }
        }
        
        this.topes = topes;
        this.matrizJuego = tablero;
    }
    
    @Override
    public void jugar() {        
        cantidadRectangulos++;
        this.setRectanguloAnterior();
        this.setMatrizJuego();
        siguienteTurno();
    }
    
    @Override
    public int validarJugada(String argsStr){
        String [] args = argsStr.trim().split(" ");
        int esValido = 0;
        int x = Integer.parseInt(args[0]) - 1;
        int y = Integer.parseInt(args[1]) - 1;
        int cantFilas = Integer.parseInt(args[2]) - 1;
        int cantColumnas = Integer.parseInt(args[3]) - 1;
        
        setRectanguloJugada(x, y, cantFilas, cantColumnas);
        if(!validarRectanguloNoPisaTopes() && esValido == 0){
            esValido = 1;
        }
        if(!validarRectanguloNoPisaOtroRectangulo() && esValido == 0){
            esValido = 2;
        }
        if(!validarEsAdyacente()&& esValido == 0){
            esValido = 3;
        }
        if(esValido == 0){
            jugar();
        }        
        return esValido;
    }
    
    @Override
    public boolean validarArgumentosJugada(String argsStr){
        String [] args = argsStr.trim().split(" ");
        int x = 0;
        int y = 0;
        int cantFilas = 0;
        int cantColumnas = 0;
        boolean esValido = true;
        try {
            x = Integer.parseInt(args[0]) - 1;
            y = Integer.parseInt(args[1]) - 1;
            cantFilas = Integer.parseInt(args[2]) - 1;
            cantColumnas = Integer.parseInt(args[3]) - 1;
        } catch (NumberFormatException e) {
            esValido = false;
        }
        //Valído la cantidad de los argumentos
        if(esValido){
            esValido = (args.length == 4);
        }
        //Valido que no pase el mínimo y máximo de filas
        if(esValido){
            esValido = (x >= 0) && ((cantFilas + x) <= 20);
        }
        //Valido que no pase el mínimo y máximo de columnas
        if(esValido){
            esValido = (y >= 0) && ((cantColumnas + y) <= 20);
        }
        return esValido;
    }
    
    @Override
    public boolean finJuego(String args){
        return args.trim().equalsIgnoreCase("X") || !validarIngresarMasRectangulos();
    };
    
    private boolean validarRectanguloNoPisaTopes(){
        boolean esValido = true;
        for(Point tope : topes ){
            if(validarEsFicha(this.rectanguloJugada, tope.x, tope.y)){
                esValido = false;
            }
        }
        
        return esValido;
    }
    
    private boolean validarRectanguloNoPisaOtroRectangulo(){
        boolean esValido = true;
        for (int i = 0; i < this.matrizJuego.length && esValido; i++) {
            for (int j = 0; j < this.matrizJuego[0].length && esValido; j++) {
                if(validarEsFicha(this.matrizJuego, i, j) && validarEsFicha(this.rectanguloJugada, j, j)){
                    esValido = false;
                }
            }

        }
        return esValido;
    }
    
    private boolean validarEsAdyacente(){
        boolean esValido = false;
        
        if(this.getTurno() > 1){
            for (int i = 0; i < this.rectanguloAnterior.length && !esValido; i++) {
                for (int j = 0; j < this.rectanguloAnterior[0].length && !esValido; j++) {
                    boolean derecha = false;
                    boolean izquierda = false;
                    boolean abajo = false;
                    boolean arriba = false;
                    boolean jugada = validarEsFicha(this.rectanguloJugada,i,j);

                    if(i<this.rectanguloAnterior.length - 1){
                        derecha = validarEsFicha(this.rectanguloAnterior,i+1,j) && jugada;
                    }
                    if(i>0){
                        izquierda = validarEsFicha(this.rectanguloAnterior,i-1,j) && jugada;
                    }
                    if(j<this.rectanguloAnterior[0].length - 1){
                        abajo = validarEsFicha(this.rectanguloAnterior,i,j+1) && jugada;
                    }
                    if(j>0){
                        arriba = validarEsFicha(this.rectanguloAnterior,i,j-1) && jugada;
                    }

                    if(derecha || izquierda || arriba || abajo){
                        esValido = true;
                    }
                }
            }
        } else {
            esValido = true;
        }
        
        return esValido;
    }
    
    private boolean validarIngresarMasRectangulos(){
        boolean esValidoPorEspacio = false;
        boolean esValidoPorCantidadRectangulos = (this.cantidadRectangulos < 10);
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[0].length; j++) {
                if(!validarEsFicha(matrizJuego, j, j)){
                    esValidoPorEspacio = true;
                }
            }
        }
        return esValidoPorEspacio && esValidoPorCantidadRectangulos;
    }
}
