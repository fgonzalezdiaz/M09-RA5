import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Polialfabetic {
    private static final char[] alfabet = "AÁÀÄBCÇDEÈÉFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static final Long clauSecreta = Long.MAX_VALUE;
    private static Random r;
    
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("Xifratge:\n -·····-- ");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    private static String xifraPoliAlfa(String msg){
        return utilitzaPoliAlfa(msg, true);
    }

    private static String desxifraPoliAlfa(String msg){
        return utilitzaPoliAlfa(msg, false);
    }

    public static String utilitzaPoliAlfa(String msg, boolean xifrar){
        char[] alfabetXifrat;
        String missatgeXifrat = "";

        for(int i = 0; i < msg.length(); i++){
            boolean esMinuscula = false;
            char c = msg.charAt(i);

            // Convertir a mayúscula temporalmente
            if(Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
                esMinuscula = true;
            }

            int indexDelAbecedari = -1;
            
            // Permutar el alfabeto para este carácter
            alfabetXifrat = permutaAlfabet();
            
            // Buscar el índice del carácter según se esté cifrando o descifrando
            for(int j = 0; j < alfabet.length; j++){
                if(xifrar){
                    if(c == alfabet[j]) indexDelAbecedari = j;
                } else {
                    if(c == alfabetXifrat[j]) indexDelAbecedari = j;
                }
            }

            // Si no está en el alfabeto, dejar el carácter tal cual
            if(indexDelAbecedari == -1){
                missatgeXifrat += c;
            } else {
                // Sustituir el carácter según el alfabeto correspondiente
                c = xifrar ? alfabetXifrat[indexDelAbecedari] : alfabet[indexDelAbecedari];

                // Volver a minúscula si era minúscula
                if(esMinuscula) c = Character.toLowerCase(c);

                missatgeXifrat += c;
            }
        }

        return missatgeXifrat;
    }

    private static char[] permutaAlfabet() {
        ArrayList<Character> alfabetModificat = new ArrayList<>();

        // Convertir el array a ArrayList para poder usar shuffle
        for(char i : alfabet){
            alfabetModificat.add(i);
        }

        // Mezclar la lista con Random r (semilla fija para reproducibilidad)
        Collections.shuffle(alfabetModificat, r);

        // Convertir la lista de nuevo a un array de char
        char[] returnAlfabet = new char[alfabet.length];
        for(int i = 0; i < alfabetModificat.size(); i++){
            returnAlfabet[i] = alfabetModificat.get(i);
        }
        return returnAlfabet;
    }

    // Inicializar el generador de números aleatorios con la clave secreta
    private static void initRandom(Long clauSecreta){
        r = new Random(clauSecreta);
    }
}
