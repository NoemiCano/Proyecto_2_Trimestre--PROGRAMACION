package xtartManager.clasificaciones;

import xtartManager.modelo.equipos.Equipo;

import java.util.ArrayList;
import java.util.List;

public class ClasificacionEquipos {
    private int puntos;
    private int partidosJugados;
    private int victorias;
    private int empates;
    private int derrotas;
    private int golesAFavor;
    private int golesEnContra;

    public ClasificacionEquipos() {
        this.puntos = 0;
        this.partidosJugados = 0;
        this.victorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int getDiferenciaGoles() {
        return this.golesAFavor - this.golesEnContra;
    }

    public void registrarResultado(int misGoles, int golesRival) {
        this.partidosJugados++;
        this.golesAFavor += misGoles;
        this.golesEnContra += golesRival;

        int puntosGanados = CalculadoraFutbol.calcularPuntos(misGoles, golesRival);
        this.puntos += puntosGanados;

        if (puntosGanados == 3) {
            this.victorias++;

        } else if (puntosGanados == 1) {
            this.empates++;

        } else {
            this.derrotas++;

        }
    }

    public static void mostrarTabla(List<Equipo> listaEquipos, String nombreCompeticion) {
        // 1. Copiamos y Ordenamos
        List<Equipo> ordenada = new ArrayList<>(listaEquipos);
        ordenada.sort((e1, e2) -> {
            int p1 = e1.getClasificacion().getPuntos();
            int p2 = e2.getClasificacion().getPuntos();
            if (p1 != p2) return Integer.compare(p2, p1);
            return Integer.compare(e2.getClasificacion().getDiferenciaGoles(),
                    e1.getClasificacion().getDiferenciaGoles());
        });

        // 2. Imprimimos (el código de los printf que ya tienes)
        System.out.println("\n--- TABLA: " + nombreCompeticion + " ---");
        System.out.printf("%-20s | %-4s | %-3s | %-3s%n", "Equipo", "PTS", "PJ", "DG");

        for (Equipo e : ordenada) {
            ClasificacionEquipos c = e.getClasificacion();
            System.out.printf("%-20s | %-4d | %-3d | %-3d%n",
                    e.getNombre(), c.getPuntos(), c.getPartidosJugados(), c.getDiferenciaGoles());
        }
    }


    @Override
    public String toString() {
        return "ClasificacionEquipos{" +
                "puntos=" + puntos +
                ", partidosJugados=" + partidosJugados +
                ", victorias=" + victorias +
                ", empates=" + empates +
                ", derrotas=" + derrotas +
                ", golesAFavor=" + golesAFavor +
                ", golesEnContra=" + golesEnContra +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClasificacionEquipos that = (ClasificacionEquipos) o;
        return puntos == that.puntos && partidosJugados == that.partidosJugados && victorias == that.victorias && empates == that.empates && derrotas == that.derrotas && golesAFavor == that.golesAFavor && golesEnContra == that.golesEnContra;
    }

}
