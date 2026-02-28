package xtartManager.interfaz;

import xtartManager.gestion.GestionEquipos;
import xtartManager.gestion.Tienda;
import xtartManager.modelo.competicion.Liga;
import xtartManager.modelo.equipos.Equipo;

import java.util.Scanner;

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

        int opcion = 0;

        do {
            System.out.println("\n ======================================");
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

        do{

            System.out.println("\n ======================================");
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
                    return;
                }
                default -> System.out.println("Opción inválida, inténtalo de nuevo");
            }
        }while (opcion != 4);
    }

    public void menuLigaOpciones(Liga liga){

        int opcion;

        while (!liga.estaFinalizada()) {

            do{

                System.out.println("\n ======================================");
                System.out.println("¡Bienvenido a " + liga.getNombre() + " " + temporadaActual + ". ");
                System.out.println("1. Jugar la siguiente Jornada. ");
                System.out.println("2. Jugar todo el calendario de la Copa del Rey " + temporadaActual);
                System.out.println("3. Ir a la Tienda. ");
                System.out.println("4. Ver la Clasificación de Equipos. ");
                System.out.println("5. Ver la Clasificación de Jugadores. ");
                System.out.println("6. Volver al Menú de Ligas. ");
                opcion = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 6);

                switch (opcion) {
                    case 1 -> {
                        liga.jugarJornada();
                        // mostrar Resultados Ultima Jornada !!!!
                    }
                    case 2 -> {
                        liga.jugarCalendario();
                        // mostrar Resultados Totales Liga !!!!
                    }
                    case 3 -> menuTienda(liga);
                    case 4 -> {
                        liga.verClasificacion();
                    }
                    case 5 -> {
                        liga.verEstadisticasJugadores();
                    } //ClasificacionJugadores();
                    case 6 -> {
                        System.out.println("Volviendo al menú de ligas...");
                        return;
                    }
                    default -> System.out.println("Opción inválida, inténtalo de nuevo");
                }

            }while(opcion!=6);

        }

        if (liga.estaFinalizada()) {
            System.out.println("La liga " + liga.getNombre() + " " + temporadaActual + " ha terminado.");
        }

        comprobarTemporada();
    }

    public void menuTienda(Liga liga){

        int opcion= 0;

        do{

            System.out.println("\n ======================================");
            System.out.println("ADVERTENCIA: Te recordamos que al comprar una leyenda tendrás que expulsar a un jugador del equipo con la misma posición. ");
            System.out.println("1. Ver leyendas "); //que valgan 100 000
            System.out.println("2. Ver equipos que pueden comprar ");
            System.out.println("3. Comprar leyendas "); // con que equipo quieres comprar -- leyendas
            System.out.println("4. Volver al menú anterior. ");

            opcion = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 4);

            switch (opcion) {
                case 1 -> Inicio.mostrarLeyendasTienda();
                case 2 -> verEquipos(liga);
                case 3 -> menuLigaOpciones(ligaSpain);
                case 4 -> {
                    System.out.println("Volviendo al menú anterior...");
                    return;
                }
                default -> System.out.println("Opción inválida, inténtalo de nuevo");
            }
        }while (opcion != 4);

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

            System.out.println("Temporada finalizada.");

            /*
             Permitir ver resultados antes de reiniciar la temporada:

             System.out.println("Temporada finalizada. Pulse Enter para iniciar nueva temporada.");
            sc.nextLine();
            */

            cambiarTemporada();
        }
    }

    private void cambiarTemporada(){

        temporadaActual = calcularTemporada(temporadaActual);

        Tienda.nuevaTemporada();
        copaRey.reiniciarLiga();
        supercopa.reiniciarLiga();
        ligaSpain.reiniciarLiga();
        crearTemporada();

        System.out.println("Nueva temporada iniciada: " + temporadaActual);

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

        System.out.println("\n =========== EQUIPOS DE LA LIGA ===========");
        for(Equipo e : liga.getEquipos()){
            System.out.println(e.getNombre() + " " + " | Presupuesto: " + e.getPresupuesto());
        }
    }

    //endregion


}
