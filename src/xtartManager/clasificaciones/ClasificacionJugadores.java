package xtartManager.clasificaciones;

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

        System.out.println("\n===== TOP 10 JUGADORES (MEDIA) =====");
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getMedia(), j1.getMedia()));
        for (int i = 0; i < Math.min(10, todosJugadores.size()); i++) {
            Jugador j = todosJugadores.get(i);
            System.out.printf("%d. %-20s | Media: %d | Equipo: %s%n",
                    (i + 1), j.getNombre(), j.getMedia(), j.getEquipo().getNombre());
        }

        // --- TOP 3 GOLEADORES (PICHICHI) ---
        System.out.println("\n===== TOP 3 GOLEADORES (PICHICHI) =====");
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getGolesMarcados(), j1.getGolesMarcados()));
        for (int i = 0; i < Math.min(3, todosJugadores.size()); i++) {
            Jugador j = todosJugadores.get(i);
            System.out.printf("%d. %-20s | Goles: %d%n",
                    (i + 1), j.getNombre(), j.getGolesMarcados());
        }

        // --- JUGADOR CON MÁS TARJETAS ---
        System.out.println("\n===== JUGADOR MÁS SANCIONADO =====");
        todosJugadores.sort((j1, j2) -> Integer.compare(j2.getTarjetasAmarillas(), j1.getTarjetasAmarillas()));
        if (!todosJugadores.isEmpty()) {
            Jugador j = todosJugadores.get(0);
            System.out.println("El 'leñero' de la liga es: " + j.getNombre() +
                    " con " + j.getTarjetasAmarillas() + " amarillas.");
        }
    }
}
