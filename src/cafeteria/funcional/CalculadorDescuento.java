package cafeteria.funcional;

/**
 * Interfaz funcional: recibe el total del pedido y devuelve el total final a pagar.
 * Se implementa con una expresión lambda.
 */
@FunctionalInterface
public interface CalculadorDescuento {

    double calcularTotalFinal(double total);
}
