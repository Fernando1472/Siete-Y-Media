package sieteymedia;

import recursos.Baraja;
import recursos.Carta;

public class SieteYMedia {
    private Baraja baraja;
    private Carta[] cartasJugador;
    private Carta[] cartasBanca;

    public SieteYMedia() {
        baraja = new Baraja();
        baraja.barajar();
        cartasJugador = new Carta[15];
        cartasBanca = new Carta[15];
    }

    // Metodo para mostrar las cartas del jugador
    public String mostrarCartasJugador() {
        StringBuilder sb = new StringBuilder("Cartas del jugador: ");
        for (Carta carta : cartasJugador) {
            if (carta != null) {
                sb.append(carta.toString()).append(" ");
            }
        }
        return sb.toString();
    }

    // Metodo para calcular el valor de las cartas del jugador
    public double valorCartasJugador() {
        double total = 0.0;
        for (Carta carta : cartasJugador) {
            if (carta != null) {
                int valor = carta.getNumero();
                total += (valor > 7) ? 0.5 : valor; // Las figuras (10, 11, 12) valen 0.5
            }
        }
        return total;
    }

    // Metodo para dar una carta al jugador
    public void darCartaJugador() {
        Carta c = baraja.darCartas(1)[0];
        insertarCartaEnArray(cartasJugador, c);
    }

    // Metodo para dar una carta a la banca
    public void darCartaBanca() {
        Carta c = baraja.darCartas(1)[0];
        insertarCartaEnArray(cartasBanca, c);
    }

    // Metodo para insertar una carta en el array de cartas del jugador
    private void insertarCartaEnArray(Carta[] cartas, Carta c) {
        int i = 0;
        while (cartas[i] != null) {
            i++;
        }
        cartas[i] = c;
    }

    // Metodo para mostrar las cartas de la banca
    public String mostrarCartasBanca() {
        StringBuilder sb = new StringBuilder("Cartas de la banca: ");
        for (Carta carta : cartasBanca) {
            if (carta != null) {
                sb.append(carta.toString()).append(" ");
            }
        }
        return sb.toString();
    }

    // Metodo para calcular el valor de las cartas de la banca
    public double valorCartasBanca() {
        double total = 0.0;
        for (Carta carta : cartasBanca) {
            if (carta != null) {
                int valor = carta.getNumero();
                total += (valor > 7) ? 0.5 : valor; // Las figuras (10, 11, 12) valen 0.5
            }
        }
        return total;
    }

    // Metodo para presentar las reglas del juego
    public String presentarJuego() {
        return "- El usuario es el jugador y el ordenador la banca.\n" +
                "- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.\n" +
                "- Las figuras (10-sota, 11-caballo y 12-rey) valen medio punto, y el resto, su valor.\n" +
                "- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.\n" +
                "- El jugador puede plantarse en cualquier momento.\n" +
                "- Si la suma de los valores de las cartas sacadas es superior a 7 y medio, el jugador 'se pasa' y pierde.\n" +
                "- Si el jugador no se pasa, la banca juega para empatar o superar sin pasarse de 7.5.\n" +
                "- Si la banca se pasa, gana el jugador.\n" +
                "- ¡Empecemos!";
    }
}