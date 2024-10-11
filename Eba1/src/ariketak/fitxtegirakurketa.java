package ariketak;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.BufferedReader;

public class fitxtegirakurketa {
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("fitxategia:");
	
		String file = scanner.nextLine();
		
		//File file=new File("text.txt");
		
		//BufferedReader buf= new Buffered
		
		File file1 = null;
		FileReader fr = null;
		BufferedReader buff= null;
		file1 = new File(file + ".txt");
		FileWriter fw = null;
	
		String lerroa="";
	
		try {
		   fr = new FileReader(file1);
		   fw = new FileWriter(file1,true );
			
		   int kont = 0;
		   
		   //Modo while
		   
		   /**while(kont != 5) {
			   kont++;
			   int lerroa = fr.read();
			   String lerroa2 = Character.toString((char) lerroa);
			   System.out.println(lerroa2);
		   }*/
		   
		   //Modo char
		   
		   char[] cbuff = new char[5];
		   fr.read(cbuff);
		   System.out.println(cbuff);
		   
		   //lerroz lerro irakurri buffer 		
		     buff=new BufferedReader(fr);
		    
		   while((lerroa=buff.readLine()) !=null) {
			   System.out.println(lerroa);
		   }
		   
		   //idatzi 
		   
		   
		}catch(Exception e) {
			System.out.println("Errorea " + e);
		}finally {
			try {
				if(fr != null) fr.close();
				if(buff !=null)buff.close();
				if(fw!= null)fw.close();
			} catch (Exception e2) {
				System.out.println("error");
			}
			
		}
		
	}



}

