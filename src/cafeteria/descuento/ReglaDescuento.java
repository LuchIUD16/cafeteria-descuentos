package cafeteria.descuento;

/**
 * Contrato de una regla de descuento. Para agregar una regla nueva basta con crear
 * otra clase que la implemente (principio abierto/cerrado de SOLID).
 */
public interface ReglaDescuento {

    String getNombre();

    /** Devuelve el valor del descuento (no el total final) para el total indicado. */
    double calcularDescuento(double total);
}
