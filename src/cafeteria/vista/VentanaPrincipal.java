package cafeteria.vista;

import cafeteria.funcional.CalculadorDescuento;
import cafeteria.persistencia.ConexionBD;

import javax.swing.JFrame;

/** Ventana principal (JFrame) que contiene el JPanel de la aplicación. */
public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaPrincipal(CalculadorDescuento calculador, ConexionBD conexion) {
        super("Cafetería - Gestión de descuentos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new PanelDescuentos(calculador, conexion));
        pack();
        setLocationRelativeTo(null);
    }
}
