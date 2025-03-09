package org.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.project.model.Employee;

import java.text.SimpleDateFormat;
import java.text.ParseException;

class JUnitTests {

    @Test
    void testGettersAndSetters() {
        Employee emp = new Employee("Juan", "Pérez", "López", "12345678A", 2000.0, "2023-05-15", "IT");

        assertEquals("Juan", emp.getNombre());
        assertEquals("Pérez", emp.getApellido1());
        assertEquals("López", emp.getApellido2());
        assertEquals("12345678A", emp.getDni());
        assertEquals(2000.0, emp.getSalario());
        assertEquals("2023-05-15", emp.getFechaIncorporacion());
        assertEquals("IT", emp.getDepartamento());

        emp.setNombre("Carlos");
        assertEquals("Carlos", emp.getNombre());
    }

    @Test
    void testDniFormatoCorrecto() {
        String dni = "12345678A";
        assertTrue(dni.matches("^[0-9]{8}[A-Za-z]$"), "El DNI debe tener 8 números y 1 letra");
    }

    @Test
    void testDniFormatoIncorrecto() {
        String dni = "123A5678";
        assertFalse(dni.matches("^[0-9]{8}[A-Za-z]$"), "Formato de DNI incorrecto");
    }

    @Test
    void testNombreNoDebeEstarVacio() {
        String nombre = "";
        assertTrue(nombre.isEmpty(), "El nombre no debe estar vacío");
    }

    @Test
    void testFormatoFechaCorrecto() throws ParseException {
        String fecha = "2023-05-15";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(fecha, formato.format(formato.parse(fecha)), "Formato de fecha incorrecto");
    }
}
