package xtartManager.modelo.equipos;

import xtartManager.clasificaciones.ClasificacionEquipos;
import xtartManager.modelo.persona.Entrenador;
import xtartManager.modelo.persona.Jugador;
import xtartManager.modelo.persona.Persona;
import xtartManager.modelo.persona.Posicion;

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
//    private Entrenador entrenador;
//    private List<Jugador> jugadores;
    private ClasificacionEquipos clasificacion;
    private List<Persona> staff;
    private List<Persona> plantillaOriginal;
    private double presupuesto = 0;

    public Equipo() {
    }

    public Equipo(int idEquipo, String nombre, String ciudad, int codigoPostal, LocalDate anioFundacion, Entrenador entrenador, List<Jugador> jugadores) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.anioFundacion = anioFundacion;
        this.staff = new ArrayList<>();
        this.plantillaOriginal = new ArrayList<>(staff);
        this.clasificacion = new ClasificacionEquipos();
    }

    public Equipo(int idEquipo, String nombre, String ciudad, int codigoPostal, LocalDate anioFundacion) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.anioFundacion = anioFundacion;
        this.staff = new ArrayList<>();
        this.plantillaOriginal = new ArrayList<>(staff);
        this.clasificacion = new ClasificacionEquipos();
    }

    // region GETTER & SETTER

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

    public List<Persona> getStaff() {
        return staff;
    }

    public void setStaff(List<Persona> staff) {
        this.staff = staff;
    }

    public ClasificacionEquipos getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionEquipos clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Entrenador getEntrenador() {
        for (Persona p : staff) {
            if (p instanceof Entrenador) {
                return (Entrenador) p; // DOWNCASTING
            }
        }
        return null;
    }

    public void setEntrenador(Entrenador e) {
        this.staff.removeIf(p -> p instanceof Entrenador);
        if (e != null) {
            this.staff.add(e);
        }
    }

    public List<Jugador> getJugadores() {
        List<Jugador> soloJugadores = new ArrayList<>();

        for (Persona p : this.staff) {
            if (p instanceof Jugador j) { // DOWNCASTING
                soloJugadores.add(j);
            }
        }
        return soloJugadores;
    }

    public void setJugadores(List<Jugador> nuevosJugadores) {
        this.staff.removeIf(p -> p instanceof Jugador);

        if (nuevosJugadores != null) {
            this.staff.addAll(nuevosJugadores);

            for (Jugador j : nuevosJugadores) {
                j.setEquipo(this);
            }
        }
    }

    public List<Jugador> getJugadoresPorPosicion(Posicion posicion) {
        List<Jugador> resultado = new ArrayList<>();
        for (Persona p : staff) {
            if (p instanceof Jugador j) {
                if (j.getPosicion() == posicion) {
                    resultado.add(j);
                }
            }
        }
        return resultado;
    }

    // endregion

    public boolean ficharJugador(Jugador j) {
        if (j.getEquipo() != null) return false;
        staff.add(j);
        j.setEquipo(this);
        return true;
    }

    public boolean expulsarJugador(Jugador j) {
        if (staff.remove(j)) {
            j.setEquipo(null);
            return true;
        }
        return false;
    }

    public boolean reemplazarJugador(Jugador jExpulsado, Jugador nuevo) {

        boolean expulsado = expulsarJugador(jExpulsado);
        if (!expulsado) return false;

        boolean fichado = ficharJugador(nuevo);
        if (!fichado) {
            ficharJugador(jExpulsado);
            return false;
        }
        return true;
    }

    public double calcularFuerzaAtaque(){
        double sumaAtaque = 0;
        int contadorDelanteros = 0;

        for (Persona p : this.staff) {
            if (p instanceof Jugador) {
                Jugador j = (Jugador) p; // DOWNCASTING
                if (j.getPosicion() == Posicion.DELANTERO) {
                    sumaAtaque += j.getMedia();
                    contadorDelanteros++;
                }
            }
        }

        return (contadorDelanteros > 0) ? (sumaAtaque / contadorDelanteros) : 40.0;
    }

    public Jugador elegirDelanteroAlAzar(){
        List<Jugador> delanteros = new ArrayList<>();

        for (Persona p : this.staff) {
            if (p instanceof Jugador j) { // DOWNCASTING
                if (j.getPosicion() == Posicion.DELANTERO) {
                    delanteros.add(j);
                }
            }
        }

        if (delanteros.isEmpty()) {
            return null;
        }

        int indice = (int) (Math.random() * delanteros.size());
        return delanteros.get(indice);
    }

    public void ingresarDinero(double cantidad){
        this.presupuesto += cantidad;
    }

    public void reiniciarClasificacion() {
        clasificacion.reiniciar();
    }

    @Override
    public String toString() {
        return "Equipo: " + nombre +
                "| anioFundacion: " + anioFundacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return idEquipo == equipo.idEquipo && codigoPostal == equipo.codigoPostal && Objects.equals(nombre, equipo.nombre) && Objects.equals(ciudad, equipo.ciudad) && Objects.equals(estadio, equipo.estadio) && Objects.equals(anioFundacion, equipo.anioFundacion) && Objects.equals(clasificacion, equipo.clasificacion) && Objects.equals(staff, equipo.staff);
    }
}
