package xtartManager.competiciones.jornadas;

import xtartManager.equipos.Equipo;

import java.util.Objects;

public final class Partido {
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean jugado =false;

    private static final double VENTAJA_LOCAL = 5.0;

    public Partido() {
    }

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "local=" + local +
                ", visitante=" + visitante +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                ", jugado=" + jugado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return golesLocal == partido.golesLocal && golesVisitante == partido.golesVisitante && jugado == partido.jugado && Objects.equals(local, partido.local) && Objects.equals(visitante, partido.visitante);
    }
}
