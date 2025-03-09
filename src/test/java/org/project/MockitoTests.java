package org.project;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.controller.DepartmentController;
import org.project.controller.EmployeeController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class MockitoTests {

    @Mock
    private EmployeeController employeeController;

    @Mock
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEmpleadoYaExiste() throws SQLException {
        when(employeeController.checkEmployeeExists("12345678A")).thenReturn(true);

        assertThrows(Exception.class, () -> {
            employeeController.addEmployee("Juan", "Pérez", "López", "12345678A", 2000.0, "2023-05-15", "IT");
        }, "El empleado con DNI ya existe.");
    }

    @Test
    void testAgregarDepartamento() throws Exception {
        when(departmentController.checkDepartmentExists("Finanzas")).thenReturn(false);

        assertDoesNotThrow(() -> {
            departmentController.addDepartment("Finanzas", "Manejo de cuentas");
        });
    }

    @Test
    void testObtenerTodosLosEmpleados() throws SQLException {
        List<String> empleados = Arrays.asList("Juan", "Carlos", "Ana");

        when(employeeController.getAllEmployees()).thenReturn(empleados);

        assertEquals(3, employeeController.getAllEmployees().size());
    }
}
