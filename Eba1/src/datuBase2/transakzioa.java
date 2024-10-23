package datuBase2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class transakzioa {

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
		String izen1;
		String izen2;
		int kantitatea;
		System.out.println("izen 1:");
		izen1=sc.nextLine();
		System.out.println("izen 2:");	
		izen2=sc.nextLine();
		System.out.println("kantitatea:");
		kantitatea=sc.nextInt();
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");

			Statement s = c.createStatement();
			
			c.setAutoCommit(false);
			
			int rs = s.executeUpdate("INSERT INTO erabiltzaile_mugimenduak (izena,kantitatea) VALUES"
					+ "('"+izen1+"','"+ -kantitatea+"')");
			
			int rs2 = s.executeUpdate("INSERT INTO erabiltzaile_mugimenduak (izena,kantitatea) VALUES"
					+ "('"+izen2+"','ss')");
			c.commit();
			
			System.out.println("Registro insertado");
			
			
		}catch(SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
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
