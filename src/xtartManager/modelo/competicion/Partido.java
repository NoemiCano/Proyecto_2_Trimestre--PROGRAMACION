package xtartManager.modelo.competicion;

import xtartManager.clasificaciones.CalculadoraFutbol;
import xtartManager.modelo.equipos.Equipo;
import xtartManager.modelo.persona.Jugador;
import xtartManager.modelo.persona.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Partido {
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean jugado = false;
    private List<String> nombresGoleadores = new ArrayList<>();

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
        double fuerzaLocal = local.calcularFuerzaAtaque() + VENTAJA_LOCAL;
        double fuerzaVisitante = visitante.calcularFuerzaAtaque();

        this.golesLocal = generarGoles(fuerzaLocal);
        this.golesVisitante = generarGoles(fuerzaVisitante);
        this.jugado = true;

        golesIndividuales(local, golesLocal);
        golesIndividuales(visitante, golesVisitante);

        gestionarTarjetas(local);
        gestionarTarjetas(visitante);

        for (Persona p : visitante.getStaff()) {
            if (p instanceof Jugador j) {
                if (Math.random() < 0.03) {
                    j.setTarjetasAmarillas(j.getTarjetasAmarillas() + 1);
//                    System.out.println("AMARILLA para " + j.getNombre() + " (" + visitante.getNombre() + ")");
                }
            }
        }

        // Actualizar clasificación
        local.getClasificacion().registrarResultado(golesLocal, golesVisitante);
        visitante.getClasificacion().registrarResultado(golesVisitante, golesLocal);

        premiosYMedia(local, golesLocal, golesVisitante);
        premiosYMedia(visitante, golesVisitante, golesLocal);

        // Mostrar resultado por consola
//        System.out.println("\n--- FINAL DEL PARTIDO ---");
//        System.out.println(local.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + visitante.getNombre());
//        System.out.println("--------------------------\n");


    }

    private void golesIndividuales(Equipo e, int goles) {
        for (int i = 0; i < goles; i++) {
            Jugador goleador = e.elegirDelanteroAlAzar();
            if (goleador != null) {
                goleador.sumarGoles(1);
                nombresGoleadores.add(goleador.getNombre() + " (" + e.getNombre() + ")");
//                System.out.println("¡GOL del " + e.getNombre() + "! Marcó: " + goleador.getNombre());
            }
        }
    }

    public String obtenerGoleadoresBreve() {
        if (nombresGoleadores.isEmpty()) return "Sin goles";
        return String.join(", ", nombresGoleadores);
    }

    private void gestionarTarjetas(Equipo e) {
        for (Persona p : e.getStaff()) {
            if (p instanceof Jugador j) {
                if (Math.random() < 0.03) {
                    j.setTarjetasAmarillas(j.getTarjetasAmarillas() + 1);
//                    System.out.println("AMARILLA: " + j.getNombre() + " [" + e.getNombre() + "]");
                }
            }
        }
    }

    private void premiosYMedia(Equipo e, int misGoles, int golesRival) {
        int puntos = CalculadoraFutbol.calcularPuntos(misGoles, golesRival);

        // Dinero
        if (puntos == 3) e.ingresarDinero(20000);
        else if (puntos == 1) e.ingresarDinero(10000);

        // Actualizar media de los jugadores
        for (Persona p : e.getStaff()) {
            if (p instanceof Jugador j) {
                j.actualizarMediaSegunResultado(puntos);
            }
        }
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
