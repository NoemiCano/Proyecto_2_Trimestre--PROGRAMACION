package xtartManager.equipos;

import java.time.LocalDate;
import java.util.Objects;

public class Estadio {

    private int idEstadio;
    private String nombre;
    private double capacidadEstadio;
    private String ciudad;
    private int codidoPostal;
    private LocalDate anioFundacion;
    private String direccion;
    private String tipoCesped;

    public Estadio() {
    }

    public Estadio(int idEstadio, String nombre, double capacidadEstadio, String ciudad, int codidoPostal, LocalDate anioFundacion, String direccion, String tipoCesped) {
        this.idEstadio = idEstadio;
        this.nombre = nombre;
        this.capacidadEstadio = capacidadEstadio;
        this.ciudad = ciudad;
        this.codidoPostal = codidoPostal;
        this.anioFundacion = anioFundacion;
        this.direccion = direccion;
        this.tipoCesped = tipoCesped;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCapacidadEstadio() {
        return capacidadEstadio;
    }

    public void setCapacidadEstadio(double capacidadEstadio) {
        this.capacidadEstadio = capacidadEstadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCodidoPostal() {
        return codidoPostal;
    }

    public void setCodidoPostal(int codidoPostal) {
        this.codidoPostal = codidoPostal;
    }

    public LocalDate getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(LocalDate anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoCesped() {
        return tipoCesped;
    }

    public void setTipoCesped(String tipoCesped) {
        this.tipoCesped = tipoCesped;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "id=" + idEstadio +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", capacidad=" + capacidadEstadio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estadio estadio = (Estadio) o;
        return idEstadio == estadio.idEstadio;
    }


}
