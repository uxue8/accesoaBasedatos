package ariketak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class leeralreves {

	public static void main(String[] args) {
		
		File f1 = new File("fitxa1.txt");
		File f2 = new File("fitxa2.txt");
		FileReader fr= null;
		BufferedReader bfr= null;
		FileWriter fw=null;
		String lerroa ="";
		String []lerroaAlreves;
		
		try {
	
			fr= new FileReader(f1);
			bfr= new BufferedReader(fr);
			fw = new FileWriter(f2);
			
			while((lerroa=bfr.readLine())!=null) {
				
				char [] karaktereak = lerroa.toCharArray();
				
				for(int i=karaktereak.length-1; i >= 0; i--) {

					fw.write(String.valueOf(karaktereak[i]));
				}
				fw.write("\n");
			}
			
			
			
		}catch(Exception e){
			
			
			
		}finally {
			
				try {
					if(bfr!=null)bfr.close();
					if(fr!=null)fr.close();
					if(fw!=null)fw.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

	}

}
