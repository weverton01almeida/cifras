package vigenere;

public class GerarAlfabeto {
//Cria um array de characteres com 96 posições
public char[] gerarAlfabeto() {
char[] abc = new char[96];

for (int i = 32; i <= 127; i++) {
abc[i - 32] = (char) i;
}
return abc;
}
}
