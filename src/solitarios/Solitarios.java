package solitarios;

import java.util.InputMismatchException;
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
    public static void registrar(Sistema sistema) {
        clearScreen();
        boolean quiereRegistrar = true;
        String nombre = "";
        String alias = "";
        int edad = 0;        
        Scanner in = new Scanner(System.in);
        while(quiereRegistrar){
            System.out.println("*******REGISTRAR JUGADOR*********");
            
            if(sistema.validarCantidadJugadores()){
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
                boolean correcto = false; 
                while (!correcto){ 
                    try{
                        edad = Integer.parseInt(in.nextLine());
                        if(sistema.validarEdad(edad)){
                            correcto = true;
                        } else {
                            System.out.println("La  edad debe ser entre 0 y 99. Intente nuevamente: ");
                            correcto = false;  
                        }
                    } 
                    catch(NumberFormatException e){ 
                        System.out.println("Debe ingresar un número. Por favor, intente nuevamente.");
                    } 
                }
                               
                Jugador j = new Jugador(nombre,alias,edad);
                sistema.registrarJugador(j);
                
                System.out.println("El jugador ha sido creado exitosamente! Pulse una tecla para continuar ..");                
                in.nextLine(); 
                
                System.out.println("Si desea continuar continuar ingresando jugadores presione 'S' de lo contrario, presione cualquier tecla: ");
                System.out.println();
                System.out.println("*******REGISTRAR JUGADOR*********");
                opcion = in.nextLine();
                if(!opcion.equalsIgnoreCase("S")){
                    quiereRegistrar = false;
                }
            } else {
                System.out.println();
                System.out.println("Ya se ha alcanzado el máximo de jugadores (4). Presione cualquier tecla para continuar .. ");
                System.out.println();
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
