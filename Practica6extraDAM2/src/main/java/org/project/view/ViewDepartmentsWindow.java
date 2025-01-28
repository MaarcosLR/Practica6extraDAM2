package org.project.view;

import org.project.controller.DepartmentController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewDepartmentsWindow extends JFrame {

    private final DepartmentController controller;

    public ViewDepartmentsWindow(DepartmentController controller) {
        this.controller = controller;

        // Configuración del JFrame
        setTitle("Ver Departamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear el panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("Lista de Departamentos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Crear la lista de departamentos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> departmentList = new JList<>(listModel);
        departmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departmentList.setVisibleRowCount(10);
        departmentList.setFont(new Font("Arial", Font.BOLD, 18));
        JScrollPane scrollPane = new JScrollPane(departmentList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botón para cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        mainPanel.add(btnCerrar, BorderLayout.SOUTH);

        // Añadir el panel al JFrame
        add(mainPanel);

        // Cargar datos de la base de datos
        loadDepartments(listModel);

        setVisible(true);
    }

    private void loadDepartments(DefaultListModel<String> listModel) {
        try {
            List<String> departments = controller.getAllDepartments();
            for (String department : departments) {
                listModel.addElement(department);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los departamentos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
