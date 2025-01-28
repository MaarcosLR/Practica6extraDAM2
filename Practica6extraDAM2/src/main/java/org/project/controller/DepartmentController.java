package org.project.controller;

import org.project.model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentController {
    private static final String URL = "jdbc:mysql://localhost:3306/GestionEmpleados";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public DepartmentController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error al cargar el driver JDBC.", ex);
        }
    }

    public void addDepartment(String nombre_departamento, String descripcion) throws Exception {
        if (nombre_departamento.isEmpty()){
            throw new Exception("El nombre es obligatorio.");
        }

        if (checkDepartmentExists(nombre_departamento)){
            throw new Exception("El departamento ya existe.");
        }

        Department department = new Department(nombre_departamento, descripcion);
        String sql = "INSERT INTO Departamentos (nombre_departamento, descripcion) VALUES(?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, department.getNombre_departamento());
            statement.setString(2, department.getDescripcion());

            statement.executeUpdate();

        } catch (SQLException ex){
            throw new Exception("Error al añadir el departamento a la base de datos: " + ex.getMessage());
        }
    }

    private boolean checkDepartmentExists(String nombre_departamento) throws SQLException {
        String sql = "SELECT 1 FROM Departamentos WHERE LOWER(nombre_departamento) = LOWER(?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre_departamento.toLowerCase());  // Convertir el nombre ingresado a minúsculas
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Si hay un resultado, significa que el departamento ya existe
            }
        }
    }

    public List<String> getAllDepartments() throws SQLException {
        String sql = "SELECT * FROM Departamentos";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<String> departments = new ArrayList<>();
            while (resultSet.next()){
                departments.add(resultSet.getString("nombre_departamento"));
            }

            return departments;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los departamentos: " + e.getMessage());
        }
    }

}
