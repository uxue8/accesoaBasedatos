package datuBase2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class cogerIds {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String datuBaseIzena = "empresadb";
		String host = "localhost";
		String port = "3306";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;

		String db_erabiltzailea = "root";
		String db_pasahitza = "";
		int datua = 0;
		
		Connection c = null;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			
			String sql_insert1="INSERT INTO empleados (nombre) VALUES(?)";
			//Return generated keys solo funciona si si el id es autoincrementable
			PreparedStatement ps_select = c.prepareStatement(sql_insert1,PreparedStatement.RETURN_GENERATED_KEYS);
			ps_select.setString(1, "Pablo");
			ps_select.executeUpdate();
			ps_select.setString(1, "joel");
			ps_select.executeUpdate();
			
			//coge el ultimo id creado
			rs= ps_select.getGeneratedKeys();
			rs.next();
			
			System.out.println("Empleados insertado");		
			//aqui lo muestro
			System.out.println("el id del ultimo empleado insertado:" + rs.getInt(1));
	}catch(Exception e) {
		
	}

}
}
