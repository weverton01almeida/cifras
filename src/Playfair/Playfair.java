package Playfair;

public class Playfair {
	static String alfabeto = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	public String chave, claro, cifrado;
	public char matriz[][] = new char[5][5];

	public Playfair() {
		this.chave = "";
		this.claro = "";
		this.cifrado = "";

	}

	public String arreglar(String texto) {
		texto.toUpperCase();
		texto.replace('I', 'J'); // Quito las Js
		texto.replace('N', 'Ñ'); // Quito las Ñs
		String arreglado = "";
		String texto2 = "";
		for (int x = 0; x < texto.length(); x++) { // Elimino los espacios
			if (texto.charAt(x) != ' ')
				texto2 += texto.charAt(x);
		}
		for (int i = 0; i < texto2.length(); i++) {
			if ((i + 1) != texto2.length()) {
				if (texto2.charAt(i) != texto2.charAt(i + 1)) {
					arreglado = arreglado + texto2.charAt(i) + texto2.charAt(i + 1); // Compruebo
																						// que
																						// no
																						// haya
																						// digramas
																						// igules
					i++;
				} else {
					arreglado = arreglado + texto2.charAt(i) + 'Z'; // Si los
																	// hay meto
																	// x entre
																	// medio
				}
			} else
				arreglado = arreglado + texto2.charAt(i) + 'Z'; // Si la
																// longitud del
																// texto es
																// impar incluyo
																// una X

		}
		if (arreglado.length() % 2 == 1)
			arreglado += 'Z';
		return arreglado;
	}

	public void generarMatriz(String clave) {
		// Aquí genero la matriz de playfair
		clave.toUpperCase();
		String texto = "";
		for (int i = 0; i < clave.length(); i++) {
			if (texto.indexOf(clave.charAt(i)) == -1 && clave.charAt(i) != ' ')
				texto += clave.charAt(i);
		}
		for (int i = 0; i < alfabeto.length(); i++) {
			if (texto.indexOf(alfabeto.charAt(i)) == -1)
				texto += alfabeto.charAt(i);
		}
		for (int j = 0, k = 0; j < 5; j++)
			for (int i = 0; i < 5; i++) {
				System.out.print(texto.charAt(k));
				matriz[j][i] = texto.charAt(k);
				k++;
			}
		this.chave = texto;
	}

	public String descifrarDigrama(char a, char b) {
		String digrama = "";
		Par par1 = new Par();
		Par par2 = new Par();

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if (matriz[j][i] == a) {
					par1.i = i;
					par1.j = j;
				}
				if (matriz[j][i] == b) {
					par2.i = i;
					par2.j = j;
				}
				System.out.print(matriz[j][i]);
			}
			System.out.println("\n");
		}
		// CASO J
		if (par1.j == par2.j) {
			System.out.println("Entro fil");
			int J, IA, IB;
			J = par1.getJ();
			IA = ((par1.getI() - 1));
			IB = ((par2.getI() - 1));
			if (IA < 0)
				IA += 5;
			if (IB < 0)
				IB += 5;
			a = matriz[J][IA];
			b = matriz[J][IB];
		}
		// CASO I
		else if (par1.i == par2.i) {
			System.out.println("entro col");
			int I, JA, JB;
			JA = ((par1.getJ() - 1));
			JB = ((par2.getJ() - 1));
			if (JA < 0)
				JA += 5;
			if (JB < 0)
				JB += 5;
			I = par1.getI();
			a = matriz[JA][I];
			b = matriz[JB][I];
			System.out.println(a + "," + b);
		}

		else {
			a = matriz[par1.j][par2.i];
			b = matriz[par2.j][par1.i];
		}
		digrama += a;
		digrama += b;

		return digrama;
	}

	public String cifrarDigrama(char a, char b) {
		String digrama = "";
		Par par1 = new Par();
		Par par2 = new Par();

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if (matriz[j][i] == a) {
					par1.i = i;
					par1.j = j;
					System.out.println("ENCUENTRO A" + j + i);
				}
				if (matriz[j][i] == b) {
					par2.i = i;
					par2.j = j;
					System.out.println("ENCUENTRO B" + j + i);
				}
			}
		}
		// CASO J
		if (par1.j == par2.j) {
			System.out.println("Entro fil");
			int J, IA, IB;
			J = par1.getJ();
			IA = ((par1.getI() + 1) % 5);
			IB = ((par2.getI() + 1) % 5);
			a = matriz[J][IA];
			b = matriz[J][IB];
		}
		// CASO I
		else if (par1.i == par2.i) {
			System.out.println("entro col");
			int I, JA, JB;
			JA = ((par1.getJ() + 1) % 5);
			JB = ((par2.getJ() + 1) % 5);
			I = par1.getI();
			a = matriz[JA][I];
			b = matriz[JB][I];
			System.out.println(a + "," + b);
		} else {
			b = matriz[par2.j][par1.i];
			a = matriz[par1.j][par2.i];
		}
		digrama += a;
		digrama += b;

		return digrama;
	}

	public String cifrar(String claro, String clave) {
		claro = arreglar(claro);
		generarMatriz(clave);
		cifrado = "";
		for (int i = 0; i < claro.length(); i = i + 2)
			cifrado += cifrarDigrama(claro.charAt(i), claro.charAt(i + 1));

		return cifrado;
	}

	public String descifrar(String cifrado, String clave) {
		generarMatriz(clave);
		claro = "";
		for (int i = 0; i < cifrado.length(); i = i + 2)
			claro += descifrarDigrama(cifrado.charAt(i), cifrado.charAt(i + 1));
		System.out.println("texto en claro: " + claro);
		return claro;

	}

}
