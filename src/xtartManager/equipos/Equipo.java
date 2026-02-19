package xtartManager.equipos;

import xtartManager.persona.Entrenador;
import xtartManager.persona.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {

    private int idEquipo;
    private String nombre;
    private String ciudad;
    private int codigoPostal;
    private Estadio estadio;
    private LocalDate anioFundacion;
    private Entrenador entrenador;
    private List<Jugador> jugadores;

    public Equipo() {
    }

    public Equipo(int idEquipo, String nombre, String ciudad, int codigoPostal, LocalDate anioFundacion, Entrenador entrenador, List<Jugador> jugadores) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.anioFundacion = anioFundacion;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
    }

    public Equipo(int idEquipo, String nombre, String ciudad, int codigoPostal, LocalDate anioFundacion) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.anioFundacion = anioFundacion;
        this.jugadores = new ArrayList<>();
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public LocalDate getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(LocalDate anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean ficharJugador(Jugador j) {
        if (j.getEquipo() != null) return false;
        jugadores.add(j);
        j.setEquipo(this);
        return true;
    }

    public boolean expulsarJugador(Jugador j) {
        if (jugadores.remove(j)) {
            j.setEquipo(null);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estadio=" + (estadio != null ? estadio.getNombre() : "SIN_ESTADIO") +
                ", entrenador=" + (entrenador != null ? entrenador.getNombre() + " " + entrenador.getApellido() : "SIN_ENTRENADOR") +
                ", numJugadores=" + (jugadores != null ? jugadores.size() : 0) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return idEquipo == equipo.idEquipo;
    }

}
