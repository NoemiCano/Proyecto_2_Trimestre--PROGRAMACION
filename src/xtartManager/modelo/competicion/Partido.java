package xtartManager.modelo.competicion;

import xtartManager.modelo.equipos.Equipo;
import xtartManager.modelo.persona.Jugador;

import java.util.Objects;

public final class Partido {
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean jugado = false;

    private static final double VENTAJA_LOCAL = 5.0;

    public Partido() {
    }

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    // region GETTER & SETTER


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

    // endregion

    private int generarGoles(double fuerzaAtaque){
        int goles = 0;

        for (int i = 0; i < 5; i++){

            double azar = Math.random() * 100;

            if (azar < (fuerzaAtaque / 3)) {
                goles++;
            }
        }

        return goles;
    }

    public void jugarPartido(){
        double fuerzaLocal = local.calcularFuerzaAtaque();
        double fuerzaVisitante = visitante.calcularFuerzaAtaque();

        fuerzaLocal += VENTAJA_LOCAL;

        this.golesLocal = generarGoles(fuerzaLocal);
        this.golesVisitante = generarGoles(fuerzaVisitante);

        this.jugado = true;

        local.getClasificacion().registrarResultado(golesLocal, golesVisitante);
        visitante.getClasificacion().registrarResultado(golesVisitante, golesLocal);

        for (int i = 0; i < this.golesLocal; i++) {
            Jugador goleador = local.elegirDelanteroAlAzar();
            if (goleador != null) {
                goleador.sumarGoles(1); // Sumamos de 1 en 1
                System.out.println("¡GOL del " + local.getNombre() + "! Marcó: " + goleador.getNombre());
            }
        }

        for (int i = 0; i < this.golesVisitante; i++) {
            Jugador goleador = visitante.elegirDelanteroAlAzar();
            if (goleador != null) {
                goleador.sumarGoles(1);
                System.out.println("¡GOL del " + visitante.getNombre() + "! Marcó: " + goleador.getNombre());
            }
        }

        System.out.println(local.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + visitante.getNombre());

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
