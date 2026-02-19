package xtartManager.persona;

import java.time.LocalDate;
import java.util.Objects;

public class Arbitro extends Persona {

    private int idArbitro;
    private String tipo;
    private String nacionalidad;
    private int idSuperior;

    public Arbitro() {
    }

    public Arbitro(String nombre, String apellido, LocalDate fechaNacimiento, int idArbitro, String tipo, String nacionalidad, int idSuperior) {
        super(nombre, apellido, fechaNacimiento);
        this.idArbitro = idArbitro;
        this.tipo = tipo;
        this.nacionalidad = nacionalidad;
        this.idSuperior = idSuperior;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getIdSuperior() {
        return idSuperior;
    }

    public void setIdSuperior(int idSuperior) {
        this.idSuperior = idSuperior;
    }

    @Override
    public String toString() {
        return super.toString() +
                "idArbitro=" + idArbitro +
                ", tipo='" + tipo + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", idSuperior=" + idSuperior +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Arbitro arbitro = (Arbitro) o;
        return idArbitro == arbitro.idArbitro;
    }

}
