package domain;

public class VehiculoCombustible extends Vehiculo {
    private double kilometrosPorLitro;
    private double litrosExtra;

    public VehiculoCombustible(String patente, Marca marca, String modelo, int anio, double capacidadCarga,
                               Sucursal sucursal, double kilometrosPorLitro, double litrosExtra) {
        super(VehiculoTipo.COMBUSTIBLE, patente, marca, modelo, anio, capacidadCarga, sucursal);
        this.kilometrosPorLitro = kilometrosPorLitro;
        this.litrosExtra = litrosExtra;
    }
    
    public double getKilometrosPorLitro() { return kilometrosPorLitro; }
    public double getLitrosExtra() { return litrosExtra; }

    @Override
    public double calcularConsumo(double kilometros) {
        double consumo = kilometros / kilometrosPorLitro;
        if (2026 - anio > 5) {
            consumo += (kilometros / 15) * litrosExtra;
        }
        return consumo;
    }
}