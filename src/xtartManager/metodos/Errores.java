package xtartManager.metodos;

import java.util.Scanner;

public class Errores {


    public static int comprobar(Scanner sc, String texto) {

        while (true) {
            System.out.print(texto);

            if (sc.hasNextInt()) {

                int valor = sc.nextInt();

                sc.nextLine();
                return valor;

            } else {
                System.out.println("Entrada inválida: introduce un número entero.");
                sc.nextLine();
            }
        }
    }

    public static int comprobar(Scanner sc, String texto, int min, int max) {

        while (true) {
            int valor = comprobar(sc, texto);

            if (valor < min || valor > max) {

                System.out.println("Valor fuera de rango. Debe estar entre " + min + " y " + max + ".");
            } else {
                return valor;
            }
        }
    }

    public static double comprobarDouble(Scanner sc, String texto) {
        while (true) {
            System.out.print(texto);

            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();

                sc.nextLine();
                return valor;

            } else {
                System.out.println("Entrada inválida: introduce un número (puede tener decimales).");
                sc.nextLine();
            }
        }
    }

    public static double comprobarDouble(Scanner sc, String texto, double min, double max) {
        while (true) {
            double valor = comprobarDouble(sc, texto);

            if (valor < min || valor > max) {

                System.out.println("Valor fuera de rango. Debe estar entre " + min + " y " + max + ".");

            } else {
                return valor;
            }
        }
    }

    public static String comprobarString(Scanner sc, String texto) {

        while (true) {
            System.out.print(texto);
            String line = sc.nextLine();

            if (line.trim().isEmpty()) {

                System.out.println("Entrada vacía. Introduce algún texto.");

            } else {
                return line;
            }
        }
    }

    public static String comprobarStringVacio(Scanner sc, String prompt) {

        System.out.print(prompt);
        return sc.nextLine();
    }

}



