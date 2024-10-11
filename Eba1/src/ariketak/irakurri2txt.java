package ariketak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class irakurri2txt {
	public static void main(String[] args) {		
			
		File f1 = new File("fitxa1.txt");
		File f2 = new File("fitxa2.txt");
		File f3 =new File ("fitxa3.txt");
		String lerroa1 ="";
		String lerroa2=""; 
		FileReader fr1= null;
		FileReader fr2 =null;
		BufferedReader bfr =null;
		BufferedReader bfr2= null;
		FileWriter fw= null;
		
		try {
			fr1= new FileReader(f1);
			fr2= new FileReader(f2);
			fw= new FileWriter(f3);
			
			bfr= new BufferedReader(fr1);
			bfr2= new BufferedReader(fr2);
			
			while((lerroa1=bfr.readLine())!=null && (lerroa2=bfr2.readLine())!=null) {
				fw.write(lerroa1 + "\n");
				fw.write(lerroa2 + "\n");
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bfr!=null) bfr.close();
				if(fr1!=null) fr1.close();
				if(bfr2!=null) bfr2.close();
				if(fr2!=null) fr2.close();
				if(fw!=null) fw.close();
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
