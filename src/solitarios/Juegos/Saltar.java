package solitarios.Juegos;

import sistema.Jugador;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Saltar extends Juego {
    //TODO:Validar reglas de fin de juego
    //TODO:Validar jugada

    private String[][] matrizJuego;

    //primeras 6 filas sin puntuacion
    //misma fila no puede haber colores iguales en el area base (primeras 6 filas)
    public Saltar(int configuracion, Jugador jugador) {
        super(configuracion, jugador);
        crearTablero();
    }

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

    public void crearTablero() {
        String[][] s = new String[11][4];
        String[] defaultColorArray = new String[]{"R", "A", "V", "M", "A", "R", "M", "V", "V", "M", "A", "R", "M", "V", "R", "A"};
        String alphabet = "RAVM";
        List<String> colors = new ArrayList<>();
        int n = alphabet.length();
        Random random = new Random();
        int colorAx = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                if (i >= 7) {
                    if (this.getConfiguracion() == 1) {
                        s[i][j] = defaultColorArray[colorAx];
                        if (colorAx < 15) {
                            colorAx++;
                        }
                    } else {
                        while (colors.size() < 4) {
                            String colorFicha = String.valueOf(alphabet.charAt(random.nextInt(n)));
                            if (!colors.contains(colorFicha)) {
                                colors.add(colorFicha);
                            }
                        }
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

    public int fichasEnAreaBase(String[][] matrizJuego) {
        int cant = 0;
        for (int i = matrizJuego.length - 1; i >= 0; i--) {
            for (int j = matrizJuego[i].length - 1; j >= 0; j--) {
                if (!Objects.equals(matrizJuego[i][j], " ") && i < 6) {
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

    //TODO: tratar de quitar todas las excepciones de las clases. Que eso se maneje desde la vista.
    public void saltar(int posicionX, int posicionY) {
        Map<String, Integer> puntosMap = new HashMap<String, Integer>();
        String[][] matrizJuego = this.matrizJuego;
        int puntaje = 0;
        int fichasAreaBase = fichasEnAreaBase(matrizJuego);
        if (fichasAreaBase <= 2) {
            System.out.println("Fin del juego");
            //TODO llamar a puntaje.
        }

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
        switch (posicionX) {
            case 0 -> puntaje += 60;
            case 1 -> puntaje += 40;
            case 2 -> puntaje += 30;
            case 3 -> puntaje += 20;
            case 4 -> puntaje += 10;
            default -> puntaje += 0;
        }
        puntosMap.put(fichaActual, puntaje);
        //TODO Agregar zona de puntaje a partir de la fila 0 a 4
    }

    /*
        Devuelve verdadero si el juego no terminó
    */
    @Override
    public boolean jugar() {
        boolean siguenJugando = true;


        return siguenJugando;
    }

    //Reglas de puntaje
    public int getPuntaje() {
        int puntaje = 0;
        return puntaje;
    }

    //Reglas para validar jugada
    public boolean validarJugada() {
        boolean esValida = false;

        return esValida;
    }
}
