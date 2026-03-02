package xtartManager.gestion;

import xtartManager.modelo.persona.Posicion;
import xtartManager.modelo.persona.TipoArbitro;
import xtartManager.modelo.persona.Arbitro;
import xtartManager.modelo.persona.Entrenador;
import xtartManager.modelo.persona.Jugador;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonal {


    private static final List<Jugador> jugadores = new ArrayList<>();
    private static final List<Entrenador> entrenadores = new ArrayList<>();
    private static final List<Arbitro> arbitros = new ArrayList<>();

    private static boolean cargado = false;
    private static boolean cargadoEntrenadores = false;
    private static boolean cargadoArbitros = false;

    public static void cargarJugadoresDesdeTxt() {
        if (cargado) return;

        try {

            Path ruta = Path.of("src/xtartManager/datos/jugadores.txt");

            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);


            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 9) continue;

                String nombre = info[0].trim();
                String apellido = info[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(info[2].trim());
                int idJugador = Integer.parseInt(info[3].trim());
                int dorsal = Integer.parseInt(info[4].trim());
                int telefono = Integer.parseInt(info[5].trim());
                Posicion posicion = Posicion.valueOf(info[6].trim().toUpperCase());
                double altura = Double.parseDouble(info[7].trim());
                int media = Integer.parseInt(info[8].trim());

                jugadores.add(new Jugador(
                        nombre, apellido, fechaNacimiento,
                        idJugador, dorsal, telefono,
                        posicion, altura, media
                ));
            }

            cargado = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Jugador> getJugadores() {
        return jugadores;
    }


    public static void cargarEntrenadoresDesdeTxt() {
        if (cargadoEntrenadores) return;

        try {
            Path ruta = Path.of("src/xtartManager/datos/entrenadores.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 5) continue;

                String nombre = info[0].trim();
                String apellido = info[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(info[2].trim());
                int idEntrenador = Integer.parseInt(info[3].trim());
                int aniosExperiencia = Integer.parseInt(info[4].trim());

                entrenadores.add(new Entrenador(
                        nombre, apellido, fechaNacimiento,
                        idEntrenador, aniosExperiencia
                ));
            }

            cargadoEntrenadores = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public static void cargarArbitrosDesdeTxt() {
        if (cargadoArbitros) return;

        try {
            Path ruta = Path.of("src/xtartManager/datos/arbitros.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 7) continue;

                String nombre = info[0].trim();
                String apellido = info[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(info[2].trim());
                int idArbitro = Integer.parseInt(info[3].trim());
                TipoArbitro tipo = TipoArbitro.valueOf(info[4].trim().toUpperCase());
                String nacionalidad = info[5].trim();
                int idSuperior = Integer.parseInt(info[6].trim());

                arbitros.add(new Arbitro(
                        nombre, apellido, fechaNacimiento,
                        idArbitro, tipo, nacionalidad, idSuperior
                ));
            }

            cargadoArbitros = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Arbitro> getArbitros() {
        return arbitros;
    }

    public static Jugador buscarJugadorPorId(int id) {

        for (Jugador j : jugadores) {
            if (j.getIdJugador() == id) {
                return j;
            }
        }

        return null;
    }

}
