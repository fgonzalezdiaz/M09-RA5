public class Rot13 {
    private static char[] abecedariMinuscules = "aàábcçdeèéfghiïíjklmnñoòópqrstuüúvwxyz".toCharArray();
    private static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    public static void main(String[] args){
        String text1 = "ABC";
        String text2 = "XYZ";
        String text3 = "Hola, Mr. calçot";
        String text4 = "Perdó, per tu què és?";
        
        
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
                    textNou = textNou + abecedariMinuscules[cercaIndex(cMinuscula, false)];
                    conegut = true;
                }
                if(c == cMajuscula){
                    textNou = textNou + abecedariMajuscules[cercaIndex(cMajuscula, false)];
                    conegut = true;
                }
            }
            if (conegut == false) textNou += c;
        }
        return textNou;
    }
    public static String desxifraRot13(String text){
        String textNou = "";
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            boolean conegut = false;
            for(int j = 0; j < abecedariMinuscules.length ; j++){
                char cMinuscula = abecedariMinuscules[j];
                char cMajuscula = abecedariMajuscules[j];
                if(c == cMinuscula){
                    textNou = textNou + abecedariMinuscules[cercaIndex(cMinuscula, true)];
                    conegut = true;
                }
                if(c == cMajuscula){
                    textNou = textNou + abecedariMajuscules[cercaIndex(cMajuscula, true)];
                    conegut = true;
                }
            }
            if (conegut == false) textNou += c;
        }
        return textNou;
    }

    public static int cercaIndex(char c, boolean desxifrar){
        int numero = 0;
        for(int i = 0; i < 37; i++){
            if(abecedariMajuscules[i] == c){ 
                numero = i; 
                break;
            }
            if(abecedariMinuscules[i] == c){
                 numero = i; 
                 break;
            }
        }
        if(desxifrar == true){
            numero -= 13;
            while(numero < 0){
                numero += 38;
            }
            return numero;
        }
        if(numero + 13 > 37) return ((numero +13) %38);
        return numero + 13;
        
    }
}

