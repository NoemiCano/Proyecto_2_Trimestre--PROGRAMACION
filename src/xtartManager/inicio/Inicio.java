package xtartManager.inicio;

import xtartManager.gestionPersonal.GestionPersonal;

public class Inicio {

    public static void iniciar() {

        GestionPersonal.cargarJugadoresDesdeTxt();
        GestionPersonal.cargarEntrenadoresDesdeTxt();
        GestionPersonal.cargarArbitrosDesdeTxt();

        System.out.println("Datos cargados correctamente.");
        System.out.println("Jugadores: " + GestionPersonal.getJugadores().size());
        System.out.println("Entrenadores: " + GestionPersonal.getEntrenadores().size());
        System.out.println("Árbitros: " + GestionPersonal.getArbitros().size());
    }
}
