package vigenere;

public class Main {
public static void main(String[] args) {
 CifraDeVigenere cv = new CifraDeVigenere("Ola Mundo", "blz");//7dT^
 System.out.println("-------------------Cifrado------------------------");
 cv.cifrar();
 System.out.println("-------------------Decifrado----------------------");
 cv.decifrar();
 }
}
