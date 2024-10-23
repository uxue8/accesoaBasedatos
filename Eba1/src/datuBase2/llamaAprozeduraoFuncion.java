package datuBase2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class llamaAprozeduraoFuncion {

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
		CallableStatement cs = null;
		CallableStatement cs2= null;
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
			
			
			//Procedure 

			cs = c.prepareCall("{call InsertarEmpleado(?, ?, ?, ?)}");
			
			//meto los parametros
			cs.setString(1,"liam");
			cs.setInt(2, 18);
			cs.setString(3, "administrador");
			cs.setDate(4, null);
			
			// todo esto si el procedure tiene un out
			//en la procedure lo que va a sacar es decir el out
			//cs.registerOutParameter(2, java.sql.Types.DOUBLE);
			//aqui cojo la respuesta
			//double euroak = cs.getDouble(2);
			
			//ejecuto el procedure
			cs.execute();
			
			
			
			System.out.println("Empleado insertado");
			
			//FUNCION
			
			
			cs2 = c.prepareCall("{ ? = CALL ObtenerEdadEmpleado(?) }");
			cs2.registerOutParameter(1, java.sql.Types.INTEGER);
			//el id del empleado
			cs2.setInt(2, 2);
			
			
			
			cs2.execute();
			
			int edad = cs2.getInt(1);
			
			System.out.println("El empleado Liam tiene"+ edad);

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


