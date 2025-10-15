import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    final static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    final static ArrayList<Character> llistaPermutada = permutaAlfabet(abecedariMajuscules);
    public static void main(String[] args){
        String cadena = "1234 ,,, ... --- PROVA prova ProvA '''''' ";

        System.out.println("XIFRAM LA SEGÜENT CADENA => " + cadena);
        cadena = xifraMonoAlfa(cadena);
        System.out.println(cadena);
        System.out.println("-----------------------------------");

        System.out.println();
       
        System.out.println("DESXIFRAM LA SEGÜENT CADENA => " + cadena);
        cadena = desxifraMonoAlfa(cadena);
        System.out.println("Aquesta es la cadena desxifrada => " + cadena);
    }

private static String xifraMonoAlfa(String cadena){
    return usaMonoalfabet(cadena, true);
}

private static String desxifraMonoAlfa(String cadena){
    return usaMonoalfabet(cadena, false);
}

private static String usaMonoalfabet(String cadena, boolean xifrar){
    String textXifrat = "";
    boolean esMinuscula = false;

    for(int i = 0; i < cadena.length(); i++){
        char c = cadena.charAt(i);
        
        if(Character.isLowerCase(c)) esMinuscula = true;
        c = Character.toUpperCase(c);

        if(trobaIndex(c, true) != -1){
            char d = (xifrar == true) ? llistaPermutada.get(trobaIndex(c, xifrar)) : abecedariMajuscules[trobaIndex(c, xifrar)];
            textXifrat += esMinuscula == true ? Character.toLowerCase(d) : d;
            esMinuscula = false;
        } else {
            textXifrat += c;
        }
    }
    return textXifrat;
}

private static int trobaIndex(char c, boolean xifrar){
    int posicio = 0;
    if(xifrar == true){
        for(char d : abecedariMajuscules){
            if(d == c) return posicio;
            posicio ++;
        }
    } else {
        for(char d : llistaPermutada){
            if(d == c) return posicio;
            posicio ++;
        }
    }
    return -1; //Retorna posicio si el caracter es troba a l'abecedari, si no retorna -1
}

private static ArrayList<Character> permutaAlfabet(char[] abecedari){
    ArrayList<Character> llistaPermutada = new ArrayList<Character>();
    for(char c : abecedari){
        llistaPermutada.add(c);
    }
    Collections.shuffle(llistaPermutada);
    return llistaPermutada;
}
}