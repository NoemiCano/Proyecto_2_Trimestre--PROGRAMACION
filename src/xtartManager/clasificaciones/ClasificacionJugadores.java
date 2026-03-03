package xtartManager.clasificaciones;

import xtartManager.interfaz.Colores;
import xtartManager.modelo.equipos.Equipo;
import xtartManager.modelo.persona.Jugador;
import xtartManager.modelo.persona.Persona;

import java.util.ArrayList;
import java.util.List;

public class ClasificacionJugadores {

    public static void mostrarRankingJugadores( List<Equipo> equipos ){
        List<Jugador> todosJugadores = new ArrayList<>();

        for (Equipo e : equipos){
            for (Persona p : e.getStaff()){
                if (p instanceof Jugador j){
                    todosJugadores.add(j);
                }
            }
        }

        System.out.println(Colores.AMARILLO + "\n========= TOP 10 JUGADORES (MEDIA) =========" + Colores.RESET);
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getMedia(), j1.getMedia()));

        for (int i = 0; i < Math.min(10, todosJugadores.size()); i++) {
            Jugador j = todosJugadores.get(i);
            System.out.printf("%2d. %-20s | Media: %d | Equipo: %-15s" + "%n",
                    (i + 1), j.getNombre() + " " + j.getApellido(), j.getMedia(), j.getEquipo().getNombre());
        }

        System.out.println(Colores.AZUL + "\n========= TOP 3 GOLEADORES (PICHICHI) =========" + Colores.RESET);
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getGolesMarcados(), j1.getGolesMarcados()));

        if (todosJugadores.isEmpty() || todosJugadores.get(0).getGolesMarcados() == 0) {
            System.out.println("Todavía no se han estrenado los marcadores.");
        } else {
            for (int i = 0; i < Math.min(3, todosJugadores.size()); i++) {
                Jugador j = todosJugadores.get(i);
                // CORRECCIÓN: El RESET va al final del formato %n, no sumado a j.getGolesMarcados()
                System.out.printf("%d. %-20s | Goles: %d" + "%n",
                        (i + 1), j.getNombre() + " " + j.getApellido(), j.getGolesMarcados());
            }
        }

        System.out.println(Colores.ROJO + "\n========= JUGADOR MÁS SANCIONADO =========" + Colores.RESET);
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getTarjetasAmarillas(), j1.getTarjetasAmarillas()));

        if (!todosJugadores.isEmpty() && todosJugadores.get(0).getTarjetasAmarillas() > 0) {
            Jugador j = todosJugadores.get(0);
            System.out.println("El jugador más sancionado: " + j.getNombre() + " " + j.getApellido() +
                    " con " + j.getTarjetasAmarillas() + " amarillas.");
        } else {
            System.out.println("Competición limpia: ¡nadie tiene tarjetas!");
        }
    }
}
