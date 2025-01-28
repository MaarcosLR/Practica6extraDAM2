package org.project.model;

public class Employee {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private Double salario;
    private String fechaIncorporacion;
    private String departamento;

    public Employee(String nombre, String apellido1, String apellido2, String dni, Double salario, String fechaIncorporacion, String departamento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.salario = salario;
        this.fechaIncorporacion = fechaIncorporacion;
        this.departamento = departamento;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() { return apellido1; }

    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }

    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public Double getSalario() { return salario; }

    public void setSalario(Double salario) { this.salario = salario; }

    public String getFechaIncorporacion() { return fechaIncorporacion; }

    public void setFechaIncorporacion(String fechaIncorporacion) { this.fechaIncorporacion = fechaIncorporacion; }

    public String getDepartamento() { return departamento; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public String toString() {
        return String.format("Empleado: %s %s %s, DNI: %s, Salario: %.2f, Fecha Incorporación: %s, Departamento: %s",
                nombre, apellido1, apellido2, dni, salario, fechaIncorporacion, departamento);
    }
}

