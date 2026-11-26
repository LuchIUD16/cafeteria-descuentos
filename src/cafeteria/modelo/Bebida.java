package cafeteria.modelo;

/** Bebida de la cafetería (hereda de Producto). */
public class Bebida extends Producto {

    private final String tamano;

    public Bebida(String nombre, double precio, String tamano) {
        super(nombre, precio);
        this.tamano = tamano;
    }

    public String getTamano() {
        return tamano;
    }

    @Override
    public String getCategoria() {
        return "Bebida";
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " - tamaño " + tamano;
    }
}
