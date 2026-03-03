package xtartManager.interfaz;

import xtartManager.modelo.equipos.Equipo;
import xtartManager.gestion.GestionEquipos;
import xtartManager.gestion.GestionPersonal;
import xtartManager.modelo.persona.Jugador;
import xtartManager.gestion.Tienda;
import java.util.List;

public class Inicio {

    public static final Tienda tienda = new Tienda();

    public static void iniciar() {

        GestionPersonal.cargarEntrenadoresDesdeTxt();
        GestionPersonal.cargarArbitrosDesdeTxt();
        GestionEquipos.asignarEstadiosUnicos();
        GestionEquipos.asignarEntrenadoresUnicos();
        GestionEquipos.asignarJugadoresEquipos();
        GestionEquipos.inicializarListasCompeticiones();
        tienda.inicializarLeyendas();



        System.out.println(Colores.VERDE + "Datos cargados correctamente." + Colores.RESET);
        System.out.println(Colores.AZUL + "Jugadores: " + GestionPersonal.getJugadores().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Entrenadores: " + GestionPersonal.getEntrenadores().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Árbitros: " + GestionPersonal.getArbitros().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Estadios: " +GestionEquipos.getEstadios().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Equipos Liga Española: " + GestionEquipos.getEquiposLigaSpain().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Equipos Copa del Rey: " + GestionEquipos.getEquiposCopaDelRey().size()+ Colores.RESET);
        System.out.println(Colores.AZUL + "Equipos Supercopa: " + GestionEquipos.getEquiposSuperCopa().size()+ Colores.RESET);


    }


    public static void mostrarResumen() {
        for (Equipo eq : GestionEquipos.getEquipos()) {
            System.out.println("======================================");
            System.out.println(Colores.VERDE + eq + Colores.RESET);

            if (eq.getEstadio() != null) {
                System.out.println(Colores.VERDE + "Estadio: " + eq.getEstadio().getNombre() +
                        " | Ciudad: " + eq.getEstadio().getCiudad() +
                        " | Capacidad: " + eq.getEstadio().getCapacidadEstadio()+ Colores.RESET);
            }

            if (eq.getEntrenador() != null) {
                System.out.println(Colores.VERDE + "Entrenador: " + eq.getEntrenador().getNombre() + " " + eq.getEntrenador().getApellido()+ Colores.RESET);
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
        System.out.println(Colores.VERDE + "=========== LEYENDAS EN TIENDA ===========" + Colores.RESET);

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
                            " | Altura: " + j.getAltura() +
                            " | Precio: 100 000€"
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

        mostrarEquiposCompeticion("Liga Española", GestionEquipos.getEquiposLigaSpain());
        mostrarEquiposCompeticion("Copa del Rey", GestionEquipos.getEquiposCopaDelRey());
        mostrarEquiposCompeticion("Supercopa", GestionEquipos.getEquiposSuperCopa());
    }

}
