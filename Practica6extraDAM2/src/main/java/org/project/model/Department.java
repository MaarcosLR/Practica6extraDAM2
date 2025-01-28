package org.project.model;

public class Department {
    private String nombre_departamento;
    private String descripcion;

    public Department(String nombre_departamento, String descripcion) {
        this.nombre_departamento = nombre_departamento;
        this.descripcion = descripcion;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Department{" +
                "nombre_departamento='" + nombre_departamento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
