package ariketak;

import java.io.File;

import java.io.FileInputStream;

public class pngirakurribitarrak {

	public static void main(String[] args) {

// TODO Auto-generated method stub

		FileInputStream fis = null;

		try {

			fis = new FileInputStream("flor2.png");

			byte[] a = new byte[24];
			byte[] png = { (byte) 137, 80, 78, 71, 13, 10, 26, 10 };
			byte[] azalera= new byte[4];
			byte [] altuera = new byte[4];

			int irakurri = 0;

			irakurri = fis.read(a);

// System.out.println(irakurri);

//System.out.println(a[0]);

			for (int i = 0; i < a.length; i++) {

				System.out.print(Byte.toUnsignedInt(a[i]) );
 
			}

//saber si es png
			// int kont = 0;
			boolean encontrado = false;
			for (int i = 0; i < png.length; i++) {
				//kont++;
				if (png[i] == a[i]) {
					//if (kont == 7) {
						encontrado = true;
					//}
				}

			}
			if (encontrado) {
				System.out.println("es un png");
			}
			else {
				System.out.println("no es un png");
			}

//sacar el widht y height
			
		
//for(int i=16; i<19; i++) {

// System.out.print("widht:"+Byte.toUnsignedInt(a[i]));
// }

		} catch (Exception e) {

			System.out.println("Errorea " + e);

		} finally {

			try {

				if (fis != null)
					fis.close();

			} catch (Exception e) {

				System.out.println("error");

			}

		}

	}

}
