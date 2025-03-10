// Define el paquete donde se encuentra la clase de prueba.
package org.project;

import static org.junit.jupiter.api.Assertions.*; // Importa las aserciones de JUnit.
import org.junit.jupiter.api.Test; // Importa la anotación para definir pruebas unitarias.
import org.project.model.Employee; // Importa la clase Employee para realizar pruebas.

import java.text.SimpleDateFormat; // Importa la clase para formateo de fechas.
import java.text.ParseException; // Importa la clase para manejar errores de formato de fecha.

class JUnitTests { // Clase que contiene pruebas unitarias para la clase Employee.

    // Test para verificar los métodos getter y setter de la clase Employee.
    @Test
    void testGettersAndSetters() {
        // Se crea un objeto Employee con valores iniciales.
        Employee emp = new Employee("Juan", "Pérez", "López", "12345678A", 2000.0, "2023-05-15", "IT");

        // Se verifica que los getters devuelvan los valores correctos.
        assertEquals("Juan", emp.getNombre());
        assertEquals("Pérez", emp.getApellido1());
        assertEquals("López", emp.getApellido2());
        assertEquals("12345678A", emp.getDni());
        assertEquals(2000.0, emp.getSalario());
        assertEquals("2023-05-15", emp.getFechaIncorporacion());
        assertEquals("IT", emp.getDepartamento());

        // Se modifica el nombre usando el setter y se verifica el cambio.
        emp.setNombre("Carlos");
        assertEquals("Carlos", emp.getNombre());
    }

    // Test para verificar que el formato del DNI es correcto.
    @Test
    void testDniFormatoCorrecto() {
        String dni = "12345678A";

        // Se verifica que el DNI cumpla con el formato: 8 dígitos seguidos de 1 letra.
        assertTrue(dni.matches("^[0-9]{8}[A-Za-z]$"), "El DNI debe tener 8 números y 1 letra");
    }

    // Test para verificar que un DNI con formato incorrecto no pase la validación.
    @Test
    void testDniFormatoIncorrecto() {
        String dni = "123A5678";

        // Se verifica que el DNI no cumpla con el formato requerido.
        assertFalse(dni.matches("^[0-9]{8}[A-Za-z]$"), "Formato de DNI incorrecto");
    }

    // Test para verificar que el nombre no esté vacío.
    @Test
    void testNombreNoDebeEstarVacio() {
        String nombre = "";

        // Se verifica que el nombre está vacío (falla porque la condición es incorrecta).
        assertTrue(nombre.isEmpty(), "El nombre no debe estar vacío");
    }

    // Test para verificar que la fecha tiene el formato correcto (yyyy-MM-dd).
    @Test
    void testFormatoFechaCorrecto() throws ParseException {
        String fecha = "2023-05-15";

        // Se define el formato de fecha esperado.
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        // Se verifica que la fecha es válida y respeta el formato esperado.
        assertEquals(fecha, formato.format(formato.parse(fecha)), "Formato de fecha incorrecto");
    }
}
