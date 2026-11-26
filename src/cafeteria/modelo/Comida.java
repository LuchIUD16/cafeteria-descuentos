package cafeteria.modelo;

/** Comida de la cafetería (hereda de Producto). */
public class Comida extends Producto {

    private final boolean vegetariana;

    public Comida(String nombre, double precio, boolean vegetariana) {
        super(nombre, precio);
        this.vegetariana = vegetariana;
    }

    public boolean esVegetariana() {
        return vegetariana;
    }

    @Override
    public String getCategoria() {
        return "Comida";
    }

    @Override
    public String descripcion() {
        return super.descripcion() + (vegetariana ? " - vegetariana" : "");
    }
}
