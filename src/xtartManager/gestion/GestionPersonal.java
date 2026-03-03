package xtartManager.gestion;

import xtartManager.modelo.equipos.Equipo;
import xtartManager.modelo.persona.*;

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

    public static void realizarActoInaugural(List<Equipo> equipos, List<Arbitro> arbitros) {
        if (equipos == null || equipos.isEmpty() || arbitros == null || arbitros.isEmpty()) return;

        System.out.println("\n" + "=".repeat(60));
        System.out.println("       RUEDA DE PRENSA OFICIAL: INICIO DE TEMPORADA");
        System.out.println("=".repeat(60));

        List<Persona> protagonistas = new ArrayList<>();
        protagonistas.add(arbitros.get(0));
        protagonistas.add(equipos.get(0).getEntrenador());

        if (!equipos.get(0).getStaff().isEmpty()) {
            for(Persona p : equipos.get(0).getStaff()){
                if(p instanceof Jugador){
                    protagonistas.add(p);
                    break;
                }
            }
        }

        for (Persona p : protagonistas) {
            String rol = p.getClass().getSimpleName().toUpperCase();
            System.out.print("[" + rol + "] " + p.getNombre() + " " + p.getApellido() + ": ");
            p.hablar();
        }
        System.out.println("=".repeat(60) + "\n");
    }




}
