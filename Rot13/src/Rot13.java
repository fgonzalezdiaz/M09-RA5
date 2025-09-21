public class Rot13 {
    private static char[] abecedariMinuscules = "aàábcçdeèéfghiïíjklmnñoòópqrstuüúvwxyz".toCharArray();
    private static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    public static void main(String[] args){
        String[] llistaText = new String[4];
        String text1 = "ABC"; llistaText[0] = text1;
        String text2 = "XYZ"; llistaText[1] = text2;
        String text3 = "Hola, Mr. calçot"; llistaText[2] = text3;
        String text4 = "Perdó, per tu què és?"; llistaText[3] = text4;

        System.out.println("Xifrat");
        System.out.println("------");
        for(int i = 0; i < 4 ; i++){
            System.out.println(llistaText[i] + "              =>" + xifraRot13(llistaText[i]));
        }
        
        System.out.println("---------");
        System.out.println("Desxifrat");
        System.out.println("---------");
        for(int i = 0; i < 4 ; i++){
            System.out.println(xifraRot13(llistaText[i]) + "              =>" + desxifraRot13(xifraRot13(llistaText[i])));
        }
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
        for(int i = 0; i < abecedariMinuscules.length; i++){
            if(abecedariMajuscules[i] == c || abecedariMinuscules[i] == c){ 
                numero = i; 
                break;
            }
        }
        if(desxifrar){
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

