package ariketak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class irakurri2txt2 {
	public static void main(String[] args) {		
		
		File f1 = new File("fitxa1.txt");
		File f2 = new File("fitxa2.txt");
		String lerroa1 ="";
		String lerroa2=""; 
		FileReader fr2 =null;
		BufferedReader bfr =null;
		BufferedReader bfr2= null;
		FileWriter fw= null;
		
		try {
			//fr1= new FileReader(f1);
			fr2= new FileReader(f2);
			
			//true para que no me sobre escriba el archivo txt esto se hace en los testu fitxagiak 
			fw= new FileWriter(f1,true);
		
			
			
			bfr2= new BufferedReader(fr2);
			
			while((lerroa2=bfr2.readLine())!=null) {
				fw.write(lerroa2 + "\n");
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bfr2!=null) bfr2.close();
				if(fr2!=null) fr2.close();
				if(fw!=null) fw.close();
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
