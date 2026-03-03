package xtartManager;

import xtartManager.interfaz.Inicio;
import xtartManager.interfaz.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        Inicio.iniciar();

        MenuPrincipal menuP = new MenuPrincipal();
        menuP.iniciarMenu();
    }
}