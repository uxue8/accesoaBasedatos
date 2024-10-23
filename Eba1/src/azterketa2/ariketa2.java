package azterketa2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.h2.tools.Server;

public class ariketa2 {

	public static void main(String[] args) {
		   // Konexio URLa (fitxategian edo memorian)
        String jdbcURL = "jdbc:h2:~/DAazterketa4"; 
        String user = "sa";
        String password = "";

        Server webServer = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            //H2 zerbitzaria martxan jarri, nabigatzailetik sartzeko
            webServer = Server.createWebServer("-webPort", "8085", "-tcpAllowOthers").start();
            System.out.println("Consola web de H2 iniciada en http://localhost:8085");

            // DB konexioa
            try (Connection conn = DriverManager.getConnection(jdbcURL, user, password)) {
                if (conn != null) {
                    System.out.println("H2ra konekzioa ezarrita.");
                    taulakSortu(conn);
                    datuakSartu(conn);
                    System.out.println("DB sortua eta datuak sortuta.");

                    // Programa martxan mantendu, erabiltzaileak zerbait idatzi arte
                  
                    Scanner scanner = new Scanner(System.in);
                  
                    
                    System.out.println("ikaslearen izena:");
                    
                    String ikasleBerri = scanner.nextLine();
                    
                    System.out.println("ikaslearen abizena:");
                    
                    String ikaslearenAbizena= scanner.nextLine();
                    
                    System.out.println("ikastaroa:");
                    
                    String ikastaroa= scanner.nextLine();
                    
                    System.out.println("ikaslearen deskribapena:");
                    String deskribapena= scanner.nextLine();
                    
                    
                    //ikaslea
                	String sql_insert1="INSERT INTO Ikasleak(izena, abizena) VALUES(?, ?)";
        		
        			PreparedStatement ps_select = conn.prepareStatement(sql_insert1,PreparedStatement.RETURN_GENERATED_KEYS);
        			ps_select.setString(1,ikasleBerri);
        			ps_select.setString(2, ikaslearenAbizena);

        			ps_select.executeUpdate();
        
        			rs= ps_select.getGeneratedKeys();
        			rs.next();
        			int idpro=rs.getInt(1);
        			
        			//ikastaroak
        			
        			String sql_insert2="INSERT INTO Ikastaroak(izenburua, deskribapena) VALUES(?, ?)";
        			
        			PreparedStatement ps_select2 = conn.prepareStatement(sql_insert2,PreparedStatement.RETURN_GENERATED_KEYS);
        			
        			ps_select2.setString(1,ikastaroa);
        			ps_select2.setString(2, deskribapena);

        			ps_select2.executeUpdate();
        			
        			rs2= ps_select2.getGeneratedKeys();
        			rs2.next();
        		
        			int idIkastaro = rs2.getInt(1);
        			
        			//ikasle ikastaro 
        			
        			
        			String sql_insert3="INSERT INTO IkasleIkastaro VALUES (?, ?)";
        			PreparedStatement ps_select3 = conn.prepareStatement(sql_insert3);
        			
        			ps_select3.setInt(1,idpro);
        			ps_select3.setInt(2,idIkastaro);
        			ps_select3.executeUpdate();
        			
        			System.out.println("Ikaslea eta ikastaroa behar bezala gehitu dira.Gainera ikaslea ikastaroan matrikulatu da");

        			
        			
        			
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (webServer != null) {
               // webServer.stop();
              // System.out.println("Consola web de H2 cerrada.");
            }
        }
    }

    // Taulak sortzeko metodoa
    public static void taulakSortu(Connection conn) throws SQLException {
    	String createIkasleak = "CREATE TABLE Ikasleak (" +
                "ikasle_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "izena VARCHAR(100) NOT NULL, " +
                "abizena VARCHAR(100) NOT NULL" +
                ");";

		String createIkastaroak = "CREATE TABLE Ikastaroak (" +
		                "ikastaro_id INT PRIMARY KEY AUTO_INCREMENT, " +
		                "izenburua VARCHAR(100) NOT NULL, " +
		                "deskribapena TEXT" +
		                ");";
		
		String createIkasleIkastaro = "CREATE TABLE IkasleIkastaro (" +
		                "ikasle_id INT, " +
		                "ikastaro_id INT, " +
		                "PRIMARY KEY (ikasle_id, ikastaro_id), " +
		                "FOREIGN KEY (ikasle_id) REFERENCES Ikasleak(ikasle_id) ON DELETE CASCADE, " +
		                "FOREIGN KEY (ikastaro_id) REFERENCES Ikastaroak(ikastaro_id) ON DELETE CASCADE" +
		                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createIkasleak );
            stmt.execute( createIkastaroak);
            stmt.execute(createIkasleIkastaro);
            System.out.println("Tablas creadas con éxito.");
        }
    }

    // Tauletan instantziak sartzeko metodoa
    public static void datuakSartu(Connection conn) throws SQLException {
    	String insertIkasleak = "INSERT INTO Ikasleak (izena, abizena) VALUES " +
                "('Ane', 'Lopez'), " +
                "('Iker', 'Garcia');";

		String insertIkastaroak = "INSERT INTO Ikastaroak (izenburua, deskribapena) VALUES " +
		                "('Matematika', 'Oinarrizko matematikak'), " +
		                "('Historia', 'Historiako oinarriak');";
		
		String insertIkasleIkastaro = "INSERT INTO IkasleIkastaro (ikasle_id, ikastaro_id) VALUES " +
		                "(1, 1), " +  // Ane Matematika ikastaroan
		                "(1, 2), " +  // Ane Historia ikastaroan
		                "(2, 1);";    // Iker Matematika ikastaroan

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(insertIkasleak);
            stmt.execute(insertIkastaroak);
            stmt.execute(insertIkasleIkastaro);
            System.out.println("Datos insertados con éxito.");
        }



	}

}
