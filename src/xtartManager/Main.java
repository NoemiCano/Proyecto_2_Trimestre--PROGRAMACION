package xtartManager;

import xtartManager.interfaz.Inicio;
import xtartManager.interfaz.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        Inicio.iniciar();
//        Inicio.mostrarEquiposPorCompeticiones();
//        Inicio.mostrarResumen();
//        Inicio.mostrarLeyendasTienda();

        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciarMenu();
    }
}