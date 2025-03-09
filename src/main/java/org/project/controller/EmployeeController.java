package org.project.controller;

import org.project.model.Employee;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private static final String URL = "jdbc:mysql://localhost:3306/GestionEmpleados";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public EmployeeController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error al cargar el driver JDBC.", ex);
        }
    }

    public void addEmployee(String nombre, String apellido1, String apellido2, String dni, Double salario, String fechaIncorporacion, String departamento) throws Exception {
        if (nombre.isEmpty() || apellido1.isEmpty() || dni.isEmpty() || fechaIncorporacion.isEmpty() || departamento.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        if (salario <= 0) {
            throw new Exception("El salario debe ser mayor a 0.");
        }

        if (checkEmployeeExists(dni)) {
            throw new Exception("El empleado con DNI " + dni + " ya existe.");
        }

        String dniRegex = "^[0-9]{8}[A-Za-z]$";
        if (!dni.matches(dniRegex)){
            throw new Exception("DNI incorrecto, debe contener 8 dígitos seguidos de una letra.");
        }

        String format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        // Comprobación del formato de la fecha
        if (!fechaIncorporacion.equals(formatter.format(formatter.parse(fechaIncorporacion)))) {
            throw new Exception("La fecha de incorporación no está en el formato correcto.");
        }

        // Obtener el ID del departamento
        int departamentoId = getDepartamentoId(departamento);

        Employee employee = new Employee(nombre, apellido1, apellido2, dni, salario, fechaIncorporacion, departamento);

        String sql = "INSERT INTO Empleados (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, id_departamento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getNombre());
            statement.setString(2, employee.getApellido1());
            statement.setString(3, employee.getApellido2());
            statement.setString(4, employee.getDni());
            statement.setDouble(5, employee.getSalario());
            statement.setString(6, employee.getFechaIncorporacion());
            statement.setInt(7, departamentoId); // Usar el id_departamento

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error al añadir el empleado a la base de datos: " + e.getMessage());
        }
    }

    // Método para obtener el id_departamento a partir del nombre del departamento
    private int getDepartamentoId(String departamento) throws SQLException {
        String sql = "SELECT id_departamento FROM Departamentos WHERE nombre_departamento = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, departamento);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_departamento");
                } else {
                    throw new SQLException("Departamento no encontrado: " + departamento);
                }
            }
        }
    }

    // Método para verificar si un empleado con el DNI ya existe en la base de datos
    public boolean checkEmployeeExists(String dni) throws SQLException {
        String sql = "SELECT 1 FROM Empleados WHERE dni = ? LIMIT 1"; // La consulta solo busca si existe un registro
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dni);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public List<String> getDepartamentos() throws SQLException {
        String sql = "SELECT nombre_departamento FROM Departamentos";
        List<String> departamentos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                departamentos.add(resultSet.getString("nombre_departamento"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los departamentos: " + e.getMessage());
        }

        return departamentos;
    }

    public List<String> getAllEmployees() throws SQLException {
        String sql = "SELECT * FROM empleados";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<String> departments = new ArrayList<>();
            while (resultSet.next()){
                departments.add(resultSet.getString("nombre"));
            }

            return departments;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los empleados: " + e.getMessage());
        }
    }

    public void uploadPhotoForEmployee(String employeeName, String photoPath) throws SQLException {
        String sql = "UPDATE Empleados SET foto = ? WHERE nombre = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, photoPath);
            statement.setString(2, employeeName);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No se encontró el empleado con el nombre especificado.");
            }
        }
    }

}
