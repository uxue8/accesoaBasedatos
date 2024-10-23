package azterketa2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Scanner;

public class ariketa3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String datuBaseIzena = "azterketaB";
		String host = "192.168.37.90";
		String port = "3307";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;

		String db_erabiltzailea = "usuario";
		String db_pasahitza = "password";
		int datua = 0;
		Connection c = null;
		Connection c2 = null;
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			 transakzioakIkusi (c);
			 
			 
			 //erregitroa gehitu
				c2 = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			 System.out.println("zein da izena?");
			 String izena= sc.nextLine();
			 System.out.println("zein da kodea?");
			 String kod = sc.nextLine();
			 System.out.println("zein da prezioa?");
			 Float pre=sc.nextFloat();
			 
			 
			 
			 Timestamp   fecha= new Timestamp(Calendar.getInstance().getTimeInMillis());
				String sql_insert1="INSERT INTO CoinMarketCap(name, code, price, updateStamp) VALUES(?, ?, ?, ?)";
        		
    			PreparedStatement ps_select = c.prepareStatement(sql_insert1);
    			ps_select.setString(1,izena);
    			ps_select.setString(2, kod);
    			ps_select.setFloat(3,pre);	
    			ps_select.setTimestamp(4,fecha);
    			ps_select.executeUpdate();
    			
    			System.out.println("datu basera gehitu da");
    			 
    			 transakzioakIkusi (c2);
    			 
			
			 
			 
		}catch(Exception e){
	       	System.out.println("no se ha podido conectar a la base de datos:"+e.getMessage());
	    }
		
		

	}
	
public static void transakzioakIkusi (Connection c) {
		
		Scanner sc = new Scanner(System.in);
		String query = "SELECT * FROM CoinMarketCap";
		
		try {
		
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
				
				System.out.println("Aukeratu aukera bat: [a] Aurrekoa, [d] Hurrengoa, [1] Lehenengoa, [5]Bostagarrena, [9] Azkena, [q] Irten");
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
			System.out.println("id:"+res.getInt("id")+"Izena: " + res.getString("name") + ", code: "+res.getString("code") +"precio: "+res.getFloat("price")+"eguneraketa data:"+ res.getDate("updateStamp"));
		}
		


	
	
	

}
