package datuBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class accederBaseSensitive {

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
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			 transakzioakIkusi (c);
		}catch(Exception e){
	       	
	    }
	    
		

	}
	
	public static void transakzioakIkusi (Connection c) {
		
		Scanner sc = new Scanner(System.in);
		String query = "SELECT * FROM erabiltzaile_mugimenduak";
		
		try {
			//cuando use sensitive si o si usar concur_updatable
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resSet = s.executeQuery(query);
			
			//Konprobatu erregistroak daudela
			if (!resSet.next()) {
				System.out.println("Ez daude erregistrorik.");
			} else {
				erregErakutsi(resSet);
			}
			
			String erantzuna;
			do {
				
				System.out.println("Aukeratu aukera bat: [a] Aurrekoa, [d] Hurrengoa, [1] Lehenengoa, [9] Azkena, [q] Irten");
				erantzuna = sc.nextLine();
				
				switch (erantzuna) {
			
				case "a":
					if (!resSet.isFirst()) {
						resSet.previous();
						erregErakutsi(resSet);
						
					} else {
						System.out.println("Lehenengoan zaude.");
					}
					break;
				case "d":
					if (resSet.next()) {
						erregErakutsi(resSet);
						//izenaGaldetuAldatu(resSet);
					} else {
						System.out.println("Ez daude erregsitro gehiago, lehena erakutsiko dizut");
						resSet.first();
						//izenaGaldetuAldatu(resSet);
						erregErakutsi(resSet);
					}
					break;
				case "1":
					resSet.first();
					
					erregErakutsi(resSet);
					//izenaGaldetuAldatu(resSet);
					break;
				case "9":
					resSet.last();
					erregErakutsi(resSet);
					//izenaGaldetuAldatu(resSet);
					break;
				}
			}while(!erantzuna.equalsIgnoreCase("q"));
		} catch (Exception e) {
			System.out.println("Errorea: " + e);
		} finally {
			try {
				if (c != null) c.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			sc.close();
		}
	}

public static void erregErakutsi (ResultSet res) throws SQLException {
	res.refreshRow();
	System.out.println("Izena: " + res.getString("izena") + ", email: " + res.getString("kantitatea"));
}


}
