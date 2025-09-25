public class RotX {
    private static char[] abecedariMinuscules = "aàábcçdeèéfghiïíjklmnñoòópqrstuüúvwxyz".toCharArray();
    private static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();
    public static void main(String[] args){
        String[] llistaText = new String[]{"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és"};
        String[] llistaXifrada = new String[llistaText.length];
        System.out.println("Xifrat");
        System.out.println("------");
        for(int i = 0; i < llistaText.length ; i++){
            System.out.println(xifraRotX(llistaText[i], 4));
            llistaXifrada[i] += xifraRotX(llistaText[i], 4);
        }
        
        System.out.println("---------");
        System.out.println("Desxifrat");
        System.out.println("---------");
        for(int i = 0; i < llistaText.length ; i++){
            System.out.println(desxifraRotX(llistaText[i], 4));
        }
    }
    public static String xifraRotX(String cadena, int desplacament){
        return RotXUno(cadena, desplacament, true);
    }
    public static String desxifraRotX(String cadena, int desplacament){
        return RotXUno(cadena, desplacament, false);
    }

    /*public static String forcaBrutaRotX(String cadenaXifrada){
        for(int i)
    }*/

    public static String RotXUno(String cadena, int desplacament, boolean xifrar){
        String textARetornar = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            if(Character.isUpperCase(c)){
                textARetornar += abecedariMajuscules[cercaIndex(c, xifrar, desplacament)];
            } else if (Character.isLowerCase(c)){
                textARetornar += abecedariMinuscules[cercaIndex(c, xifrar, desplacament)];
            } else {
                textARetornar += c;
            }
        }

        return textARetornar;
    }

    public static int cercaIndex(char c, boolean xifrar, int desplacament){
        int numero = 0;
        for(int i = 0; i < abecedariMajuscules.length; i++){
            if(abecedariMajuscules[i] == c){
                numero = i; break;
            }
            if(abecedariMinuscules[i] == c){
                numero = i; break;
            }
        }

        if(xifrar == true){
            numero += desplacament;
            while (numero > abecedariMajuscules.length){
                numero %= 38;
            }
            return numero;
        } else {
            numero -= desplacament;
            while (numero < 0){
                numero += 38;
            }
            return numero;
        }
    }
}
