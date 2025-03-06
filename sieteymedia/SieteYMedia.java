package sieteymedia;

import java.util.Scanner;
import recursos.Baraja;
import recursos.Carta;

    public class SieteYMedia {
        Baraja baraja;
        Carta[] cartasJugador;
        Carta[] cartasBanca;
        Scanner sc = new Scanner(System.in);
        InterfaceConsola interfaz;

        public SieteYMedia() {
            baraja = new Baraja();
            baraja.barajar();
            // se van pidiendo cartas al jugar pero matemáticamente a partir de 15 siempre
            // nos pasamos
            // hay 12 cartas de medio puntos, si sacara estas 12 luego cartas con valor 1
            // vemos que a partir de 15 cartas siempre se pasas
            cartasJugador = new Carta[15];
            cartasBanca = new Carta[15];

            interfaz = new InterfaceConsola();
            interfaz.presentarJuego();
            jugar();
        }

        public static void main(String[] args) {
            new SieteYMedia();
        }

        void jugar() {
            turnoJugador();
            turnoBanca();
            interfaz.saludar();
        }

        void turnoJugador() {
            char opc = 'C';
            interfaz.reglaCartaJugador();

            while (valorCartas(cartasJugador) < 7.5 && opc == 'C') {
                Carta c = baraja.darCartas(1)[0];
                // insertamos c en las cartas del jugador
                insertarCartaEnArray(cartasJugador, c);

                interfaz.mostrarCartaValor();

                mostrarCartas(cartasJugador);
                double valor = valorCartas(cartasJugador);

                interfaz.valorCarta(valor);

                if (valor < 7.5) {
                    interfaz.eleccionJugardor();
                    opc = sc.next().trim().toUpperCase().charAt(0);
                }
            }
        }

        void turnoBanca() {
            // lo primero es consultar el valor que alcanzó el jugador en su turno
            double valorCartasJugador = valorCartas(cartasJugador);

            if (valorCartasJugador > 7.5) {
                interfaz.ganaBancaAntes();
                return;
            }

            interfaz.turnoBanca();

            // juega hasta empatar o superar
            while (valorCartas(cartasBanca) < valorCartasJugador) {
                Carta c = baraja.darCartas(1)[0];
                insertarCartaEnArray(cartasBanca, c);
            }

            interfaz.decirCartas();
            mostrarCartas(cartasBanca);

            interfaz.mostrarValoresCartasBanca(valorCartas(cartasBanca));

            if (valorCartas(cartasBanca) > 7.5) {
                interfaz.ganaJugardor();
            } else {
                interfaz.ganaBanca();
            }
        }

        double valorCartas(Carta[] cartas) {
            double total = 0.0;
            int val;
            int i = 0;
            while (cartas[i] != null) {
                val = cartas[i].getNumero();
                total += (val > 7) ? 0.5 : val;
                i++;
            }

            return total;
        }

        void insertarCartaEnArray(Carta[] cartas, Carta c) {
            // inserta al final detectando el primer null
            int i = 0;
            while (cartas[i] != null) {
                i++;
            }
            cartas[i] = c;

        }

        void mostrarCartas(Carta[] cartas) {
            int i = 0;
            while (cartas[i] != null) {
                interfaz.mostrarCartas(cartas[i]);
                i++;
            }
        }

    }


