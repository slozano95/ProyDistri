/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Santiago
 */
public class Jugador {
    public int idJugador;
    public int pEntrada;
    public int pSalida;
    
    public int poX;
    public int poY;

    public Jugador(String idJugador, String pEntrada, String pSalida) {
        this.idJugador = Integer.parseInt(idJugador);
        this.pEntrada = Integer.parseInt(pEntrada);
        this.pSalida = Integer.parseInt(pSalida);
        this.poX = 0;
        this.poY = 0;
    }

    @Override
    public String toString() {
        return "Jugador{" + "idJugador=" + idJugador + ", pEntrada=" + pEntrada + ", pSalida=" + pSalida + '}';
    }
    
}
