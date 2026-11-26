package cafeteria.vista;

import cafeteria.funcional.CalculadorDescuento;
import cafeteria.persistencia.ConexionBD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel con los componentes de la aplicación. Solo conoce la interfaz funcional
 * CalculadorDescuento, no las reglas concretas: la lógica de descuento queda fuera
 * de la interfaz (bajo acoplamiento).
 */
public class PanelDescuentos extends JPanel {

    private static final long serialVersionUID = 1L;

    private final transient CalculadorDescuento calculador;
    private final transient ConexionBD conexion;

    private final JTextField campoMonto = new JTextField(10);
    private final JButton botonCalcular = new JButton("Calcular Descuento");
    private final JTextArea areaResultado = new JTextArea(12, 46);

    public PanelDescuentos(CalculadorDescuento calculador, ConexionBD conexion) {
        super(new BorderLayout(8, 8));
        this.calculador = calculador;
        this.conexion = conexion;
        construirInterfaz();
        probarConexionInicial();
    }

    private void construirInterfaz() {
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel entrada = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        entrada.add(new JLabel("Monto total ($):"));
        entrada.add(campoMonto);
        entrada.add(botonCalcular);

        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        add(entrada, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        botonCalcular.addActionListener(evento -> alCalcular());
        campoMonto.addActionListener(evento -> alCalcular());
    }

    /** Al iniciar: intenta una conexión simple y muestra el resultado en el JTextArea. */
    private void probarConexionInicial() {
        try {
            int productos = conexion.contarProductos();
            agregar("[OK] Conexión exitosa a MariaDB. Productos en el catálogo: " + productos);
        } catch (SQLException ex) {
            agregar("[ERROR] No se pudo conectar a la base de datos.");
            agregar("        Detalle: " + ex.getMessage());
            agregar("        La aplicación sigue funcionando, pero los pedidos no se guardarán.");
        }
    }

    /** ActionListener del botón: 1) leer monto, 2) aplicar la lambda, 3) mostrar el resultado. */
    private void alCalcular() {
        double total;
        try {
            total = Double.parseDouble(campoMonto.getText().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            agregar("[ERROR] Ingrese un monto numérico válido (ejemplo: 25.50).");
            return;
        }
        if (!Double.isFinite(total) || total < 0) {
            agregar("[ERROR] El monto debe ser un número mayor o igual a cero.");
            return;
        }

        double totalFinal = calculador.calcularTotalFinal(total);
        double descuento = total - totalFinal;

        agregar(String.format(Locale.US,
                "Monto: $%.2f | Descuento: $%.2f | TOTAL A PAGAR: $%.2f",
                total, descuento, totalFinal));

        try {
            conexion.registrarPedido(total, descuento, totalFinal);
            agregar("[OK] Pedido registrado en la base de datos.");
        } catch (SQLException ex) {
            agregar("[AVISO] El pedido no se guardó en la base de datos: " + ex.getMessage());
        }
    }

    private void agregar(String mensaje) {
        areaResultado.append(mensaje + "\n");
        areaResultado.setCaretPosition(areaResultado.getDocument().getLength());
    }
}
