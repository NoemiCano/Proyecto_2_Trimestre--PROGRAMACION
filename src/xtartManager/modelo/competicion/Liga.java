package xtartManager.modelo.competicion;

import xtartManager.clasificaciones.ClasificacionEquipos;
import xtartManager.clasificaciones.ClasificacionJugadores;
import xtartManager.interfaz.MenuPrincipal;
import xtartManager.modelo.equipos.Equipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Liga {
    private String nombre;
    private String temporada;
    private List<Jornada> jornadas;
    private List<Equipo> equipos;
    private int jornadaActual = 0;

    public Liga(){}

    public Liga(String nombre, String temporada, List<Equipo> equipos) {
        this.nombre = nombre;
        this.jornadas = new ArrayList<>();
        this.equipos = equipos;
        this.temporada = temporada;
    }

    // region GETTER & SETTER

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    // endregion

    public void calendario(List<Equipo> equipos){

        List<Equipo> equiposLiga = equipos;
        Collections.shuffle(equiposLiga);

        int numEquipos = equiposLiga.size();
        int numJornadas = numEquipos - 1;
        int partidosPorJornada = numEquipos / 2;

        for (int i = 0; i < numJornadas; i++) {
            Jornada jornadaIda = new Jornada(i + 1);

            for (int j = 0; j < partidosPorJornada; j++) {
                Equipo local = equiposLiga.get(j);
                Equipo visitante = equiposLiga.get(numEquipos - 1 - j);

                jornadaIda.addPartido(new Partido(local, visitante));
            }
            this.jornadas.add(jornadaIda);

            Equipo ultimo = equiposLiga.remove(numEquipos - 1);
            equiposLiga.add(1, ultimo);

        }
        calendarioVuelta();
    }

    private void calendarioVuelta(){
        int totalJornadas = this.jornadas.size();

        for (int i = 0; i < totalJornadas; i++) {
            Jornada jornadaIda = this.jornadas.get(i);
            Jornada jornadaVuelta = new Jornada(i + 1 + totalJornadas);
            
            for(Partido partidoIda : jornadaIda.getPartidos()){
                Partido partidoVuelta = new Partido(partidoIda.getVisitante(), partidoIda.getLocal());
                jornadaVuelta.addPartido(partidoVuelta);
            }
            this.jornadas.add(jornadaVuelta);
        }
    }

    public void jugarJornada(){
        if(jornadaActual<jornadas.size()){
            Jornada j = jornadas.get(jornadaActual);

            for(Partido partido : j.getPartidos()){

                partido.jugarPartido();
            }
            jornadaActual++;
            System.out.println("Se ha jugado la jornada: " + jornadaActual);
        } else {
            System.out.println("La liga ya ha terminado");
        }

    }

    public void jugarCalendario(){
        while(jornadaActual<jornadas.size()){
            jugarJornada();
        }
        System.out.println("Se ha jugado la liga: " + nombre + " " + temporada);
    }

    public void verClasificacion() {
        ClasificacionEquipos.mostrarTabla(this.equipos, this.nombre);
    }

    public void verEstadisticasJugadores() {
        ClasificacionJugadores.mostrarRankingJugadores(this.equipos);
    }

    public boolean estaFinalizada() {
        return jornadaActual >= jornadas.size();
    }

    public void reiniciarLiga() {
        jornadas.clear();
        jornadaActual = 0;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                ", temporada='" + temporada + '\'' +
                ", jornadas=" + jornadas +
                ", equipos=" + equipos;
    }
}
