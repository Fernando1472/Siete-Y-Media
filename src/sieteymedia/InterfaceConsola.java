package sieteymedia;

import java.util.Scanner;

public class InterfaceConsola {
    private Scanner scanner;
    private SieteYMedia juego;

    public InterfaceConsola() {
        scanner = new Scanner(System.in);
        juego = new SieteYMedia();
    }

    public void ejecutar() {
        // Mostrar las reglas del juego
        System.out.println(juego.presentarJuego());

        // Iniciar el turno del jugador
        boolean continuar = true;
        juego.darCartaJugador(); // El jugador recibe su primera carta.
        while (continuar) {
            // Mostrar cartas del jugador
            System.out.println(juego.mostrarCartasJugador());
            System.out.println("Valor de tus cartas: " + juego.valorCartasJugador());

            // Preguntar al jugador si quiere pedir carta o plantarse
            System.out.println("\n¿Pides [C]arta o te [P]lantas?");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("C")) {
                juego.darCartaJugador();
                // Si el jugador se pasa de 7.5, termina su turno
                if (juego.valorCartasJugador() > 7.5) {
                    System.out.println("Te has pasado de 7 y medio. ¡Perdiste!");
                    continuar = false;
                }
            } else if (respuesta.equalsIgnoreCase("P")) {
                continuar = false;
            } else {
                System.out.println("Opción no válida. Por favor, elige 'C' para pedir carta o 'P' para plantarte.");
            }
        }

        // Ahora es el turno de la banca
        System.out.println("\nTurno de la banca...");
        while (juego.valorCartasBanca() < juego.valorCartasJugador() && juego.valorCartasBanca() <= 7.5) {
            juego.darCartaBanca();
        }

        // Mostrar las cartas de la banca y su puntuación
        System.out.println(juego.mostrarCartasBanca());
        System.out.println("Valor de las cartas de la banca: " + juego.valorCartasBanca());

        // Determinar el ganador
        if (juego.valorCartasBanca() > 7.5) {
            System.out.println("La banca se ha pasado. ¡Ganaste!");
        } else if (juego.valorCartasBanca() >= juego.valorCartasJugador()) {
            System.out.println("La banca ha ganado.");
        } else {
            System.out.println("¡Ganaste!");
        }
    }

    public static void main(String[] args) {
        InterfaceConsola interfaz = new InterfaceConsola();
        interfaz.ejecutar();
    }
}