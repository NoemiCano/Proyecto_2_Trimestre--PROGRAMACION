package xtartManager.gestionPersonal;

import xtartManager.persona.Arbitro;
import xtartManager.persona.Entrenador;
import xtartManager.persona.Jugador;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonal {


    protected static final List<Jugador> jugadores = new ArrayList<>();
    protected static final List<Entrenador> entrenadores = new ArrayList<>();
    protected static final List<Arbitro> arbitros = new ArrayList<>();

    private static boolean cargado = false;
    private static boolean cargadoEntrenadores = false;
    private static boolean cargadoArbitros = false;

    public static void cargarJugadoresDesdeTxt() {
        if (cargado) return;

        try {

            Path ruta = Path.of("src/xtartManager/datosCreacion/jugadores.txt");

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
                String posicion = info[6].trim();
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
            Path ruta = Path.of("src/xtartManager/datosCreacion/entrenadores.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) { // saltar cabecera
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
            Path ruta = Path.of("src/xtartManager/datosCreacion/arbitros.txt");
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            for (int i = 1; i < lineas.size(); i++) { // saltar cabecera
                String linea = lineas.get(i);
                if (linea == null || linea.isBlank()) continue;

                String[] info = linea.split(";");
                if (info.length != 7) continue;

                String nombre = info[0].trim();
                String apellido = info[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(info[2].trim());
                int idArbitro = Integer.parseInt(info[3].trim());
                String tipo = info[4].trim();
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




}
