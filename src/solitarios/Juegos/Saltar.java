package solitarios.Juegos;

import sistema.Jugador;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Saltar extends Juego {
    //TODO:Validar reglas de fin de juego
    //TODO:Validar jugada

    private String[][] matrizJuego;
    private String[][] matrizJugada;
    private String[][] matrizAnterior;

    //primeras 6 filas sin puntuacion
    //misma fila no puede haber colores iguales en el area base (primeras 6 filas)
    public Saltar(int configuracion, Jugador jugador) {
        super(configuracion, jugador);
        crearTablero();
    }

    public String[][] getMatrizJuego() {
        String[][] s = new String[11][4];
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

    private void setMatrizJuego() {
        for (int i = 0; i < this.matrizJugada.length; i++) {
            for (int j = 0; j < this.matrizJugada[0].length; j++) {
                if (validarEsFicha(this.matrizJugada, i, j)) {
                    this.matrizJuego[i][j] = this.getColorJugadaActual();
                }
            }

        }
    }

    //TODO falta hacer esto.
    private void setMatrizJugada(int x) {
        String[][] s = new String[11][4];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (i == x) {
                    s[i][j] = getColorJugadaActual();
                }
            }
        }
        this.matrizJugada = s;
    }

    //Al final no las supe usar, quedaban en bucle.
    private boolean validarFichaPorColumna(String[][] matrizJuego, String colorFicha) {
        boolean esValido = false;
        List<String> colors = new ArrayList<>();
        for (int i = 0; i < matrizJuego[0].length; i++) {
            for (int j = 0; j < matrizJuego.length; j++) {
                if (j >= 7) {
                    if (!Objects.equals(matrizJuego[j][i], colorFicha)) {
                        esValido = true;
                    }
                }
            }
        }
        return esValido;
    }

    //Al final no las supe usar, quedaban en bucle.
    private boolean validarFichaPorFila(String[][] matrizJuego, String colorFicha) {
        boolean esValido = false;
        List<String> colors = new ArrayList<>();
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                if (i >= 7) {
                    if (!Objects.equals(matrizJuego[i][j], colorFicha)) {
                        esValido = true;
                    }
                }
            }
        }
        return esValido;
    }

    public void crearTablero() {
        //TODO hay que verificar que no haya colores repetidos en la columna
        String[][] s = new String[11][4];
        String[] defaultColorArray = new String[]{"R", "A", "V", "M", "A", "R", "M", "V", "V", "M", "A", "R", "M", "V", "R", "A"};
        List<String> colors = new ArrayList<>();
        Random random = new Random();
        int colorAx = 0;
        for (int i = 0; i < s.length; i++) {
            List<String> alphabet = new ArrayList<>();
            alphabet.add("R");
            alphabet.add("A");
            alphabet.add("V");
            alphabet.add("M");
            for (int j = 0; j < s[i].length; j++) {
                int n = alphabet.size();
                if (i >= 7) {
                    if (this.getConfiguracion() == 1) {
                        s[i][j] = defaultColorArray[colorAx];
                        if (colorAx < 15) {
                            colorAx++;
                        }
                    } else {
                        String colorFicha = alphabet.get(random.nextInt(n));
                        colors.add(colorFicha);
                        alphabet.remove(colorFicha);
                        s[i][j] = colors.get(j);
                    }
                } else {
                    s[i][j] = " ";
                }
            }
            colors.clear();
        }
        this.matrizJuego = s;
    }

    public int fichasEnMismaFila(String[][] matrizJuego, int posicion) {
        int cantidad = 0;
        for (int i = 0; i < matrizJuego[posicion].length; i++) {
            if (!Objects.equals(matrizJuego[posicion][i], " ")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int[] primeraFicha(String[][] matrizJuego) {
        int[] s = new int[2];
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                if (!Objects.equals(matrizJuego[i][j], " ")) {
                    s[0] = i;
                    s[1] = j;
                    return s;
                }
            }
        }
        return s;
    }

    public int fichasEnAreaBase() {
        int cant = 0;
        for (int i = this.matrizJuego.length - 1; i >= 0; i--) {
            for (int j = this.matrizJuego[i].length - 1; j >= 0; j--) {
                if (!Objects.equals(this.matrizJuego[i][j], " ") && i > 4) {
                    cant++;
                }
            }
        }
        return cant;
    }

    public ArrayList<Point> fichasColor(String color) {
        String[][] matrizJuego = this.matrizJuego;
        ArrayList<Point> coordenadas = new ArrayList<>();
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                if (Objects.equals(matrizJuego[i][j], color)) {
                    coordenadas.add(new Point(i,j));
                }
            }
        }
        return coordenadas;
    }

    //TODO: Habría que sacarlo a la mierda, no sirve para nada, lo estaba usando para hacer los metodos de validaciones.
    public void saltar(int posicionX, int posicionY) {
        Map<String, Integer> puntosMap = new HashMap<String, Integer>();
        String[][] matrizJuego = this.matrizJuego;
        int puntaje = 0;
        int fichasASaltar = fichasEnMismaFila(matrizJuego, posicionX);
        String fichaActual = matrizJuego[posicionX][posicionY];
        int posXPrimerFicha = primeraFicha(matrizJuego)[0];
        int posYPrimerFicha = primeraFicha(matrizJuego)[1];
        if (posicionX - fichasASaltar >= 0 && !Objects.equals(matrizJuego[posicionX - fichasASaltar][posicionY], " ")) {
            if (fichasASaltar == 1 && (posicionX == posXPrimerFicha && posicionY == posYPrimerFicha)) {
                System.out.println("No puede saltar hasta haber otra ficha en la misma fila");
            } else {
                matrizJuego[posicionX][posicionY] = " ";
                matrizJuego[posicionX - fichasASaltar][posicionY] = fichaActual;
            }
        }
        puntosMap.put(fichaActual, puntaje);
        //TODO Agregar zona de puntaje a partir de la fila 0 a 4
    }

    private void setMatrizAnterior() {
        this.matrizAnterior = matrizJugada;
    }

    /*
        Devuelve verdadero si el juego no terminó
    */
    @Override
    public void jugar() {
        //TODO toh'
        this.setMatrizAnterior();
        this.setMatrizJuego();
        siguienteTurno();
    }

    //Reglas del puntaje
    public int getPuntaje() {
        //TODO PUNTAJE
        int puntaje = 0;
        return puntaje;
    }


    @Override
    public boolean finJuego(String args) {
        int fichasAreaBase = fichasEnAreaBase();
        return args.trim().equalsIgnoreCase("X") || (fichasAreaBase <= 2);
    }

    @Override
    public int validarJugada(String argsStr) {
        String[] args = argsStr.trim().split(" ");
        int esValido = 0;
        int columna = Integer.parseInt(args[0]) - 1;
        setMatrizJugada(columna);
        if (!validarPosicionDestinoVacia(columna)) { //TODO arreglar colores en la misma columna para poder validar esto.
            esValido = 1;
        }
        if (!validarFichasMismoColorAreaBase() && esValido == 0) {
            esValido = 2;
        }
        if (esValido == 0) {
            jugar();
        }
        return esValido;
    }


    private ArrayList<Point> obtenerPosicionPorColumna(int columna){
        ArrayList<Point> coordenadas = new ArrayList<>();
        for (int i = 0; i < matrizJuego[0].length; i++) {
            for (int j = 0; j < matrizJuego.length; j++) {
                if (j == columna && Objects.equals(matrizJuego[i][j], getColorJugadaActual())) {
                    coordenadas.add(new Point(j,i));
                }
            }
        }
        return coordenadas;
    }
    private boolean validarPosicionDestinoVacia(int columna) {
        //TODO arreglar colores en la misma columna para poder validar esto.
        //Pensaba crear un método que le pase por parametro la columna a mover, recorra la matriz y con el método validarEsFicha ver si está vació.
        //Sin TESTEAR
        boolean esValido;
        int fichasASaltar = fichasEnMismaFila(this.matrizJuego, obtenerPosicionPorColumna(columna).get(0).x);
        esValido = validarEsFicha(matrizJuego,obtenerPosicionPorColumna(columna).get(0).x-fichasASaltar,obtenerPosicionPorColumna(columna).get(0).y);
        return esValido;
    }

    private boolean validarFichasMismoColorAreaBase() {
        boolean esValido = true;
        int cantidad = 0;
        int posicionFichaUno = fichasColor(getColorJugadaActual()).get(0).x;
        int posicionFichaDos = fichasColor(getColorJugadaActual()).get(1).x;
        int posicionFichaTres = fichasColor(getColorJugadaActual()).get(2).x;
        int posicionFichaCuatro = fichasColor(getColorJugadaActual()).get(3).x;
        if (posicionFichaUno > 4 && (posicionFichaUno == posicionFichaDos || posicionFichaUno == posicionFichaTres || posicionFichaUno == posicionFichaCuatro)) {
            cantidad++;
        }
        if (posicionFichaDos > 4 && (posicionFichaDos == posicionFichaTres || posicionFichaDos == posicionFichaCuatro)) {
            cantidad++;
        }
        if (posicionFichaTres > 4 && (posicionFichaTres == posicionFichaDos || posicionFichaTres == posicionFichaCuatro)) {
            cantidad++;
        }
        if (posicionFichaCuatro > 4 && (posicionFichaCuatro == posicionFichaDos || posicionFichaCuatro == posicionFichaTres)) {
            cantidad++;
        }

        if (cantidad > 2) {
            esValido = false;
        }
        return esValido;
    }

    @Override
    public boolean validarArgumentosJugada(String argsStr) {
        String[] args = argsStr.trim().split(" ");
        boolean esValido = true;
        int columna = 0;
        try {
            columna = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            esValido = false;
        }
        //Valído la cantidad de los argumentos
        if (esValido) {
            esValido = (args.length == 1);
        }
        //Valido que no pase el mínimo y máximo de columnas
        if (esValido) {
            esValido = (columna >= 0) && (columna <= 3);
        }
        return esValido;
    }
}
