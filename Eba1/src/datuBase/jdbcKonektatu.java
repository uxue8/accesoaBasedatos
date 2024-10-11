package datuBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcKonektatu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String datuBaseIzena = "kutxabank";
		String host = "localhost";
		String port = "3306";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;

		String db_erabiltzailea = "root";
		String db_pasahitza = "";
		int datua = 0;

		Connection c = null;
		System.out.println("Sartu izen bat");
		String izena = sc.nextLine();

		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT kantitatea FROM erabiltzaile_mugimenduak WHERE izena='" + izena + "'");

			boolean aurkituta = false;
			
			while (rs.next()) { 
				datua = rs.getInt("kantitatea");
				//datua = rs.getInt(1);  Horrela ere egin ahal da
				System.out.println("diru kantitatea:" + datua);
				aurkituta = true;
			}
			
			if(!aurkituta) {
				System.out.println("Ez da existitzen izen hori");
			}
			
			
			
			//insert 
			if(aurkituta) {
				//egin dut con la misma persona que encuentro
				Statement sInsert = c.createStatement();
				System.out.println("Sartu kantitate bat:");
				int kant= sc.nextInt();
				
				int nFila = s.executeUpdate("INSERT INTO erabiltzaile_mugimenduak (izena,kantitatea) VALUES('"+izena+"','"+kant+"');");
				System.out.println("Registro insertado");
			}
			
			

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
