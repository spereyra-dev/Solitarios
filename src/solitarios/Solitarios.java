package solitarios;

import java.util.Scanner;
import sistema.Jugador;
import sistema.Sistema;
import solitarios.Juegos.Saltar;

public class Solitarios {
    public static void main(String[] args) throws Exception {
        Sistema sistema = new Sistema();
        Scanner in = new Scanner(System.in);
        int opcion;
        menu();
        
        opcion = in.nextInt();
        switch(opcion){
            case 1:
                registrar(sistema);
                menu();
            break;
            case 2:  
                clearScreen();              
                System.out.println("*******JUGAR SALTAR*********");
                System.out.println("*******JUGAR SALTAR*********");
            break;
            case 3:
                clearScreen();              
                System.out.println("*******JUGAR RECTÁNGULO*********");
                System.out.println("*******JUGAR RECTÁNGULO*********");
            break;
            case 4:
                clearScreen();              
                System.out.println("*******BITÁCORA*********");
                System.out.println("*******BITÁCORA*********");
            break;
            case 5:
                clearScreen();
            break;
            default:
            break;
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
                in.nextLine();
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
            } else {
                System.out.println("Solo se puede ingresar hasta 4 Jugadores. Presione cualquier tecla para continuar");  
                in.nextLine();
            }
            
            System.out.println("*******REGISTRAR JUGADOR*********");
        }
    }
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
}
