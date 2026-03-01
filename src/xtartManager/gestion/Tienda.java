package xtartManager.gestion;

import xtartManager.modelo.persona.Jugador;
import xtartManager.modelo.equipos.Equipo;

import java.util.ArrayList;
import java.util.List;

public class Tienda {

    private static final List<Jugador> jugadoresEnVenta = new ArrayList<>();
    private static boolean cargada = false;


    public List<Jugador> getJugadoresEnVenta() {
        return jugadoresEnVenta;
    }

    public boolean isCargada() {
        return cargada;
    }

    public void setCargada(boolean cargada) {
        this.cargada = cargada;
    }

    public static void inicializarLeyendas() {
        if (cargada) return;

        GestionPersonal.cargarJugadoresDesdeTxt();
        for (Jugador j : GestionPersonal.getJugadores()) {
            if (j.getIdJugador() > 330 && j.getEquipo() == null) {
                jugadoresEnVenta.add(j);
            }
        }
        cargada = true;
    }

    public static void nuevaTemporada() {
        jugadoresEnVenta.clear();
        cargada = false;
        inicializarLeyendas();
    }


    public boolean comprarLeyenda(int idJugador, Equipo destino, Jugador expulsado) {
        if (destino == null || expulsado == null) return false;

        Jugador elegido = null;
        for (Jugador j : jugadoresEnVenta) {
            if (j.getIdJugador() == idJugador) {
                elegido = j;
                break;
            }
        }

        if (elegido == null || elegido.getEquipo() != null) return false;

        destino.reemplazarJugador(expulsado, elegido);
        destino.setPresupuesto(destino.getPresupuesto() - 100000);

        jugadoresEnVenta.remove(elegido);

        return true;
    }
}
