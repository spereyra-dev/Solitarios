package solitarios;

import java.util.Scanner;
import sistema.Jugador;
import sistema.Sistema;
import solitarios.Juegos.Juego;
import solitarios.Juegos.Saltar;

public class Solitarios {
    public static void main(String[] args) throws Exception {
        Sistema sistema = new Sistema();
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 5){
            menu();
            opcion = in.nextInt();
            switch(opcion){
                case 1:
                    registrar(sistema);
                break;
                case 2:  
                    jugar(sistema,"SALTAR");
                break;
                case 3:
                    jugar(sistema,"SALTAR");
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
    public static void registrar(Sistema sistema) throws Exception{
        clearScreen();
        boolean quiereRegistrar = true;
        String nombre;
        String alias;
        int edad;        
        Scanner in = new Scanner(System.in);
        while(quiereRegistrar){
            System.out.println("*******REGISTRAR JUGADOR*********");
            
            if(sistema.getJugadores().size() < 4){
                String opcion = "";
                System.out.println("Escriba su nombre: ");
                nombre = in.nextLine();
                System.out.println("Escriba su alias: ");
                alias = in.nextLine();
                System.out.println("Escriba su edad: ");
                edad = in.nextInt();
                in.nextLine();
                try{
                    Jugador j = new Jugador(nombre,alias,edad);
                    sistema.registrarJugador(j);
                } catch (Exception ex){
                    System.out.print(ex.getMessage());
                } 
                System.out.println("Si desea continuar continuar ingresando jugadores presione 'S' de lo contrario, presione cualquier tecla: ");
                opcion = in.nextLine();
                if(!opcion.equalsIgnoreCase("S")){
                    quiereRegistrar = false;
                }
                System.out.println("*******REGISTRAR JUGADOR*********");
            } else {
                System.out.println("Solo se puede ingresar hasta 4 Jugadores. Presione cualquier tecla para continuar");
                System.out.println("*******REGISTRAR JUGADOR*********");
                quiereRegistrar = false;
                in.nextLine();
            }
            
            clearScreen();
        }
    }
    public static void clearScreen() {  
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private static void jugar(Sistema sistema, String juegoStr) {
        sistema.elegirJuego(juegoStr);
        Juego j = sistema.getJuego();
        while(j.jugar()){
            System.out.println("*******JUGAR " + juegoStr + "*********");
            
            System.out.println("*******JUGAR " + juegoStr + "*********");
        }
    }
}
