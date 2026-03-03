package xtartManager.interfaz;

import java.util.Scanner;

public class Errores {


    private static int comprobar(Scanner sc, String texto) {

        while (true) {
            System.out.println(texto);

            if (sc.hasNextInt()) {

                int valor = sc.nextInt();

                sc.nextLine();
                return valor;

            } else {
                System.out.println(Colores.ROJO + "Entrada inválida: introduce un número entero." + Colores.RESET);
                sc.nextLine();
            }
        }
    }

    protected static int comprobar(Scanner sc, String texto, int min, int max) {

        while (true) {
            int valor = comprobar(sc, texto);

            if (valor < min || valor > max) {

                System.out.println(Colores.ROJO + "Valor fuera de rango. Debe estar entre " + min + " y " + max + "." + Colores.RESET);
            } else {
                return valor;
            }
        }
    }

    private static double comprobarDouble(Scanner sc, String texto) {
        while (true) {
            System.out.println(texto);

            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();

                sc.nextLine();
                return valor;

            } else {
                System.out.println(Colores.ROJO + "Entrada inválida: introduce un número (puede tener decimales)." + Colores.RESET);
                sc.nextLine();
            }
        }
    }

    public static double comprobarDouble(Scanner sc, String texto, double min, double max) {
        while (true) {
            double valor = comprobarDouble(sc, texto);

            if (valor < min || valor > max) {

                System.out.println(Colores.ROJO + "Valor fuera de rango. Debe estar entre " + min + " y " + max + "." + Colores.RESET);

            } else {
                return valor;
            }
        }
    }

    public static String comprobarString(Scanner sc, String texto) {

        while (true) {
            System.out.println(texto);
            String line = sc.nextLine();

            if (line.trim().isEmpty()) {

                System.out.println(Colores.ROJO + "Entrada vacía. Introduce algún texto." + Colores.RESET);

            } else {
                return line;
            }
        }
    }

    public static String comprobarStringVacio(Scanner sc, String prompt) {

        System.out.println(prompt);
        return sc.nextLine();
    }

}



