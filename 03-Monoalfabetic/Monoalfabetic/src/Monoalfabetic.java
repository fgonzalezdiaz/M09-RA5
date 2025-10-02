import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    final static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    final static ArrayList<Character> llistaPermutada = permutaAlfabet(abecedariMajuscules);
    public static void main(String[] args){
        String cadena = "Hola Dias";

        System.out.println("XIFRAM LA SEGÜENT CADENA => " + cadena);
        cadena = xifraMonoAlfa(cadena, llistaPermutada);
        System.out.println(cadena);
        System.out.println("-----------------------------------");

        System.out.println();
       
        System.out.println("DESXIFRAM LA SEGÜENT CADENA => " + cadena);
        cadena = desxifraMonoAlfa(cadena, llistaPermutada);
        System.out.println("Aquesta es la cadena desxifrada => " + cadena);
    }



private static ArrayList<Character> permutaAlfabet(char[] abecedari){
    ArrayList<Character> llistaPermutada = new ArrayList<Character>();
    for(char c : abecedari){
        llistaPermutada.add(c);
    }
    Collections.shuffle(llistaPermutada);
    return llistaPermutada;
}

private static String xifraMonoAlfa(String cadena, ArrayList<Character> llistaPermutada){
    String textXifrat = "";
    for(int i = 0; i < cadena.length(); i++){
        char c = cadena.charAt(i);
        if(trobaIndex(c) != -1){
            char d = Character.toUpperCase(llistaPermutada.get(trobaIndex(c)));
            textXifrat += d;
        } else {
            textXifrat += c;
        }
    }
    return textXifrat;
}

private static String desxifraMonoAlfa(String cadena, ArrayList<Character> llistaPermutada){
    String textXifrat = "";
    for(int i = 0; i < cadena.length(); i++){
        char c = cadena.charAt(i);
        if(trobaIndex(c) != -1){
            char d = Character.toUpperCase(abecedariMajuscules[trobaIndex(c, llistaPermutada)]);
            textXifrat += d;  // CERCA DINS ABECEDARI MAJUSCULES LA POSICIO QUE COINCIDEIX AMB LLISTA PERMUTADA EL CARACTER XIFRAT
        } else {
            textXifrat += c;
        }
    }
    return textXifrat;
}

private static int trobaIndex(char c){
    int posicio = 0;
    for(char d : abecedariMajuscules){
        if(d == c) return posicio;
        posicio ++;
    }
    return -1; //Retorna posicio si el caracter es troba a l'abecedari, si no retorna -1
}

private static int trobaIndex(char c, ArrayList<Character> llistaPermutada){
    int posicio = 0;
    for(char d : llistaPermutada){
        if(d == c) return posicio;
        posicio ++;
    }
    return -1; //Retorna posicio si el caracter es troba a l'abecedari, si no retorna -1
}

}