package ariketak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ariketa2 {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub	
		Scanner scanner=new Scanner(System.in);
		String zutabeak[] = {"ida","izena","dni"};
		int zabalerak[]= {2,10,9};
			
		//instantziak
		
		int []idak = {1,2};
		String [] izena= {"Juan","Lorea"};
		String [] dniak= {"12345678A","98765432P"};
		int longreg = zabalerak[0] + zabalerak[1] + zabalerak[2]; 
		
		   try (RandomAccessFile file = new RandomAccessFile("datuak.dat", "rw")){
			   
			     
			
			String valorCampoForm="";
			String valorCampoForm2="";
			String valorCampoForm3="";
		
			//mover el puntero para que no sobre escriba 
			   file.seek(file.length());
			
			for(int i=0; i<idak.length;i++) {
				 valorCampoForm = String.format("%1$-" + zabalerak[0] + "s",  idak[i]);
				 valorCampoForm2 = String.format("%1$-" + zabalerak[1] + "s",  izena[i]);
				 valorCampoForm3 = String.format("%1$-" + zabalerak[2] + "s",  dniak[i]);
				 
					file.write(valorCampoForm.getBytes("UTF-8"), 0,zabalerak[0]);
					file.write(valorCampoForm2.getBytes("UTF-8"), 0,zabalerak[1]);
					file.write(valorCampoForm3.getBytes("UTF-8"), 0,zabalerak[2]);
					
			}
			
			//bilatu eta irakurri
			
			//bilatu
			//longreg=> la longitud ddel registro  * 2-1 esto ira a la posicion 2 (seria para el programa 1)del registro  porque las posiciones son 0 1 y 2
			file.seek(longreg * (2-1)+ zabalerak[0]);
			
			
			byte[] izenaBilatuta =new byte[zabalerak[1]];
			
			
			//irakurri
			file.read(izenaBilatuta);
			
			//bytak pasatu a String	
			System.out.println(new String (izenaBilatuta,StandardCharsets.UTF_8));
			
			
			//izen baten bilaketa => izen bat emanda 
			
		
			String nom="lorea";
			boolean encontrado= false;
			byte[] izenaAu=new byte[nom.length()];
			int erregitroZenbakia= 1;
			byte[] irakurritakoizena =new byte[zabalerak[1]];
			
			while(!encontrado && (erregitroZenbakia <= file.length()/longreg) ) {
				file.seek((erregitroZenbakia-1)*longreg+zabalerak[0]);
					file.read(irakurritakoizena);
				if(nom.toLowerCase().equals((new String (irakurritakoizena,StandardCharsets.UTF_8).trim().toLowerCase()))) {
					encontrado=true;
					System.out.println(nom + " izena, " + erregitroZenbakia + " erregistro zenbakian dago.");
				}
				
				erregitroZenbakia++;
			}
			
			if(!encontrado) {
				System.out.println("Izen hori ez da fitxategian aurkitu");
			}
	
	
		
			
			
			
	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
				
	}

}
