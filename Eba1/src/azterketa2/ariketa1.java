package azterketa2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class ariketa1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			 conn = DriverManager.getConnection("jdbc:ucanaccess://SchoolTests.mdb");
			 Statement s = conn.createStatement();
		
			String sql= "SELECT StudentId, StudentName FROM Student ";
			
			ResultSet resSet = s.executeQuery(sql);
			
			while(resSet.next()) {
				System.out.println( " id:"+resSet.getInt(1) + " izena:" +resSet.getString(2));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(conn!=null) { try {
				conn.close();
				System.out.println("bukatuta");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			}
		}
	}

}
