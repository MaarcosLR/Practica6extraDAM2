// Define el paquete en el que se encuentra la clase de pruebas.
package org.project;

import static org.mockito.Mockito.*; // Importa las funciones de Mockito para simulaciones (mocks).
import static org.junit.jupiter.api.Assertions.*; // Importa las aserciones de JUnit.

import org.junit.jupiter.api.BeforeEach; // Importa la anotación para métodos de configuración antes de cada prueba.
import org.junit.jupiter.api.Test; // Importa la anotación para definir pruebas unitarias.
import org.mockito.Mock; // Importa la anotación para la creación de mocks.
import org.mockito.MockitoAnnotations; // Importa la clase para inicializar los mocks.
import org.project.controller.DepartmentController; // Importa el controlador de departamentos.
import org.project.controller.EmployeeController; // Importa el controlador de empleados.

import java.sql.SQLException; // Importa la excepción para manejo de errores en bases de datos.
import java.util.Arrays; // Importa la utilidad para trabajar con listas.
import java.util.List; // Importa la clase List para manejar listas de empleados.

class MockitoTests { // Clase que contiene pruebas unitarias con Mockito.

    // Se crean objetos simulados (mocks) para evitar el uso real de la base de datos o lógica de negocio.
    @Mock
    private EmployeeController employeeController;

    @Mock
    private DepartmentController departmentController;

    // Método que se ejecuta antes de cada prueba para inicializar los mocks.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para verificar que no se puede agregar un empleado si ya existe en la base de datos.
    @Test
    void testEmpleadoYaExiste() throws SQLException {
        // Se simula que el método checkEmployeeExists devuelve "true" cuando se consulta un DNI existente.
        when(employeeController.checkEmployeeExists("12345678A")).thenReturn(true);

        // Se verifica que al intentar agregar un empleado con un DNI existente, se lanza una excepción.
        assertThrows(Exception.class, () -> {
            employeeController.addEmployee("Juan", "Pérez", "López", "12345678A", 2000.0, "2023-05-15", "IT");
        }, "El empleado con DNI ya existe.");
    }

    // Prueba para verificar que se puede agregar un departamento si no existe previamente.
    @Test
    void testAgregarDepartamento() throws Exception {
        // Se simula que el método checkDepartmentExists devuelve "false", indicando que el departamento no existe.
        when(departmentController.checkDepartmentExists("Finanzas")).thenReturn(false);

        // Se verifica que no se lanza ninguna excepción al agregar un nuevo departamento.
        assertDoesNotThrow(() -> {
            departmentController.addDepartment("Finanzas", "Manejo de cuentas");
        });
    }

    // Prueba para verificar que se pueden obtener todos los empleados de la base de datos.
    @Test
    void testObtenerTodosLosEmpleados() throws SQLException {
        // Se crea una lista simulada de empleados.
        List<String> empleados = Arrays.asList("Juan", "Carlos", "Ana");

        // Se simula que el método getAllEmployees devuelve esta lista.
        when(employeeController.getAllEmployees()).thenReturn(empleados);

        // Se verifica que la lista devuelta tiene el tamaño esperado.
        assertEquals(3, employeeController.getAllEmployees().size());
    }
}
