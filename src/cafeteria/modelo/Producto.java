package cafeteria.modelo;

/**
 * Clase base de todos los productos de la cafetería.
 * Las subclases definen su categoría y pueden ampliar la descripción (polimorfismo).
 */
public abstract class Producto {

    private final String nombre;
    private final double precio;

    protected Producto(String nombre, double precio) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    /** Cada subclase indica su categoría (Bebida, Comida, ...). */
    public abstract String getCategoria();

    /** Descripción base; las subclases pueden sobrescribirla. */
    public String descripcion() {
        return String.format("%s: %s ($%.2f)", getCategoria(), nombre, precio);
    }
}
