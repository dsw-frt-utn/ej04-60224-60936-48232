package views;

import data.Persistencia;
import domain.*;
import javax.swing.*;
import java.awt.*;

public class VentanaAltaVehiculo extends JFrame {
    private JTextField txtPatente, txtMarcaNombre, txtMarcaPais, txtModelo, txtAnio, txtCapacidad;
    private JComboBox<String> cbTipo;
    private JComboBox<String> cbSucursales;
    private JTextField txtDatoExtra1, txtDatoExtra2;
    private JLabel lblDatoExtra1, lblDatoExtra2;

    public VentanaAltaVehiculo() {
        setTitle("Alta de Vehículo");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel form = new JPanel(new GridLayout(11, 2, 5, 5));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        form.add(new JLabel("Patente:"));
        txtPatente = new JTextField();
        form.add(txtPatente);

        form.add(new JLabel("Marca (Nombre):"));
        txtMarcaNombre = new JTextField();
        form.add(txtMarcaNombre);

        form.add(new JLabel("Marca (País):"));
        txtMarcaPais = new JTextField();
        form.add(txtMarcaPais);

        form.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        form.add(txtModelo);

        form.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        form.add(txtAnio);

        form.add(new JLabel("Capacidad Carga (kg):"));
        txtCapacidad = new JTextField();
        form.add(txtCapacidad);

        form.add(new JLabel("Sucursal:"));
        cbSucursales = new JComboBox<>();
        for (Sucursal s : Persistencia.getSucursales()) {
            cbSucursales.addItem(s.getCodigo());
        }
        form.add(cbSucursales);

        form.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Eléctrico", "Combustible"});
        form.add(cbTipo);

        lblDatoExtra1 = new JLabel("kWh Base:");
        txtDatoExtra1 = new JTextField();
        form.add(lblDatoExtra1);
        form.add(txtDatoExtra1);

        lblDatoExtra2 = new JLabel("Litros Extra:");
        txtDatoExtra2 = new JTextField();
        txtDatoExtra2.setEnabled(false); // Oculto por defecto para eléctrico
        form.add(lblDatoExtra2);
        form.add(txtDatoExtra2);

        // Cambiar campos según el tipo seleccionado
        cbTipo.addActionListener(e -> {
            if (cbTipo.getSelectedIndex() == 0) { // Eléctrico
                lblDatoExtra1.setText("kWh Base:");
                txtDatoExtra2.setEnabled(false);
            } else { // Combustible
                lblDatoExtra1.setText("Km por Litro:");
                txtDatoExtra2.setEnabled(true);
            }
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarVehiculo());

        add(form, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardarVehiculo() {
        try {
            Marca marca = new Marca(txtMarcaNombre.getText(), txtMarcaPais.getText());
            Sucursal sucursal = Persistencia.getSucursales().get(cbSucursales.getSelectedIndex());
            
            String patente = txtPatente.getText();
            String modelo = txtModelo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            
            Vehiculo nuevoVehiculo;

            if (cbTipo.getSelectedIndex() == 0) { // Eléctrico
                double kwhBase = Double.parseDouble(txtDatoExtra1.getText());
                nuevoVehiculo = new VehiculoElectrico(patente, marca, modelo, anio, capacidad, sucursal, kwhBase);
            } else { // Combustible
                double kmLitro = Double.parseDouble(txtDatoExtra1.getText());
                double litrosExtra = Double.parseDouble(txtDatoExtra2.getText());
                nuevoVehiculo = new VehiculoCombustible(patente, marca, modelo, anio, capacidad, sucursal, kmLitro, litrosExtra);
            }

            Persistencia.agregarVehiculo(nuevoVehiculo);
            JOptionPane.showMessageDialog(this, "Vehículo agregado con éxito.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Verifique los números.");
        }
    }
}
