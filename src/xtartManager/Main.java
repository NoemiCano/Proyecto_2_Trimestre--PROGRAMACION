package xtartManager;

import xtartManager.competiciones.jornadas.Partido;
import xtartManager.inicio.Inicio;


import java.util.List;

public class Main {

    public static void main(String[] args) {

        Inicio.iniciar();
        Inicio.mostrarEquiposPorCompeticiones();
        Inicio.mostrarResumen();
        Inicio.mostrarLeyendasTienda();
    }
}