package com.example.tl01e1_027.Tablas;

public class Personas {

    private Integer id;
    private String nombre;
    private Integer telefono;
    private String notas;

    public Personas(Integer id, String nombre, Integer telefono, String notas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.notas = notas;
    }

    public Personas() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
