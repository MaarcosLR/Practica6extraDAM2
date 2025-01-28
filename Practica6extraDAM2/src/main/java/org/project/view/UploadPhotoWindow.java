package org.project.view;

import org.project.controller.EmployeeController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class UploadPhotoWindow extends JFrame {

    private EmployeeController employeeController;

    public UploadPhotoWindow(EmployeeController employeeController) {
        this.employeeController = employeeController;

        // Configuración del JFrame
        setTitle("Subir Foto de Empleado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear el panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear la lista de empleados
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> employeesList = new JList<>(listModel);
        employeesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeesList.setVisibleRowCount(10);
        employeesList.setFont(new Font("Arial", Font.BOLD, 18));
        JScrollPane scrollPane = new JScrollPane(employeesList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Crear el panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 filas, 1 columna, con espacio entre botones

        // Botón para subir foto
        JButton uploadButton = new JButton("Subir Foto");
        uploadButton.addActionListener(e -> {
            String selectedEmployee = employeesList.getSelectedValue();
            if (selectedEmployee == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar Foto");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        employeeController.uploadPhotoForEmployee(selectedEmployee, selectedFile.getAbsolutePath());
                        JOptionPane.showMessageDialog(this, "Foto subida con éxito para " + selectedEmployee, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error al subir la foto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(uploadButton);

        // Botón para cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        buttonPanel.add(btnCerrar);

        // Agregar el panel de botones al sur del panel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        loadEmployees(listModel);

        setVisible(true);
    }

    private void loadEmployees(DefaultListModel<String> listModel) {
        try {
            List<String> employees = employeeController.getAllEmployees();
            for (String employee : employees) {
                listModel.addElement(employee);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los empleados: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
