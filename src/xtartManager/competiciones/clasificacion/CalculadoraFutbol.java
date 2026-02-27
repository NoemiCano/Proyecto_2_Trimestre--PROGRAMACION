package xtartManager.competiciones.clasificacion;

public final class CalculadoraFutbol {

    public static int calcularPuntos(int golesPropios, int golesRival) {
        if (golesPropios > golesRival) {
            return 3;
        } else if (golesPropios == golesRival) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int calcularDiferenciaGoles(int golesFavor, int golesContra) {
        return golesFavor - golesContra;
    }

}
