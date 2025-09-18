public class Rot13 {
    private static char[] abecedariMinuscules = "aàábcçdeèéfghiïíjklmnñoòópqrstuüúvwxyz".toCharArray();
    private static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    public static void main(String[] args){
        String text = "ABC";
        text = xifraRot13(text);
        System.out.println(text);
        
    }
    public static String xifraRot13(String text){
        String textNou = "";
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            boolean conegut = false;
            for(int j = 0; j < abecedariMinuscules.length ; j++){
                char cMinuscula = abecedariMinuscules[j];
                char cMajuscula = abecedariMajuscules[j];
                if(c == cMinuscula){
                    textNou = textNou + abecedariMinuscules[cercaIndex(cMinuscula)];
                    conegut = true;
                }
                if(c == cMajuscula){
                    textNou = textNou + abecedariMajuscules[cercaIndex(cMajuscula)];
                    conegut = true;
                }
            }
            if (conegut == false) textNou += c;
        }
        return textNou;
    }

    public static int cercaIndex(char c){
        int numero = 0;
        for(int i = 0; i < 38; i++){
            if(abecedariMajuscules[i] == c){ 
                numero = i; 
                break;
            }
            if(abecedariMinuscules[i] == c){
                 numero = i; 
                 break;
            }
        }
        if(numero + 13 > 38) return ((numero +13) %38);
        return numero + 13;
        
    }
}

