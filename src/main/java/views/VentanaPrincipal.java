package views;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Menú Principal - Sistema de Logística");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JButton btnListar = new JButton("Listar Vehículos");
        JButton btnAgregar = new JButton("Agregar Vehículo");

        btnListar.addActionListener(e -> {
            // Instanciamos la vista de listado
            ListarVehiculosView lista = new ListarVehiculosView();
            lista.setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
            // Instanciamos tu vista de Alta
            VentanaAltaVehiculo alta = new VentanaAltaVehiculo();
            alta.setVisible(true);
        });

        panel.add(btnAgregar);
        panel.add(btnListar);
        add(panel);
    }
}