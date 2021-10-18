//Santiago Pereyra 245198
//Venancio Portillo 276560

package solitarios.Juegos;

import sistema.Jugador;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Saltar extends Juego {
    private String[][] matrizJuego;
    private String[][] matrizJugada;
    private String[][] matrizAnterior;

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
        this.matrizJuego = matrizJugada;
    }

    private void setMatrizJugada(int columna) {
        String[][] s = matrizJuego;
        int fichasASaltar = 0;
        int fila = 0;
        for (Point coordenadas : posicionFichaColorPorColumna(s, columna)) {
            if (coordenadas.y == columna) {
                fichasASaltar = fichasEnMismaFila(s, coordenadas.x);
                fila = coordenadas.x;
            }
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (i == fila && j == columna) {
                    s[i][j] = " ";
                    if (i - fichasASaltar < 0) {
                        s[0][j] = getColorJugadaActual();
                    } else {
                        s[i - fichasASaltar][j] = getColorJugadaActual();
                    }
                }
            }
        }
        this.matrizJugada = s;
    }

    private String alphabet(int numero) {
        List<String> alphabet = new ArrayList<>();
        alphabet.add("R");
        alphabet.add("A");
        alphabet.add("V");
        alphabet.add("M");
        return alphabet.get(numero);
    }

    public void crearTablero() {
        String[][] s = new String[11][4];
        int[][] aleatorio = matrizAleatoria();
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
                        s[i][j] = alphabet((aleatorio[i - 7][j]) - 1);
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
                    coordenadas.add(new Point(i, j));
                }
            }
        }
        return coordenadas;
    }

    private void setMatrizAnterior() {
        this.matrizAnterior = matrizJuego;
    }

    @Override
    public void jugar() {
        this.setMatrizAnterior();
        this.setMatrizJuego();
        siguienteTurno();
    }

    //Reglas del puntaje
    public int getPuntaje() {
        int puntaje = 0;
        for (int i = 0; i < this.matrizJuego.length; i++) {
            for (int j = 0; j < this.matrizJuego[0].length; j++) {
                if (validarEsFicha(matrizJuego, i, j)) {
                    switch (i) {
                        case 0:
                            puntaje += 60;
                            break;
                        case 1:
                            puntaje += 40;
                            break;
                        case 2:
                            puntaje += 30;
                            break;
                        case 3:
                            puntaje += 20;
                            break;
                        case 4:
                            puntaje += 10;
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        return puntaje;
    }


    @Override
    public boolean finJuego(String args) {
        int fichasAreaBase = fichasEnAreaBase();
        return args.trim().equalsIgnoreCase("X") || (fichasAreaBase <= 2);
    }

    public boolean validarNoEsPrimerFicha(int columna) {
        boolean esValido = true;
        String[][] s = matrizJuego;
        int fichasASaltar = 0;
        int fila = 0;
        int posXPrimerFicha = primeraFicha(s)[0];
        int posYPrimerFicha = primeraFicha(s)[1];
        for (Point coordenadas : posicionFichaColorPorColumna(s, columna)) {
            if (coordenadas.y == columna) {
                fichasASaltar = fichasEnMismaFila(s, coordenadas.x);
                fila = coordenadas.x;
            }
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (i == fila && j == columna) {
                    if (fichasASaltar == 1 && (i == posXPrimerFicha && j == posYPrimerFicha)) {
                        esValido = false;
                    }
                }
            }
        }
        return esValido;
    }

    @Override
    public int validarJugada(String argsStr) {
        String[] args = argsStr.trim().split(" ");
        int esValido = 0;
        int columna = Integer.parseInt(args[0]) - 1;
        if (!validarPosicionDestinoVacia(columna)) {
            esValido = 1;
        }
        if (!validarFichasMismoColorAreaBase() && esValido == 0) {
            esValido = 2;
        }
        if (!validarNoEsPrimerFicha(columna) && esValido == 0) {
            esValido = 3;
        }
        if (esValido == 0) {
            setMatrizJugada(columna);
            jugar();
        }
        return esValido;
    }

    public ArrayList<Point> posicionFichaColorPorColumna(String[][] matrizJuego, int posicion) {
        ArrayList<Point> coordenadas = new ArrayList<>();
        for (int i = 0; i < matrizJuego.length; i++) {
            if (!Objects.equals(matrizJuego[i][posicion], " ") && Objects.equals(getColorJugadaActual(), matrizJuego[i][posicion])) {
                coordenadas.add(new Point(i, posicion));
            }
        }
        return coordenadas;
    }

    private boolean validarPosicionDestinoVacia(int columna) {
        boolean esValido = true;
        int nuevaCoordenada = 0;
        for (Point coordenadas : posicionFichaColorPorColumna(this.matrizJuego, columna)) {
            if (coordenadas.y == columna) {
                int fichasASaltar = fichasEnMismaFila(this.matrizJuego, coordenadas.x);
                nuevaCoordenada = (coordenadas.x - fichasASaltar) < 0 ? 0 : coordenadas.x - fichasASaltar;
                esValido = !validarEsFicha(this.matrizJuego, nuevaCoordenada, coordenadas.y);
            }
        }
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

    @Override
    public int setPuntaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int[][] n = new int[4][4];
    private int[] num = {1, 2, 3, 4};

    public int[][] matrizAleatoria() {
        for (int i = 0; i < 4; i++) {
            int time = 0;
            for (int j = 0; j < 4; j++) {
                n[i][j] = generateNum(time);
                if (n[i][j] == 0) {
                    if (j > 0) {
                        j -= 2;
                        continue;
                    } else {
                        i--;
                        j = 3;
                        continue;
                    }
                }
                if (isCorrect(i, j)) {
                    time = 0;
                } else {
                    time++;
                    j--;
                }
            }
        }
        return n;
    }

    private boolean isCorrect(int row, int col) {
        return (checkRow(row) & checkLine(col) & checkNine(row, col));
    }

    private boolean checkRow(int row) {
        for (int j = 0; j < 3; j++) {
            if (n[row][j] == 0) {
                continue;
            }
            for (int k = j + 1; k < 4; k++) {
                if (n[row][j] == n[row][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLine(int col) {
        for (int j = 0; j < 3; j++) {
            if (n[j][col] == 0) {
                continue;
            }
            for (int k = j + 1; k < 4; k++) {
                if (n[j][col] == n[k][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkNine(int row, int col) {
        int j = row / 4 * 4;
        int k = col / 4 * 4;
        for (int i = 0; i < 3; i++) {
            if (n[j + i / 4][k + i % 4] == 0) {
                continue;
            }
            for (int m = i + 1; m < 4; m++) {
                if (n[j + i / 4][k + i % 4] == n[j + m / 4][k + m % 4]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Random r = new Random();

    private int generateNum(int time) {
        if (time == 0) {
            for (int i = 0; i < 4; i++) {
                num[i] = i + 1;
            }
        }
        if (time == 4) {
            return 0;
        }

        int ranNum = r.nextInt(4 - time);
        int temp = num[3 - time];
        num[3 - time] = num[ranNum];
        num[ranNum] = temp;
        return num[3 - time];
    }
    /*public boolean validarHayColumnasValidas(){
        boolean esValido = false;
        for (int i = 1; i <= 4; i++) {
            if(validarJugada(Integer.toString(i)) == 0){
                esValido = true;
            }
        }
        return esValido;
    }*/
}
