package domain;

public class VehiculoElectrico extends Vehiculo {
    private double kwhBase;

    public VehiculoElectrico(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                             Sucursal sucursal, double kwhBase) {
        super(VehiculoTipo.ELECTRICO, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kwhBase = kwhBase;
    }

    @Override
    public double calcularConsumo(double kilometros) {
        double total = (16 * kilometros) / 100;
        if (capacidadCarga > 1200) {
            total *= 1.15;
        }
        return total;
    }
}