package xtartManager.equipos;

import xtartManager.persona.Posicion;
import xtartManager.gestionPersonal.GestionPersonal;
import xtartManager.persona.Entrenador;
import xtartManager.persona.Jugador;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionEquipos {

    protected static final List<Equipo> equipos = new ArrayList<>();
    protected static final List<Estadio> estadios = new ArrayList<>();
    private static final Random rnd = new Random();

    private static boolean cargadoEquipos = false;
    private static boolean cargadoEstadios = false;


    public static void cargarEquiposDesdeTxt() {
        if (cargadoEquipos) return;

        try {
            Path ruta = Path.of("src/xtartManager/datosCreacion/equipos.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 5) continue;

                int idEquipo = Integer.parseInt(info[0].trim());
                String nombre = info[1].trim();
                String ciudad = info[2].trim();
                int codigoPostal = Integer.parseInt(info[3].trim());
                LocalDate anioFundacion = LocalDate.parse(info[4].trim());

                equipos.add(new Equipo(idEquipo, nombre, ciudad, codigoPostal, anioFundacion));
            }

            cargadoEquipos = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarEstadiosDesdeTxt() {
        if (cargadoEstadios) return;

        try {
            Path ruta = Path.of("src/xtartManager/datosCreacion/estadios.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) { // saltar cabecera
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 8) continue;

                int idEstadio = Integer.parseInt(info[0].trim());
                String nombre = info[1].trim();
                double capacidadEstadio = Double.parseDouble(info[2].trim());
                String ciudad = info[3].trim();
                int codigoPostal = Integer.parseInt(info[4].trim());
                LocalDate anioFundacion = LocalDate.parse(info[5].trim());
                String direccion = info[6].trim();
                String tipoCesped = info[7].trim();

                estadios.add(new Estadio(
                        idEstadio, nombre, capacidadEstadio,
                        ciudad, codigoPostal, anioFundacion,
                        direccion, tipoCesped
                ));
            }

            cargadoEstadios = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Equipo> getEquipos() {
        return equipos;
    }

    public static List<Estadio> getEstadios() {
        return estadios;
    }



    public static void asignarEstadiosUnicos() {
        if (!cargadoEquipos) cargarEquiposDesdeTxt();
        if (!cargadoEstadios) cargarEstadiosDesdeTxt();

        if (estadios.size() < equipos.size()) {
            throw new RuntimeException("No hay suficientes estadios. Equipos: " + equipos.size() + " | Estadios: " + estadios.size());
        }

        List<Estadio> disponibles = new ArrayList<>(estadios);

        for (Equipo eq : equipos) {
            int idx = rnd.nextInt(disponibles.size());
            Estadio elegido = disponibles.remove(idx);
            eq.setEstadio(elegido);
        }
    }

    public static void asignarEntrenadoresUnicos() {
        if (!cargadoEquipos) cargarEquiposDesdeTxt();

        GestionPersonal.cargarEntrenadoresDesdeTxt();

        List<Entrenador> disponibles = new ArrayList<>(GestionPersonal.getEntrenadores());

        if (disponibles.size() < equipos.size()) {
            throw new RuntimeException("No hay suficientes entrenadores. Equipos: " + equipos.size()
                    + " | Entrenadores: " + disponibles.size());
        }

        for (Equipo eq : equipos) {
            int idx = rnd.nextInt(disponibles.size());
            Entrenador elegido = disponibles.remove(idx);
            eq.setEntrenador(elegido);
            elegido.setEquipo(eq);
        }
    }


    public static void repartirOnce442SinLeyendas() {
        if (!cargadoEquipos) cargarEquiposDesdeTxt();
        GestionPersonal.cargarJugadoresDesdeTxt();

        List<Jugador> jugadoresDisponibles = new ArrayList<>();
        for (Jugador j : GestionPersonal.getJugadores()) {
            if (j.getEquipo() == null && j.getIdJugador() <= 330) {
                jugadoresDisponibles.add(j);
            }
        }

        int necesarios = equipos.size() * 11;
        if (jugadoresDisponibles.size() < necesarios) {
            throw new RuntimeException("No hay suficientes jugadores normales. Necesarios: " + necesarios + " | Disponibles: " + jugadoresDisponibles.size());
        }

        for (Equipo eq : equipos) {
            ficharPorPosicion(eq, jugadoresDisponibles, Posicion.PORTERO, 1);
            ficharPorPosicion(eq, jugadoresDisponibles, Posicion.DEFENSA, 4);
            ficharPorPosicion(eq, jugadoresDisponibles, Posicion.MEDIO, 4);
            ficharPorPosicion(eq, jugadoresDisponibles, Posicion.DELANTERO, 2);
        }
    }

    private static void ficharPorPosicion(Equipo eq, List<Jugador> jugadoresDisponibles, Posicion pos, int cantidad) {
        List<Jugador> candidatos = new ArrayList<>();
        for (Jugador j : jugadoresDisponibles) {
            if (j.getPosicion() == pos) candidatos.add(j);
        }

        if (candidatos.size() < cantidad) {
            throw new RuntimeException("No hay suficientes jugadores para " + pos +
                    ". Necesarios: " + cantidad + " | Disponibles: " + candidatos.size());
        }

        for (int i = 0; i < cantidad; i++) {
            int idx = rnd.nextInt(candidatos.size());
            Jugador elegido = candidatos.remove(idx);
            eq.ficharJugador(elegido);
            jugadoresDisponibles.remove(elegido);
        }
    }
}
