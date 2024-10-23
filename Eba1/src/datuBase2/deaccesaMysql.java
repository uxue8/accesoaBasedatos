package datuBase2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class deaccesaMysql {

	public static void main(String[] args) {
		String datuBaseIzena = "farmacia";
		String host = "localhost";
		String port = "3306";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;

		String db_erabiltzailea = "root";
		String db_pasahitza = "";
		Connection c = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			 conn = DriverManager.getConnection("jdbc:ucanaccess://farmacia.mdb");
			 
			 DatabaseMetaData metadatuak= conn.getMetaData();
			 ResultSet taulak=metadatuak.getTables(null, null, "%",new String [] {"TABLE"});
			 
			 while(taulak.next()) {
				 String taulaizena=taulak.getString("TABLE_NAME");
				 System.out.println(taulaizena);
				 ResultSet zutabeak=metadatuak.getColumns(null, null, taulaizena, null);
			 }
					 
			 
			 
			 
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			
			
			
			
		}
		
		catch(Exception e) {
			
			
		}
}
}