package xtartManager.persona;

import xtartManager.equipos.Equipo;

import java.time.LocalDate;
import java.util.Objects;

public class Entrenador extends Persona {

    private int idEntrenador;
    private int aniosExperiencia;
    private Equipo equipo;

    public Entrenador() {
    }

    public Entrenador(String nombre, String apellido, LocalDate fechaNacimiento, int idEntrenador, int aniosExperiencia) {
        super(nombre, apellido, fechaNacimiento);
        this.idEntrenador = idEntrenador;
        this.aniosExperiencia = aniosExperiencia;
    }

    public Entrenador(String nombre, String apellido, LocalDate fechaNacimiento, int idEntrenador, int aniosExperiencia, Equipo equipo) {
        super(nombre, apellido, fechaNacimiento);
        this.idEntrenador = idEntrenador;
        this.aniosExperiencia = aniosExperiencia;
        this.equipo = equipo;
    }


    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void hablar(){
        System.out.println("¡Vamos equipo!");
    }

    @Override
    public String toString() {
        return super.toString() +
                "idEntrenador=" + idEntrenador +
                ", aniosExperiencia=" + aniosExperiencia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrenador that = (Entrenador) o;
        return idEntrenador == that.idEntrenador;
    }

}
