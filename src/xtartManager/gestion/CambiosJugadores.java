package xtartManager.gestion;

import xtartManager.modelo.equipos.Equipo;

import java.util.Objects;

public class CambiosJugadores {

    private int idLeyenda;
    private int idJugadorOriginal;
    private Equipo equipo;

    public CambiosJugadores() {}

    public CambiosJugadores(int idLeyenda, int idJugadorOriginal, Equipo equipo) {
        this.idLeyenda = idLeyenda;
        this.idJugadorOriginal = idJugadorOriginal;
        this.equipo = equipo;
    }

    // region GETTER & SETTER

    public int getIdLeyenda() {
        return idLeyenda;
    }

    public void setIdLeyenda(int idLeyenda) {
        this.idLeyenda = idLeyenda;
    }

    public int getIdJugadorOriginal() {
        return idJugadorOriginal;
    }

    public void setIdJugadorOriginal(int idJugadorOriginal) {
        this.idJugadorOriginal = idJugadorOriginal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }


    // endregion


    @Override
    public String toString() {
        return "Movimientos Jugadores:" +
                " ID Leyenda: " + idLeyenda +
                ", ID Jugador Original: " + idJugadorOriginal +
                ", Equipo: " + equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CambiosJugadores that = (CambiosJugadores) o;
        return idLeyenda == that.idLeyenda && idJugadorOriginal == that.idJugadorOriginal && Objects.equals(equipo, that.equipo);
    }
}
