package cafeteria.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Pedido de un cliente: conjunto de productos cuyo total se calcula con polimorfismo. */
public class Pedido {

    private final List<Producto> productos = new ArrayList<>();

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
