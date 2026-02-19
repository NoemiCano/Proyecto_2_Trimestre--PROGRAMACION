package xtartManager.tienda;

import xtartManager.gestionPersonal.GestionPersonal;
import xtartManager.persona.Jugador;
import xtartManager.equipos.Equipo;

import java.util.ArrayList;
import java.util.List;

public class Tienda {

    private final List<Jugador> jugadoresEnVenta = new ArrayList<>();
    private boolean cargada = false;


    public List<Jugador> getJugadoresEnVenta() {
        return jugadoresEnVenta;
    }

    public boolean isCargada() {
        return cargada;
    }

    public void setCargada(boolean cargada) {
        this.cargada = cargada;
    }

    public void inicializarLeyendas() {
        if (cargada) return;

        GestionPersonal.cargarJugadoresDesdeTxt();
        for (Jugador j : GestionPersonal.getJugadores()) {
            if (j.getIdJugador() > 330 && j.getEquipo() == null) {
                jugadoresEnVenta.add(j);
            }
        }
        cargada = true;
    }

    public void nuevaTemporada() {
        jugadoresEnVenta.clear();
        cargada = false;
        inicializarLeyendas();
    }


    public boolean comprarLeyenda(int idJugador, Equipo destino) {
        if (destino == null) return false;

        Jugador elegido = null;
        for (Jugador j : jugadoresEnVenta) {
            if (j.getIdJugador() == idJugador) {
                elegido = j;
                break;
            }
        }

        if (elegido == null) return false;
        if (elegido.getEquipo() != null) return false;

        boolean ok = destino.ficharJugador(elegido);
        if (ok) jugadoresEnVenta.remove(elegido);

        return ok;
    }
}
