package cafeteria;

import cafeteria.descuento.DescuentoPorMonto;
import cafeteria.descuento.GestorDescuentos;
import cafeteria.descuento.SinDescuento;
import cafeteria.funcional.CalculadorDescuento;
import cafeteria.modelo.Bebida;
import cafeteria.modelo.Comida;
import cafeteria.modelo.Pedido;
import cafeteria.modelo.Producto;

import java.util.List;


public class PruebasDescuento {

    private static int fallos = 0;

    public static void main(String[] args) {
        GestorDescuentos gestor = new GestorDescuentos(List.of(
                new SinDescuento(), new DescuentoPorMonto(10.0, 0.15)));
        CalculadorDescuento calculador = total -> total - gestor.calcularMejorDescuento(total);

        verificar("Total $5.00 (sin descuento)", calculador.calcularTotalFinal(5.0), 5.0);
        verificar("Total $10.00 (no supera el umbral)", calculador.calcularTotalFinal(10.0), 10.0);
        verificar("Total $10.01 (supera el umbral)", calculador.calcularTotalFinal(10.01), 8.5085);
        verificar("Total $20.00 (15 %)", calculador.calcularTotalFinal(20.0), 17.0);
        verificar("Total $100.00 (15 %)", calculador.calcularTotalFinal(100.0), 85.0);

        Pedido pedido = new Pedido();
        pedido.agregar(new Bebida("Café americano", 3.50, "Mediano"));
        pedido.agregar(new Bebida("Capuchino", 4.80, "Grande"));
        pedido.agregar(new Comida("Sándwich de pollo", 6.20, false));
        pedido.agregar(new Comida("Croissant", 2.90, true));

        System.out.println();
        System.out.println("Pedido de ejemplo (polimorfismo en descripcion()):");
        for (Producto producto : pedido.getProductos()) {
            System.out.println("  - " + producto.descripcion());
        }
        double total = pedido.calcularTotal();
        verificar("Pedido de ejemplo: total $17.40 con 15 %",
                calculador.calcularTotalFinal(total), 14.79);

        System.out.println();
        System.out.println(fallos == 0
                ? "Todas las pruebas pasaron."
                : fallos + " prueba(s) fallaron.");
    }

    private static void verificar(String descripcion, double obtenido, double esperado) {
        boolean ok = Math.abs(obtenido - esperado) < 0.0001;
        if (!ok) {
            fallos++;
        }
        System.out.printf("[%s] %s -> total final %.2f (esperado %.2f)%n",
                ok ? "PASA" : "FALLA", descripcion, obtenido, esperado);
    }
}
