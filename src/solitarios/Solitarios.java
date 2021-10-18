package solitarios;

import sistema.Jugador;
import sistema.Sistema;
import solitarios.Juegos.Juego;
import solitarios.Juegos.Rectangulo;
import solitarios.Juegos.Saltar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Solitarios {  
    
    public static void main(String[] args) throws Exception {
        Sistema sistema = new Sistema();
        /*
        REGION TEST:
        ***Borrar antes de la entrega***
        */
        Jugador j1 = new Jugador("Venancio","Vena",26);
        Jugador j2 = new Jugador("Santiago","San",21);
        sistema.registrarJugador(j1);
        sistema.registrarJugador(j2);
        sistema.elegirJuego(3,2,j1);

        /*
        END REGION TEST:        
        */
        
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 5){
            clearScreen();
            menu();
            opcion = in.nextInt();
            switch(opcion){
                case 1:
                    registrar(sistema);
                break;
                case 2: 
                case 3:
                    clearScreen();
                    if(sistema.getJugadores().size() != 0){
                        Jugador jugador = elegirJugador(sistema);
                        clearScreen();
                        int configuracion = elegirConfiguracion(sistema);
                        clearScreen();
                        sistema.elegirJuego(opcion,configuracion,jugador);
                        if(opcion == 2){
                            jugarSaltar(sistema);
                        } else if (opcion == 3){
                            jugarRectangulo(sistema);
                        }
                    } else {
                        System.out.println("Para poder jugar debe ingresar un jugador primero! Presione cualquier tecla para volver al menú .. ");
                        in.nextLine();
                        in.nextLine();
                    }
                break;
                case 4:
                    clearScreen();              
                    System.out.println("*******BITÁCORA*********");
                    System.out.println("*******EN CONSTRUCCION*********");
                    System.out.println("*******BITÁCORA*********");
                break;
                case 5:
                    clearScreen();
                break;
                default:
                break;
            }
        }
    }
    public static void menu(){
        System.out.println("*******MENU*********");
        System.out.println("Seleccione una opción:");
        System.out.println("1-Registrar Jugador");
        System.out.println("2-Jugar a Saltar");
        System.out.println("3-Jugar a Rectángulo");
        System.out.println("4-Bitácora");
        System.out.println("5-Fin");
        System.out.println("*******MENU*********");
    }
    public static void registrar(Sistema sistema) {
        clearScreen();
        boolean quiereRegistrar = true;
        String nombre = "";
        String alias = "";
        int edad = 0;        
        Scanner in = new Scanner(System.in);
        while(quiereRegistrar){
            System.out.println("*******REGISTRAR JUGADOR*********");
            String opcion = "";

            //Solicito nombre del jugador
            System.out.println("Escriba su nombre: ");
            nombre = in.nextLine();
            while(nombre.isBlank()){
                System.out.println("Debe escribir su nombre: ");
                nombre = in.nextLine();                    
            }

            //Solicito alias del jugador
            System.out.println("Escriba su alias: ");
            alias = in.nextLine();
            while(alias.isBlank() || sistema.existeAlias(alias)){
                if(alias.isBlank()){
                    System.out.println("Debe escribir su alias: ");
                }
                if(sistema.existeAlias(alias)){
                    System.out.println("Otro jugador ya tiene ese alias. Por favor, ingrese otro alias: ");
                }                    
                alias = in.nextLine();                    
            }

            //Solicito edad del jugador
            System.out.println("Escriba su edad: ");
            boolean edadValidada = false;
            while(!edadValidada){
                edad = leerEntero();
                if(sistema.validarEdad(edad)){
                    edadValidada = true;
                } else {
                    System.out.println("La  edad debe ser entre 0 y 99. Intente nuevamente: ");
                }
            }
            Jugador j = new Jugador(nombre,alias,edad);
            sistema.registrarJugador(j);

            System.out.println("El jugador ha sido creado exitosamente! Pulse una tecla para continuar ..");                
            in.nextLine(); 

            System.out.println("Si desea continuar continuar ingresando jugadores presione 'S' de lo contrario, presione cualquier tecla: ");
            opcion = in.nextLine();
            System.out.println();
            System.out.println("*******REGISTRAR JUGADOR*********");
            if(!opcion.equalsIgnoreCase("S")){
                quiereRegistrar = false;
            }            
            clearScreen();
        }
    }
    public static void clearScreen() {  
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private static void jugarSaltar(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        String args = "";
        Juego juego = ((Saltar)sistema.getJuego());
        while(!juego.finJuego(args)){
            int validarJugada = -1;
            while(validarJugada != 0){
                clearScreen();
                dibujarTablero(sistema);
                System.out.println("Puede mover las fichas de las siguientes columnas: ");
                for (int i = 0; i < ((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).size(); i++) {
                    System.out.println("Fila: "+((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).get(i).x+", Columna: "+(((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).get(i).y+1));
                }
                System.out.println();
                System.out.println("Elija columna a mover");
                args = in.nextLine();
                if(juego.validarArgumentosJugada(args)){
                    validarJugada = juego.validarJugada(args);
                    if(validarJugada == 1){
                        System.out.println("La posición de destino debe estar vacía.");
                        in.nextLine();
                    }
                    if(validarJugada == 2){
                        System.out.println("En el área de base, no puede haber dos fichas del mismo color en la misma fila.");
                        in.nextLine();
                    }
                    if(validarJugada == 3){
                        sistema.getJuego().colorAnterior();
                        System.out.println("La ficha más adelantada del color considerado en el tablero no puede avanzar solamente una posición");
                        in.nextLine();
                    }
                } else {
                    System.out.println("La jugada debe ingresar un número");
                    System.out.println("El mismo indica la columna desde la cual se realizará el salto.");
                    System.out.println("No se debe ingresar un número que supere el máximo de columnas del tablero.");
                    in.nextLine();
                }
            }
        }

    } 
    private static void jugarRectangulo(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        String args = "";
        Juego juego = (Rectangulo)sistema.getJuego();
        while(!juego.finJuego(args)){
            int validarJugada = -1;
            while(validarJugada != 0){
                clearScreen();
                dibujarTablero(sistema);
                System.out.println("Ingrese Jugada:");
                args = in.nextLine();
                if(juego.validarArgumentosJugada(args)){
                    validarJugada = juego.validarJugada(args);
                    if(validarJugada == 1){
                        System.out.println("El rectángulo elegido pisa algún tope.");
                        in.nextLine();
                    }
                    if(validarJugada == 2){
                        System.out.println("El rectángulo elegido pisa algún otro rectángulo.");
                        in.nextLine();
                    }
                    if(validarJugada == 3){
                        sistema.getJuego().colorAnterior();
                        System.out.println("El rectángulo elegido no es adyacente al anterior. (Jugada anterior color: " + sistema.getJuego().colorAnterior() + ")");
                        in.nextLine();
                    }
                } else {
                    System.out.println("La jugada debe ser 4 números del 1-20.");
                    System.out.println("El primero indíca la posición \"x\" de inicio.");
                    System.out.println("El segundo indíca la posición \"y\" de inicio. ");
                    System.out.println("El tercero indíca la cantidad de filas.");
                    System.out.println("El cuarto indíca la cantidad de columnas.");
                    System.out.println("La suma de la \"x\" y las filas o la \"y\" y las columnas no debe superar el máximo del tablero.");
                    in.nextLine();
                }
            }
        }
    }
    
    private static Jugador elegirJugador(Sistema sistema){
        Scanner in = new Scanner(System.in);
        List<Jugador> jugadores = sistema.getJugadores();
        Jugador jugadorElegido = jugadores.get(0);
        int i = 0;
        int jugadorId = -1;
        System.out.println("*******ELEGIR JUGADOR*********");
        System.out.println();
        for (Jugador jugador : jugadores) {
            i++;
            System.out.println(i + " - " + jugador.getNombre() + " (" + jugador.getAlias() + ")." );
        }
        System.out.println();
        System.out.println("Seleccione un jugador apretando el número que tiene en la lista y luego enter.");
        System.out.println("*******ELEGIR JUGADOR*********");
        
        boolean jugadorValidado = false;
        while(!jugadorValidado){
            jugadorId = leerEntero() - 1;
            try {            
                jugadorElegido = jugadores.get(jugadorId);
                jugadorValidado = true;
                System.out.println("Se ha seleccionado al jugador: " + jugadorElegido.getNombre() + " (" + jugadorElegido.getAlias() + ").");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Debe ingresar un número de la lista. Por favor, intente nuevamente.");
            }
        }
        return jugadorElegido;    
    }
    
    private static int elegirConfiguracion(Sistema sistema){
        System.out.println("*******ELEGIR CONFIGURACION*********");
        System.out.println();
        System.out.println("1-Predeterminada");
        System.out.println("2-Aleatoria");
        System.out.println();
        System.out.println("Seleccione la configuración apretando el número que tiene en la lista y luego enter.");
        boolean correcto = false;
        int configuracion = 0;
        while(!correcto){
            configuracion = leerEntero();
            if(configuracion == 1 || configuracion == 2){
                correcto = true;
            } else {
                System.out.println("Debe ingresar un número de la lista. Por favor, intente nuevamente.");
            }
        }
        System.out.println();
        System.out.println("*******ELEGIR CONFIGURACION*********");
        return configuracion;
    }
    private static int leerEntero(){
        Scanner in = new Scanner(System.in);
        boolean correcto = false;
        int entero = 0;
        while (!correcto){ 
            try{
                entero = Integer.parseInt(in.nextLine());
                correcto = true;
            } 
            catch(NumberFormatException e){ 
                System.out.println("Debe ingresar un número. Por favor, intente nuevamente.");
            } 
        }
        return entero;
    }

    private static void dibujarTablero(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        String [][] matrizJuego;
        if(sistema.getJuego().getClass() == Saltar.class){
            matrizJuego = ((Saltar)sistema.getJuego()).getMatrizJuego();

            System.out.println("*******  SALTAR  *********");
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
                    String letra = Objects.equals(matrizJuego[i][j], "") ? matrizJuego[i][j] = " " : matrizJuego[i][j];
                    System.out.print(letra);

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

            System.out.println("Juega " + sistema.getJuego().getColorJugadaActual());
            System.out.println("*******  SALTAR  *********");

        } else if (sistema.getJuego().getClass() == Rectangulo.class){
            System.out.println("*******  RECTANGULO  *********");
            matrizJuego = ((Rectangulo)sistema.getJuego()).getMatrizJuego();
            System.out.println("     1 2 3 4 5 6 7 8 9 1 1 1 1 1 1 1 1 1 1 2");
            System.out.println("                       0 1 2 3 4 5 6 7 8 9 0");
            for (int i = 0; i < matrizJuego.length; i++) {
                if(i<10){
                    System.out.print("0" + i + "  ");
                }else{
                    System.out.print(i + "  ");
                }

                for (int j = 0; j < matrizJuego[0].length; j++) {
                    System.out.print(" " + matrizJuego[i][j]);
                }
                    System.out.println();

            }
            System.out.println("Juega " + sistema.getJuego().getColorJugadaActual());
            System.out.println("*******  RECTANGULO  *********");
        } else {
            System.out.println("*******  No existe ese juego  *********");
        }

    }

}
