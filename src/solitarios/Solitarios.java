//Santiago Pereyra 245198
//Venancio Portillo 276560

package solitarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import sistema.Jugador;
import sistema.Sistema;
import solitarios.Juegos.Juego;
import solitarios.Juegos.Rectangulo;
import solitarios.Juegos.Saltar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import sistema.Bitacora;
import sistema.CriterioAlias;
import sistema.CriterioPuntaje;
import sistema.Partida;

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
        sistema.getBitacora().agregarPartida(new Partida(new Date(),j1, 10));
        sistema.getBitacora().agregarPartida(new Partida(new Date(),j2, 10));
        sistema.getBitacora().agregarPartida(new Partida(new Date(),j2, 5000));
        sistema.getBitacora().agregarPartida(new Partida(new Date(),j1, 1));
        sistema.getBitacora().agregarPartida(new Partida(new Date(),j1, 50));
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
                        System.out.println("Para poder jugar debe ingresar un jugador primero! Presione cualquier tecla para volver al men?? .. ");
                        in.nextLine();
                        in.nextLine();
                    }
                break;
                case 4:
                    clearScreen();              
                    bitacora(sistema);
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
        System.out.println("Seleccione una opci??n:");
        System.out.println("1-Registrar Jugador");
        System.out.println("2-Jugar a Saltar");
        System.out.println("3-Jugar a Rect??ngulo");
        System.out.println("4-Bit??cora");
        System.out.println("5-Fin");
        System.out.println("*******MENU*********");
    }
    public static void bitacora(Sistema sistema){
        Scanner in = new Scanner(System.in);
        String input = "A";
        Bitacora bitacora = sistema.getBitacora();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while(!input.equalsIgnoreCase("X")){
            clearScreen();
            
            if(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("X")){
                Collections.sort(bitacora.getPartidas(), new CriterioAlias());   
            } else if (input.equalsIgnoreCase("P") || input.equalsIgnoreCase("X")){
                Collections.sort(bitacora.getPartidas(), new CriterioPuntaje());
            } else {
                System.out.println("Debe elegir una de las opciones anteriores.");
            }
            System.out.println("*******BIT??CORA*********");
            if(bitacora.getPartidas().size() > 0){
                for (Partida partida : bitacora.getPartidas()){
                    System.out.print("Fecha: " + formatoFecha.format(partida.getComienzo()) + " | " );
                    System.out.print("Jugador: " + partida.getJugador().getNombre() + "[" + partida.getJugador().getAlias() + "]"  +  " (" + partida.getJugador().getEdad() + ")" + " | " );
                    if(sistema.getJuego().getClass() == Saltar.class){
                        System.out.println("Juego: Saltar | ");
                    }else{
                        System.out.println("Juego: Rect??ngulo | ");                        
                    }
                    System.out.println("Puntaje: " + partida.getPuntaje());
                }
                System.out.println("A - Ordenar por alias ASC");
                System.out.println("P - Ordenar por puntaje DESC");
            } else {
                System.out.println("La bitacora est?? vac??a. Debe jugar alg??n juego para registrarlo.");
            }
            System.out.println("X - Salir");
            System.out.println("*******BIT??CORA*********");
            input = in.nextLine();
        }
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
        while(!sistema.finJuego(args)){
            int validarJugada = -1;
            while(validarJugada != 0 && !juego.finJuego(args)){
                clearScreen();
                dibujarTablero(sistema);
                System.out.println("Puede mover las fichas de las siguientes columnas: ");
                for (int i = 0; i < ((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).size(); i++) {
                    System.out.println("Fila: "+((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).get(i).x+", Columna: "+(((Saltar)sistema.getJuego()).fichasColor(juego.getColorJugadaActual()).get(i).y+1));
                }
                System.out.println();
                System.out.println("Elija columna a mover");
                args = in.nextLine();
                if(!juego.finJuego(args)){
                    if(juego.validarArgumentosJugada(args)){
                        validarJugada = juego.validarJugada(args);
                        if(validarJugada == 1){
                            System.out.println("La posici??n de destino debe estar vac??a.");
                            System.out.println();
                            System.out.println("Presione cualquier tecla para continuar");
                            in.nextLine();
                        }
                        if(validarJugada == 2){
                            System.out.println("En el ??rea de base, no puede haber dos fichas del mismo color en la misma fila.");
                            System.out.println();
                            System.out.println("Presione cualquier tecla para continuar");
                            in.nextLine();
                        }
                        if(validarJugada == 3){
                            sistema.getJuego().colorAnterior();
                            System.out.println("La ficha m??s adelantada del color considerado en el tablero no puede avanzar solamente una posici??n");
                            System.out.println();
                            System.out.println("Presione cualquier tecla para continuar");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("La jugada debe ingresar un n??mero");
                        System.out.println("El mismo indica la columna desde la cual se realizar?? el salto.");
                        System.out.println("No se debe ingresar un n??mero que supere el m??ximo de columnas del tablero.");
                        System.out.println();
                        System.out.println("Presione cualquier tecla para continuar");
                        in.nextLine();
                    }
                }
            }
        }
        System.out.println("");
        System.out.println("El juego termin??. Tu puntaje fue: " + sistema.getPartida().getPuntaje());
        System.out.println("Presione cualquier tecla para continuar.. ");
        in.nextLine();
    } 
    private static void jugarRectangulo(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        String args = "";
        Juego juego = (Rectangulo)sistema.getJuego();
        while(!sistema.finJuego(args)){
            int validarJugada = -1;
            while(validarJugada != 0 && !juego.finJuego(args)){
                clearScreen();
                dibujarTablero(sistema);
                System.out.println("Ingrese Jugada:");
                args = in.nextLine();
                if(!juego.finJuego(args)){
                    if(juego.validarArgumentosJugada(args)){
                        validarJugada = juego.validarJugada(args);
                        if(validarJugada == 1){
                            System.out.println("El rect??ngulo elegido pisa alg??n tope.");
                            in.nextLine();
                        }
                        if(validarJugada == 2){
                            System.out.println("El rect??ngulo elegido pisa alg??n otro rect??ngulo.");
                            in.nextLine();
                        }
                        if(validarJugada == 3){
                            sistema.getJuego().colorAnterior();
                            System.out.println("El rect??ngulo elegido no es adyacente al anterior. (Jugada anterior color: " + sistema.getJuego().colorAnterior() + ")");
                            in.nextLine();
                        }
                    } else {
                        System.out.println("La jugada debe ser 4 n??meros del 1-20.");
                        System.out.println("El primero ind??ca la posici??n \"x\" de inicio.");
                        System.out.println("El segundo ind??ca la posici??n \"y\" de inicio. ");
                        System.out.println("El tercero ind??ca la cantidad de filas.");
                        System.out.println("El cuarto ind??ca la cantidad de columnas.");
                        System.out.println("La suma de la \"x\" y las filas o la \"y\" y las columnas no debe superar el m??ximo del tablero.");
                        in.nextLine();
                    }
                }
            }
        }
        
        System.out.println("");
        System.out.println("El juego termin??. Tu puntaje fue: " + sistema.getPartida().getPuntaje());
        System.out.println("Presione cualquier tecla para continuar.. ");
        in.nextLine();
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
        System.out.println("Seleccione un jugador apretando el n??mero que tiene en la lista y luego enter.");
        System.out.println("*******ELEGIR JUGADOR*********");
        
        boolean jugadorValidado = false;
        while(!jugadorValidado){
            jugadorId = leerEntero() - 1;
            try {            
                jugadorElegido = jugadores.get(jugadorId);
                jugadorValidado = true;
                System.out.println("Se ha seleccionado al jugador: " + jugadorElegido.getNombre() + " (" + jugadorElegido.getAlias() + ").");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Debe ingresar un n??mero de la lista. Por favor, intente nuevamente.");
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
        System.out.println("Seleccione la configuraci??n apretando el n??mero que tiene en la lista y luego enter.");
        boolean correcto = false;
        int configuracion = 0;
        while(!correcto){
            configuracion = leerEntero();
            if(configuracion == 1 || configuracion == 2){
                correcto = true;
            } else {
                System.out.println("Debe ingresar un n??mero de la lista. Por favor, intente nuevamente.");
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
                System.out.println("Debe ingresar un n??mero. Por favor, intente nuevamente.");
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
                if(i<9){
                    System.out.print("0" + (i+1) + "  ");
                }else{
                    System.out.print((i+1) + "  ");
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
