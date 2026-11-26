package cafeteria;

import cafeteria.descuento.DescuentoPorMonto;
import cafeteria.descuento.GestorDescuentos;
import cafeteria.descuento.SinDescuento;
import cafeteria.funcional.CalculadorDescuento;
import cafeteria.persistencia.ConexionBD;
import cafeteria.vista.VentanaPrincipal;

import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Main {

    public static void main(String[] args) {
        // Reglas disponibles. Para agregar otra regla solo se añade a esta lista.
        GestorDescuentos gestor = new GestorDescuentos(List.of(
                new SinDescuento(),
                new DescuentoPorMonto(10.0, 0.15)));   // más de $10 -> 15 %

        // Expresión lambda: total final = total - mejor descuento
        CalculadorDescuento calculador = total -> total - gestor.calcularMejorDescuento(total);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignorada) {
                // Si falla, Swing usa el aspecto por defecto.
            }
            new VentanaPrincipal(calculador, new ConexionBD()).setVisible(true);
        });
    }
}
