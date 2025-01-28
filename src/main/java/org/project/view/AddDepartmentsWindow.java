package org.project.view;

import org.project.controller.DepartmentController;

import javax.swing.*;
import java.awt.*;

public class AddDepartmentsWindow extends JFrame {

    private final DepartmentController controller;

    public AddDepartmentsWindow(DepartmentController departmentController) {
        this.controller = departmentController;

        // Configuración del JFrame
        setTitle("Añadir Departamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear campos de entrada
        JLabel lblNombreDepartamento = new JLabel("Nombre del Departamento:");
        JTextField txtNombreDepartamento = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");

        // Crear JTextArea para la descripción
        JTextArea txtDescripcion = new JTextArea(5, 20); // 5 filas y 20 columnas
        txtDescripcion.setLineWrap(true); // Permite el ajuste automático de línea
        txtDescripcion.setWrapStyleWord(true); // Ajusta el texto por palabras
        JScrollPane scrollPane = new JScrollPane(txtDescripcion); // Añadir JScrollPane para hacer la descripción desplazable
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de desplazamiento vertical

        JButton btnAgregarDepartamento = getJButton(txtNombreDepartamento, txtDescripcion);

        // Añadir componentes al panel
        mainPanel.add(lblNombreDepartamento);
        mainPanel.add(txtNombreDepartamento);
        mainPanel.add(lblDescripcion);
        mainPanel.add(scrollPane); // Añadir JScrollPane que contiene el JTextArea
        mainPanel.add(new JLabel()); // Espacio vacío
        mainPanel.add(btnAgregarDepartamento);

        // Añadir el panel al JFrame
        add(mainPanel);
        setVisible(true);
    }

    private JButton getJButton(JTextField txtNombreDepartamento, JTextArea txtDescripcion) {
        JButton btnAgregarDepartamento = new JButton("Agregar Departamento");
        btnAgregarDepartamento.addActionListener(e -> {
            String nombreDepartamento = txtNombreDepartamento.getText().trim();
            String descripcion = txtDescripcion.getText().trim();

            try {
                // Delegar toda la lógica y manejo de errores al controlador
                controller.addDepartment(nombreDepartamento, descripcion);
                JOptionPane.showMessageDialog(
                        AddDepartmentsWindow.this,
                        "Departamento añadido con éxito.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // Limpiar los campos después de añadir el departamento
                txtNombreDepartamento.setText("");
                txtDescripcion.setText("");

            } catch (Exception ex) {
                // Mostrar cualquier error que devuelva el controlador
                JOptionPane.showMessageDialog(
                        AddDepartmentsWindow.this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        return btnAgregarDepartamento;
    }
}