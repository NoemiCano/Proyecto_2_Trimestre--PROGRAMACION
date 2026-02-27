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
