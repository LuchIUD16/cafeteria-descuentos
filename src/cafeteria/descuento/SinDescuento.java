package cafeteria.descuento;

/** Regla neutra: el cliente no recibe descuento (0 %). */
public class SinDescuento implements ReglaDescuento {

    @Override
    public String getNombre() {
        return "Sin descuento";
    }

    @Override
    public double calcularDescuento(double total) {
        return 0.0;
    }
}
