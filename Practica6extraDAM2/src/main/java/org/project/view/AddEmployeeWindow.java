package org.project.view;

import org.project.controller.EmployeeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddEmployeeWindow extends JFrame {

    private EmployeeController controller;

    public AddEmployeeWindow(EmployeeController controller) {
        this.controller = controller;

        // Configuración del JFrame
        setTitle("Añadir Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear campos de entrada
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        JTextField txtApellido1 = new JTextField();

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        JTextField txtApellido2 = new JTextField();

        JLabel lblDNI = new JLabel("DNI:");
        JTextField txtDNI = new JTextField();

        JLabel lblSalario = new JLabel("Salario:");
        JTextField txtSalario = new JTextField();

        JLabel lblFechaIncorporacion = new JLabel("Fecha de Incorporación (YYYY-MM-DD):");
        JTextField txtFechaIncorporacion = new JTextField();

        JLabel lblDepartamento = new JLabel("Departamento:");

        // Obtener departamentos desde la base de datos
        JComboBox<String> cmbDepartamento = new JComboBox<>();
        try {
            for (String departamento : controller.getDepartamentos()) {
                cmbDepartamento.addItem(departamento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar departamentos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton btnAgregar = new JButton("Agregar Empleado");
        btnAgregar.addActionListener(e -> {
            try {
                // Capturar datos directamente de los campos
                String nombre = txtNombre.getText().trim();
                String apellido1 = txtApellido1.getText().trim();
                String apellido2 = txtApellido2.getText().trim();
                String dni = txtDNI.getText().trim();
                String salarioStr = txtSalario.getText().trim();
                String fechaIncorporacion = txtFechaIncorporacion.getText().trim();
                String departamento = (String) cmbDepartamento.getSelectedItem();

                // Convertir salario solo si no está vacío
                Double salario = salarioStr.isEmpty() ? null : Double.parseDouble(salarioStr);

                // Llamar al controlador para agregar al empleado
                controller.addEmployee(nombre, apellido1, apellido2, dni, salario, fechaIncorporacion, departamento);

                // Si no se lanza excepción, limpiar campos
                txtNombre.setText("");
                txtApellido1.setText("");
                txtApellido2.setText("");
                txtDNI.setText("");
                txtSalario.setText("");
                txtFechaIncorporacion.setText("");
                cmbDepartamento.setSelectedIndex(0);

                JOptionPane.showMessageDialog(this, "Empleado añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                // Mostrar el mensaje de error desde el controlador
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



        // Añadir componentes al panel
        mainPanel.add(lblNombre);
        mainPanel.add(txtNombre);
        mainPanel.add(lblApellido1);
        mainPanel.add(txtApellido1);
        mainPanel.add(lblApellido2);
        mainPanel.add(txtApellido2);
        mainPanel.add(lblDNI);
        mainPanel.add(txtDNI);
        mainPanel.add(lblSalario);
        mainPanel.add(txtSalario);
        mainPanel.add(lblFechaIncorporacion);
        mainPanel.add(txtFechaIncorporacion);
        mainPanel.add(lblDepartamento);
        mainPanel.add(cmbDepartamento);
        mainPanel.add(new JLabel()); // Espacio vacío
        mainPanel.add(btnAgregar);

        add(mainPanel);
        setVisible(true);
    }
}
