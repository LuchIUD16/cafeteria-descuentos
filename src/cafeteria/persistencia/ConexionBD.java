package cafeteria.persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexionBD {

    private static final String URL_POR_DEFECTO = "jdbc:mariadb://localhost:3306/cafeteria";
    private static final String USUARIO_POR_DEFECTO = "root";
    private static final String CLAVE_POR_DEFECTO = "1234";

    private final String url;
    private final String usuario;
    private final String clave;

    public ConexionBD() {
        this(valor("CAFETERIA_DB_URL", URL_POR_DEFECTO),
             valor("CAFETERIA_DB_USER", USUARIO_POR_DEFECTO),
             valor("CAFETERIA_DB_PASS", CLAVE_POR_DEFECTO));
    }

    public ConexionBD(String url, String usuario, String clave) {
        this.url = url;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUrl() {
        return url;
    }

    private static String valor(String variable, String porDefecto) {
        String v = System.getenv(variable);
        return (v == null || v.isBlank()) ? porDefecto : v;
    }

    private Connection abrir() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }

    /** Consulta simple para comprobar la conexión: cuenta los productos del catálogo. */
    public int contarProductos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos";
        try (Connection conexion = abrir();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {
            resultado.next();
            return resultado.getInt(1);
        }
    }

    /** Guarda en la tabla pedidos el resultado de un cálculo de descuento. */
    public void registrarPedido(double total, double descuento, double totalFinal)
            throws SQLException {
        String sql = "INSERT INTO pedidos (total, descuento, total_final) VALUES (?, ?, ?)";
        try (Connection conexion = abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setBigDecimal(1, aDinero(total));
            sentencia.setBigDecimal(2, aDinero(descuento));
            sentencia.setBigDecimal(3, aDinero(totalFinal));
            sentencia.executeUpdate();
        }
    }

    private static BigDecimal aDinero(double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP);
    }
}
