package xtartManager.inicio;

import xtartManager.equipos.Equipo;
import xtartManager.equipos.GestionEquipos;
import xtartManager.gestionPersonal.GestionPersonal;
import xtartManager.persona.Jugador;
import xtartManager.tienda.Tienda;
import java.util.List;

public class Inicio {

    private static final Tienda tienda = new Tienda();

    public static void iniciar() {

        GestionPersonal.cargarEntrenadoresDesdeTxt();
        GestionPersonal.cargarArbitrosDesdeTxt();
        GestionEquipos.asignarEstadiosUnicos();
        GestionEquipos.asignarEntrenadoresUnicos();
        GestionEquipos.repartirOnce442SinLeyendas();
        GestionEquipos.inicializarListasCompeticiones();
        tienda.inicializarLeyendas();



        System.out.println("Datos cargados correctamente.");
        System.out.println("Jugadores: " + GestionPersonal.getJugadores().size());
        System.out.println("Entrenadores: " + GestionPersonal.getEntrenadores().size());
        System.out.println("Árbitros: " + GestionPersonal.getArbitros().size());
        System.out.println("Estadios: " +GestionEquipos.getEstadios().size());
        System.out.println("Equipos Liga Española: " + GestionEquipos.getEquiposLigaEspañola().size());
        System.out.println("Equipos Copa del Rey: " + GestionEquipos.getEquiposCopaDelRey().size());
        System.out.println("Equipos Supercopa: " + GestionEquipos.getEquiposSuperCopa().size());


    }


    public static void mostrarResumen() {
        for (Equipo eq : GestionEquipos.getEquipos()) {
            System.out.println("======================================");
            System.out.println(eq);

            if (eq.getEstadio() != null) {
                System.out.println("Estadio: " + eq.getEstadio().getNombre() +
                        " | Ciudad: " + eq.getEstadio().getCiudad() +
                        " | Capacidad: " + eq.getEstadio().getCapacidadEstadio());
            }

            if (eq.getEntrenador() != null) {
                System.out.println("Entrenador: " + eq.getEntrenador().getNombre() + " " + eq.getEntrenador().getApellido());
            }

            System.out.println("Jugadores (" + eq.getJugadores().size() + "):");
            for (Jugador j : eq.getJugadores()) {
                System.out.println(" - " + j.getPosicion() + " | " +
                        j.getNombre() + " " + j.getApellido() +
                        " | media=" + j.getMedia());
            }
        }
        System.out.println("======================================");
    }

    public static Tienda getTienda() {
        return tienda;
    }

    public static void mostrarLeyendasTienda() {
        System.out.println("=========== LEYENDAS EN TIENDA ===========");

        if (tienda.getJugadoresEnVenta().isEmpty()) {
            System.out.println("No hay leyendas disponibles.");
            return;
        }

        for (Jugador j : tienda.getJugadoresEnVenta()) {
            System.out.println(
                    "ID: " + j.getIdJugador() +
                            " | " + j.getNombre() + " " + j.getApellido() +
                            " | Pos: " + j.getPosicion() +
                            " | Media: " + j.getMedia() +
                            " | Altura: " + j.getAltura()
            );
        }

        System.out.println("Total: " + tienda.getJugadoresEnVenta().size());
        System.out.println("==========================================");
    }

    private static void mostrarEquiposCompeticion(String nombre, List<Equipo> lista) {
        System.out.println("=========== " + nombre.toUpperCase() + " ===========");

        int i = 1;
        for (Equipo e : lista) {
          
            System.out.println(i + ". " + e.getNombre());
            i++;
        }

        System.out.println("Total: " + lista.size());
        System.out.println("======================================");
    }

    public static void mostrarEquiposPorCompeticiones() {

        GestionEquipos.inicializarListasCompeticiones();

        mostrarEquiposCompeticion("Liga Española", GestionEquipos.getEquiposLigaEspañola());
        mostrarEquiposCompeticion("Copa del Rey", GestionEquipos.getEquiposCopaDelRey());
        mostrarEquiposCompeticion("Supercopa", GestionEquipos.getEquiposSuperCopa());
    }

}
