package xtartManager.interfaz;

import xtartManager.gestion.GestionEquipos;
import xtartManager.gestion.Tienda;
import xtartManager.modelo.competicion.Liga;
import xtartManager.modelo.equipos.Equipo;
import xtartManager.modelo.persona.Jugador;
import xtartManager.modelo.persona.Persona;
import xtartManager.modelo.persona.Posicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static xtartManager.interfaz.Inicio.tienda;

public class MenuPrincipal {

    public static Scanner sc = new Scanner(System.in);

    private Liga copaRey;
    private Liga supercopa;
    private Liga ligaSpain;

    private String temporadaActual = "26/27";

    public MenuPrincipal() {
        crearTemporada();
    }

    public void iniciarMenu(){

        comprobarTemporada();
        int opcion = 0;

        do {
            comprobarTemporada();
            System.out.println("\n======================================");
            System.out.println("¡Bienvenido a Xtart Manager! ");
            System.out.println("1. Ver Equipos y Jugadores. ");
            System.out.println("2. Jugar una Liga. ");
            System.out.println("3. Salir. ");

            opcion = Errores.comprobar(sc, "¿Qué acción deseas realizar?", 1, 3);

            switch (opcion) {
                case 1 -> Inicio.mostrarResumen();
                case 2 -> menuLigas();
                case 3 -> {
                    System.out.println("Saliendo del juego...");
                    System.exit(0);
                }
                default-> System.out.println("Opción inválida, inténtalo de nuevo");
            }
        } while (opcion != 3);
    }

    public void menuLigas(){

        int opcion= 0;
        comprobarTemporada();

        do{
            comprobarTemporada();
            System.out.println("\n======================================");
            System.out.println("1. Copa del Rey");
            System.out.println("2. Supercopa");
            System.out.println("3. Liga Española");
            System.out.println("4. Volver al menú principal");

            opcion = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 4);

            switch (opcion) {
                case 1 -> menuLigaOpciones(copaRey);
                case 2 -> menuLigaOpciones(supercopa);
                case 3 -> menuLigaOpciones(ligaSpain);
                case 4 -> {
                    System.out.println("Volviendo al menú principal...");
                    iniciarMenu();
                }
                default -> System.out.println("Opción inválida, inténtalo de nuevo");
            }
        }while (opcion != 4);
    }

    public void menuLigaOpciones(Liga liga){

        int opcion;
        boolean salir = false;
        comprobarTemporada();

        if (liga.estaFinalizada()) {

            do{
                System.out.println("\n======================================");
                System.out.println("La liga " + liga.getNombre() + " " + temporadaActual + " ha terminado.");
                System.out.println("1. Ver la Clasificación de Equipos. ");
                System.out.println("2. Ver la Clasificación de Jugadores. ");
                System.out.println("3. Volver al Menú de Ligas. ");
                opcion = Errores.comprobar(sc, "¿Qué deseas hacer?\n", 1, 3);

                switch (opcion) {
                    case 1 -> liga.verClasificacion();
                    case 2 -> liga.verEstadisticasJugadores();
                    case 3 -> {
                        System.out.println("Volviendo al menú de ligas...");
                        menuLigas();
                    }
                    default -> System.out.println("Opción inválida, inténtalo de nuevo");
                }

            }while(!salir);
        }

        while (!liga.estaFinalizada()) {

            do{

                System.out.println("\n======================================");
                System.out.println("¡Bienvenido a " + liga.getNombre() + " " + temporadaActual + ". ");
                System.out.println("1. Jugar la siguiente Jornada. ");
                System.out.println("2. Jugar todo el calendario de " + liga.getNombre() + " " + temporadaActual);
                System.out.println("3. Ir a la Tienda. ");
                System.out.println("4. Ver la Clasificación de Equipos. ");
                System.out.println("5. Ver la Clasificación de Jugadores. ");
                System.out.println("6. Volver al Menú de Ligas. ");
                opcion = Errores.comprobar(sc, "¿Qué deseas hacer?\n", 1, 6);

                switch (opcion) {
                    case 1 -> {
                        liga.jugarJornada();
                        // mostrar Resultados Ultima Jornada !!!!
                        if (liga.estaFinalizada()) {
                            System.out.println("Liga finalizada.");
                            menuLigas();
                        }
                    }
                    case 2 -> {
                        liga.jugarCalendario();
                        // mostrar Resultados Totales Liga !!!!
                        if (liga.estaFinalizada()) {
                            System.out.println("Liga finalizada.");
                            menuLigas();
                        }
                    }
                    case 3 -> menuTienda(liga);
                    case 4 -> liga.verClasificacion();
                    case 5 -> liga.verEstadisticasJugadores();
                    case 6 -> {
                        System.out.println("Volviendo al menú de ligas...");
                        menuLigas();
                    }
                    default -> System.out.println("Opción inválida, inténtalo de nuevo");
                }

            }while(!salir);

        }

        comprobarTemporada();
    }

    public void menuTienda(Liga liga){

        int opcion= 0;
        boolean salir = false;

        do{

            System.out.println("\n======================================");
            System.out.println("ADVERTENCIA: Te recordamos que al comprar una leyenda tendrás que expulsar a un jugador del equipo con la misma posición. ");
            System.out.println("1. Ver leyendas "); //que valgan 100 000
            System.out.println("2. Ver equipos de la " + liga.getNombre() + " " + temporadaActual + ". ");
            System.out.println("3. Comprar leyendas "); // con que equipo quieres comprar -- leyendas
            System.out.println("4. Volver al menú anterior. ");

            opcion = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 4);

            switch (opcion) {
                case 1 -> Inicio.mostrarLeyendasTienda();
                case 2 -> verEquipos(liga);
                case 3 -> comprarTienda(liga);
                case 4 -> menuLigaOpciones(liga);
                default -> System.out.println("Opción inválida, inténtalo de nuevo");
            }
        }while (!salir);

    }

    //region TEMPORADAS

    private void crearTemporada() {

        copaRey = new Liga("Copa del Rey", temporadaActual, GestionEquipos.getEquiposCopaDelRey());
        supercopa = new Liga("Supercopa", temporadaActual, GestionEquipos.getEquiposSuperCopa());
        ligaSpain = new Liga("Liga Española", temporadaActual, GestionEquipos.getEquiposLigaSpain());

        copaRey.calendario(GestionEquipos.getEquiposCopaDelRey());
        supercopa.calendario(GestionEquipos.getEquiposSuperCopa());
        ligaSpain.calendario(GestionEquipos.getEquiposLigaSpain());

    }

    private void comprobarTemporada(){

        if (copaRey.estaFinalizada() && supercopa.estaFinalizada() && ligaSpain.estaFinalizada()) {
            menuFinTemporada();
        }
    }

    private void menuFinTemporada(){
        int opcion;
        boolean salir = false;

        do {
            System.out.println("\n===== LA TEMPORADA " + temporadaActual + " HA FINALIZADO =====");
            System.out.println("1. Ver clasificación Copa del Rey");
            System.out.println("2. Ver clasificación Supercopa");
            System.out.println("3. Ver clasificación Liga Española");
            System.out.println("4. Ver estadísticas jugadores Copa del Rey");
            System.out.println("5. Ver estadísticas jugadores Supercopa");
            System.out.println("6. Ver estadísticas jugadores Liga Española");
            System.out.println("7. Iniciar nueva temporada");

            opcion = Errores.comprobar(sc, "Elige una opción:", 1, 7);

            switch (opcion) {
                case 1 -> copaRey.verClasificacion();
                case 2 -> supercopa.verClasificacion();
                case 3 -> ligaSpain.verClasificacion();
                case 4 -> copaRey.verEstadisticasJugadores();
                case 5 -> supercopa.verEstadisticasJugadores();
                case 6 -> ligaSpain.verEstadisticasJugadores();
                case 7 -> {
                    System.out.println("Iniciando nueva temporada...");
                    cambiarTemporada();
                }
            }
        }while (!salir) ;
    }

    private void cambiarTemporada(){

        temporadaActual = calcularTemporada(temporadaActual);

        //GestionEquipos.asignarJugadoresEquipos();
        Tienda.nuevaTemporada();
        copaRey.reiniciarLiga();
        supercopa.reiniciarLiga();
        ligaSpain.reiniciarLiga();

        crearTemporada();

        System.out.println("Nueva temporada iniciada: " + temporadaActual + ". Pulsa ENTER para continuar. ");
        sc.nextLine();
        iniciarMenu();
    }

    private String calcularTemporada(String temporadaActual) {

        String[] partes = temporadaActual.split("/");
        int year1 = Integer.parseInt(partes[0]);
        int year2 = Integer.parseInt(partes[1]);

        year1++;
        year2++;

        return year1 + "/" + year2;
    }

    //endregion

    //region TIENDA

    private void verEquipos(Liga liga){

        int opcion;

        System.out.println("\n=========== EQUIPOS DE LA " + liga.getNombre() + " " + temporadaActual + " ===========");

        for (int i = 0; i < liga.getEquipos().size(); i++) {
            Equipo e = liga.getEquipos().get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " " + " | Presupuesto: " + e.getPresupuesto());
        }
        System.out.println((liga.getEquipos().size() + 1) + ". Volver");

        opcion = Errores.comprobar(sc,"¿Quieres ver los jugadores de algún equipo?",1,liga.getEquipos().size() + 1);

        if (opcion == liga.getEquipos().size() + 1) {
            return;
        }

        Equipo equipoSeleccionado = liga.getEquipos().get(opcion - 1);

        System.out.println("\n====== Plantilla de " + equipoSeleccionado.getNombre() + " ======");

        for (Persona p : equipoSeleccionado.getStaff()) {
            if (p instanceof Jugador j) {
                System.out.println("- " + j.getNombre() + " " + j.getApellido() + " " + " | Media: " + j.getMedia() + " | Posición: " + j.getPosicion());
            }
        }

        System.out.println("\nPulsa ENTER para volver...");
        sc.nextLine();
    }

    private void comprarTienda(Liga liga) {

        int opcion, opcion2;

        List<Equipo> equipos = liga.getEquipos();
        List<Equipo> equiposPresupuesto = new ArrayList<>();

        //region EQUIPOS CON PRESUPUESTO
        for (Equipo e : equipos) {
            if (e.getPresupuesto() >= 100000) {
                equiposPresupuesto.add(e);
            }
        }

        if (equiposPresupuesto.isEmpty()) {
            System.out.println("Ningún equipo tiene dinero suficiente para comprar leyendas.");
            return;
        }
        //endregion

        //region ELEGIR EQUIPO
        System.out.println("\nElige el Equipo que va a comprar a la Leyenda:");
        for (int i = 0; i < equiposPresupuesto.size(); i++) {
            Equipo e = equiposPresupuesto.get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " - Presupuesto: " + e.getPresupuesto());
        }
        opcion = Errores.comprobar(sc, "¿Qué equipo eliges?", 1, equiposPresupuesto.size());
        Equipo equipo = equiposPresupuesto.get(opcion - 1);
        //endregion

        //region ELEGIR LEYENDA
        List<Jugador> leyendas = tienda.getJugadoresEnVenta();
        System.out.println("\nElige la leyenda que quieres comprar:");

        for (int i = 0; i < leyendas.size(); i++) {
            Jugador l = leyendas.get(i);
            System.out.println((i + 1) + ". " + l.getNombre() + " " + l.getApellido() + " | Posición: " + l.getPosicion() + " | Media: " + l.getMedia() + " | Precio: 100 000€");
        }

        opcion2 = Errores.comprobar(sc, "¿Qué jugador deseas comprar?", 1, leyendas.size());
        Jugador leyendaElegida = leyendas.get(opcion2 - 1);
        Posicion posicion = leyendaElegida.getPosicion();
        //endregion

        //region ELEGIR JUGADOR EXPULSADO
        List<Jugador> jugadoresMismaPosicion = new ArrayList<>();

        for (Persona p : equipo.getStaff()) {
            if (p instanceof Jugador j && j.getPosicion() == posicion) {
                jugadoresMismaPosicion.add(j);
            }
        }

        if (jugadoresMismaPosicion.isEmpty()) {
            System.out.println("No hay jugadores en el equipo con esa posición para expulsar. Compra cancelada.");
            return;
        }

        System.out.println("\nElige el jugador a expulsar para reemplazarlo:");

        for (int i = 0; i < jugadoresMismaPosicion.size(); i++) {
            Jugador j = jugadoresMismaPosicion.get(i);
            System.out.println((i + 1) + ". " + j.getNombre() + " " + j.getApellido() + " | Media: " + j.getMedia());
        }

        int eleccionExpulsion = Errores.comprobar(sc, "¿Qué jugador expulsas?", 1, jugadoresMismaPosicion.size());
        Jugador jugadorExpulsado = jugadoresMismaPosicion.get(eleccionExpulsion - 1);
        //endregion

        //region REEMPLAZAR JUGADOR
        boolean ok = equipo.reemplazarJugador(jugadorExpulsado, leyendaElegida);

        if (ok) {
            equipo.setPresupuesto(equipo.getPresupuesto() - 100000);
            tienda.getJugadoresEnVenta().remove(leyendaElegida);
            System.out.println("\nSe ha comprado la leyenda " + leyendaElegida.getNombre() + " " + leyendaElegida.getApellido() + " y expulsado a " + jugadorExpulsado.getNombre() + " " + jugadorExpulsado.getApellido() + ".");
            System.out.println("Equipo actualizado:");
            for (Persona p : equipo.getStaff()) {
                if (p instanceof Jugador j) {
                    System.out.println("- " + j.getNombre() + " " + j.getApellido() + " | Posición: " + j.getPosicion());
                }
            }
        } else {
            System.out.println("No se pudo realizar la compra. Revisa los jugadores seleccionados.");
        }

        System.out.println("\nVolviendo al menú anterior...");
        menuLigaOpciones(liga);
        //endregion
    }

    //endregion

}
