package datuBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class scrollableResultSet {

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
		System.out.println( "a- aurrekoa , d hurrengoa, 1 lehenengoa, 9 azkena , q irten:");
		String a =sc.nextLine();
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			
			//Type_scroll_insensitive no enseña los datos actualizados si otro en la base de datos hace un cambio
			//para que salgan actualizados ResultSet.TYPE_SCROLL_SENSITIVE(no necesito porque solo uso yo la base de datos)
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//ResultSet.CONCUR_READ_ONLY solo lectura 
			//ResultSet.CONCUR_UPDATABLE modifica
			
			String sql= "SELECT * FROM erabiltzaile_mugimenduak";
			PreparedStatement pe= c.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet r = pe.executeQuery();
				
			while(r.next()) {
				String data = r.getString(1);
				String izena = r.getString(2);
				int kantitatea = r.getInt(3);
				System.out.println(data + " | " + izena + " | " + kantitatea);
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
