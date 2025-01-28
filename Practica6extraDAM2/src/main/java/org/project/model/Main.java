package org.project.model;

import org.project.controller.DepartmentController;
import org.project.controller.EmployeeController;
import org.project.view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    public Main() {

        // Configuración del JFrame
        setTitle("Gestión Directiva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear un JPanel para aplicar el borde
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 filas y 3 columnas, con espaciado de 10px entre componentes

        // Añadir borde alrededor del panel
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // margen de 20px alrededor del panel

        // Crear botones con su actionListener correspondiente.
        addButton(buttonsPanel, "Añadir Empleado", e -> {
            EmployeeController employeeController = new EmployeeController();
            new AddEmployeeWindow(employeeController);
        });
        addButton(buttonsPanel, "Añadir Departamento", e -> {
            DepartmentController departmentController = new DepartmentController();
            new AddDepartmentsWindow(departmentController);
        });
        addButton(buttonsPanel, "Ver Departamentos", e -> {
            DepartmentController departmentController = new DepartmentController();
            new ViewDepartmentsWindow(departmentController);
        });
        addButton(buttonsPanel, "Subir Foto de Empleado", e -> {
            EmployeeController employeeController = new EmployeeController();
            new UploadPhotoWindow(employeeController);
        });


        // Crear botón "Salir" con color rojo
        JButton exitButton = new JButton("Salir");
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(e -> {dispose();});
        buttonsPanel.add(exitButton);

        // Añadir el panel al JFrame
        add(buttonsPanel);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    public static void main(String[] args) {
        new Main();
    }
}
