package xtartManager.modelo.competicion;

import java.util.ArrayList;
import java.util.List;

public class Jornada {
    //private int id_jornada;
    //private Date fecha_inicio;
    //private Date fecha_final;
    private int numeroJornada;
    private List<Partido> partidos;
    private static final int NUMERO_PARTIDOS = 5;

    public Jornada() {
    }

    public Jornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
        this.partidos = new ArrayList<>();
    }

    // region GETTER & SETTER

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    // endregion

    public void addPartido(Partido partido) {
        if (partido == null) {
            System.out.println("Error: El partido no puede ser nulo.");
            return;
        }

        if (this.partidos.size() < NUMERO_PARTIDOS) {
            this.partidos.add(partido);
        } else {
            System.out.println("Error: Esta jornada ya tiene los 5 partidos permitidos.");
        }

    }

    public void mostrarResumenJornada(int numeroJornada) {
        System.out.println("\n=== RESUMEN JORNADA " + numeroJornada + " ===");

        System.out.printf("%-20s | %-7s | %-20s | %s%n", "LOCAL", "GOLES", "VISITANTE", "GOLEADOR");
        System.out.println("-".repeat(95));

        // Como estamos en la clase Jornada, usamos "this.partidos" (o como se llame tu lista de partidos)
        for (Partido p : this.partidos) {
            if (p.isJugado()) {
                String marcador = String.format("%2d - %-2d", p.getGolesLocal(), p.getGolesVisitante());
                System.out.printf("%-20s | %-7s | %-20s | %s%n",
                        p.getLocal().getNombre(),
                        marcador,
                        p.getVisitante().getNombre(),
                        p.obtenerGoleadoresBreve());
            }
        }
        System.out.println("-".repeat(95));
    }

    @Override
    public String toString() {
        return "Jornada:" +
                "Numero de Jornada=" + numeroJornada;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jornada jornada = (Jornada) o;
        return numeroJornada == jornada.numeroJornada;
    }

}
