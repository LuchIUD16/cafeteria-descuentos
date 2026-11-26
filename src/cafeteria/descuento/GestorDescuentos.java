package cafeteria.descuento;

import java.util.List;

/**
 * Administra un conjunto de reglas y entrega la más favorable para el cliente.
 * No conoce ninguna regla concreta: depende solo de la abstracción ReglaDescuento.
 */
public class GestorDescuentos {

    private final List<ReglaDescuento> reglas;

    public GestorDescuentos(List<ReglaDescuento> reglas) {
        this.reglas = List.copyOf(reglas);
    }

    /** Mayor descuento que ofrece alguna de las reglas registradas. */
    public double calcularMejorDescuento(double total) {
        return reglas.stream()
                .mapToDouble(regla -> regla.calcularDescuento(total))
                .max()
                .orElse(0.0);
    }
}
