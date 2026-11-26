package cafeteria.descuento;

/** Aplica un porcentaje de descuento cuando el total supera un umbral. */
public class DescuentoPorMonto implements ReglaDescuento {

    private final double umbral;
    private final double porcentaje;

    public DescuentoPorMonto(double umbral, double porcentaje) {
        if (porcentaje < 0 || porcentaje > 1) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 1.");
        }
        this.umbral = umbral;
        this.porcentaje = porcentaje;
    }

    @Override
    public String getNombre() {
        return String.format("%.0f%% si el total supera $%.2f", porcentaje * 100, umbral);
    }

    @Override
    public double calcularDescuento(double total) {
        return total > umbral ? total * porcentaje : 0.0;
    }
}
