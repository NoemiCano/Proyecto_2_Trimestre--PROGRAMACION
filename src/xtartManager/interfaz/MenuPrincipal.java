package xtartManager.interfaz;

import xtartManager.gestion.GestionEquipos;
import xtartManager.gestion.Tienda;
import xtartManager.modelo.competicion.Liga;

import java.util.Scanner;

public class MenuPrincipal {

    public static Scanner sc = new Scanner(System.in);

    private boolean copaReyJugada = false;
    private boolean supercopaJugada = false;
    private boolean ligaSpainJugada = false;
    private Liga copaRey;
    private Liga supercopa;
    private Liga ligaSpain;

    private int ligasJugadas = 0;

    private String temporadaActual = "26/27";

    public void iniciarMenu(){

        int opcion = 0;

        System.out.println("¡Bienvenido a Xtart Manager!");
        System.out.println("1. Ver Equipos y Jugadores");
        System.out.println("2. Jugar una Liga.");

        opcion = Errores.comprobar(sc, "¿Qué acción deseas realizar?", 1, 3);

        if(opcion == 1){
            Inicio.iniciar();
        }else if(opcion == 2){
            menuLigas();
        }
    }

    public void menuLigas(){

        int opcion, opcion2 = 0;

        do{

        System.out.println("1. Copa del Rey");
        System.out.println("2. Supercopa");
        System.out.println("3. Liga Española");
        System.out.println("4. Volver");

        opcion = Errores.comprobar(sc, "¿Qué liga deseas jugar?", 1, 4);



        if(opcion == 1){

            if(!copaReyJugada){

                copaRey = new Liga("Copa del Rey", temporadaActual, GestionEquipos.getEquiposCopaDelRey());
                copaReyJugada = true;
                ligasJugadas++;
                copaRey.calendario(GestionEquipos.getEquiposCopaDelRey());

                System.out.println("1. Jugar todas las Jornadas.");
                System.out.println("2. Ver el resultado de la Copa del Rey " + temporadaActual);
                opcion2 = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 2);
                if(opcion2 == 1){
                    copaRey.jugarJornada();

                }else if(opcion2 == 2){
                    copaRey.jugarCalendario();

                }
            }else {
                System.out.println("Esta liga ya ha sido jugada esta temporada.");
                menuLigas();
            }

            comprobarTemporada();

        }else if(opcion == 2){

            if(!supercopaJugada){

                supercopa = new Liga("Supercopa", temporadaActual, GestionEquipos.getEquiposSuperCopa());
                supercopaJugada = true;
                ligasJugadas++;
                supercopa.calendario(GestionEquipos.getEquiposSuperCopa());

                System.out.println("1. Jugar todas las Jornadas.");
                System.out.println("2. Ver el resultado de la Supercopa " + temporadaActual);
                opcion2 = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 2);
                if(opcion2 == 1){
                    supercopa.jugarJornada();

                }else if(opcion2 == 2){
                    supercopa.jugarCalendario();

                }

            }else {
                System.out.println("Esta liga ya ha sido jugada esta temporada.");
                menuLigas();
            }

            comprobarTemporada();

        }else if(opcion == 3){

            if(!ligaSpainJugada){
                ligaSpain = new Liga("Liga Española", temporadaActual, GestionEquipos.getEquiposLigaSpain());
                ligaSpainJugada = true;
                ligasJugadas++;
                ligaSpain.calendario(GestionEquipos.getEquiposLigaSpain());

                System.out.println("1. Jugar todas las Jornadas.");
                System.out.println("2. Ver el resultado de la Supercopa " + temporadaActual);
                opcion2 = Errores.comprobar(sc, "¿Qué deseas hacer?", 1, 2);
                if(opcion2 == 1){
                    ligaSpain.jugarJornada();

                }else if(opcion2 == 2){
                    ligaSpain.jugarCalendario();

                }

            }else {
                System.out.println("Esta liga ya ha sido jugada esta temporada.");
                menuLigas();
            }

            comprobarTemporada();
        }

        }while (opcion != 4);

    }


    //region TEMPORADAS

    private void comprobarTemporada(){
        if (ligasJugadas == 3) {
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

        copaReyJugada = false;
        supercopaJugada = false;
        ligaSpainJugada = false;
        ligasJugadas = 0;

        temporadaActual = calcularTemporada(temporadaActual);

        Tienda.nuevaTemporada();
        copaRey.reiniciarLiga();
        supercopa.reiniciarLiga();
        ligaSpain.reiniciarLiga();

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

}
