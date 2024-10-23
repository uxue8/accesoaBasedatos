package datuBase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.h2.tools.Server;

public class h2dbKudeatu {

	public static void main(String[] args) {
	    // Konexio URLa (fitxategian edo memorian)
        String jdbcURL = "jdbc:h2:mem:test"; 
        String user = "sa";
        String password = "";

        Server webServer = null;
        try {
            //H2 zerbitzaria martxan jarri, nabigatzailetik sartzeko
            webServer = Server.createWebServer("-webPort", "8084", "-tcpAllowOthers").start();
            System.out.println("Consola web de H2 iniciada en http://localhost:8082");

            // DB konexioa
            try (Connection conn = DriverManager.getConnection(jdbcURL, user, password)) {
                if (conn != null) {
                    System.out.println("H2ra konekzioa ezarrita.");
                    taulakSortu(conn);
                    datuakSartu(conn);
                    System.out.println("DB sortua eta datuak sortuta.");

                    // Programa martxan mantendu, erabiltzaileak zerbait idatzi arte
                    System.out.println("Zapaldu edozein tekla programa bukatzeko...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (webServer != null) {
                webServer.stop();
                System.out.println("Consola web de H2 cerrada.");
            }
        }
    }

    // Taulak sortzeko metodoa
    public static void taulakSortu(Connection conn) throws SQLException {
        String tablaClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                "id_cliente INT PRIMARY KEY AUTO_INCREMENT, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL" +
                ");";

        String tablaPedidos = "CREATE TABLE IF NOT EXISTS pedidos (" +
                "id_pedido INT PRIMARY KEY AUTO_INCREMENT, " +
                "id_cliente INT, " +
                "descripcion VARCHAR(255), " +
                "FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE CASCADE" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(tablaClientes);
            stmt.execute(tablaPedidos);
            System.out.println("Tablas creadas con éxito.");
        }
    }

    // Tauletan instantziak sartzeko metodoa
    public static void datuakSartu(Connection conn) throws SQLException {
        String insertClientes = "INSERT INTO clientes (nombre, email) VALUES " +
                "('Juan Pérez', 'juan@gmail.com'), " +
                "('Ana Gómez', 'ana@gmail.com');";

        String insertPedidos = "INSERT INTO pedidos (id_cliente, descripcion) VALUES " +
                "(1, 'Pedido de libros'), " +
                "(2, 'Pedido de ropa');";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertClientes);
            stmt.execute(insertPedidos);
            System.out.println("Datos insertados con éxito.");
        }



	}

}
