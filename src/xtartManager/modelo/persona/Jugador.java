package xtartManager.modelo.persona;

import xtartManager.modelo.equipos.Equipo;

import java.time.LocalDate;

public class Jugador extends Persona {

    private int idJugador;
    private int dorsal;
    private int telefono;
    private Posicion posicion;
    private double altura;
    private int media;
    private Equipo equipo;
    private int golesMarcados = 0;

    public Jugador(String nombre, String apellido, LocalDate fechaNacimiento, int idJugador, int dorsal, int telefono, Posicion posicion, double altura, int media) {
        super(nombre, apellido, fechaNacimiento);
        this.idJugador = idJugador;
        this.dorsal = dorsal;
        this.telefono = telefono;
        this.posicion = posicion;
        this.altura = altura;
        this.media = media;
    }

    public Jugador(String nombre, String apellido, LocalDate fechaNacimiento, int idJugador, Equipo equipo, double altura, Posicion posicion, int telefono, int dorsal,int media) {
        super(nombre, apellido, fechaNacimiento);
        this.idJugador = idJugador;
        this.equipo = equipo;
        this.altura = altura;
        this.posicion = posicion;
        this.telefono = telefono;
        this.dorsal = dorsal;
        this.media = media;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getGolesMarcados() {
        return golesMarcados;
    }

    public void setGolesMarcados(int golesMarcados) {
        this.golesMarcados = golesMarcados;
    }

    public void hablar(){
        System.out.println("Hola,gracias por ficharme");
    }

    public void sumarGoles(int cantidad) {
        this.golesMarcados += cantidad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + idJugador +
                ", nombre='" + getNombre() + " " + getApellido() + '\'' +
                ", pos=" + posicion +
                ", media=" + media +
                ", equipo=" + (equipo != null ? equipo.getNombre() : "SIN_EQUIPO") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Jugador jugador = (Jugador) o;
        return idJugador == jugador.idJugador;
    }

}
