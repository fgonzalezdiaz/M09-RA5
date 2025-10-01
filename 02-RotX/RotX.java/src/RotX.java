public class RotX {
    private static char[] abecedariMinuscules = "aàábcçdeèéfghiïíjklmnñoòópqrstuüúvwxyz".toCharArray();
    private static char[] abecedariMajuscules = "AÀÁBCÇDEÈÉFGHIÏÍJKLMNÑOÒÓPQRSTUÜÚVWXYZ".toCharArray();

    public static void main(String[] args){
        String[] llistaText = new String[]{"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] llistaXifrada = new String[llistaText.length];
        int desplaçament = 50;
        int desplaçamentInicial = desplaçament;
        System.out.println("Xifrat");
        System.out.println("------");
        for(int i = 0; i < llistaText.length ; i++){
            String xifrat = xifraRotX(llistaText[i], desplaçament);
            System.out.printf("(%d)   %-30s => %s%n", desplaçament, llistaText[i], xifrat);
            llistaXifrada[i] = xifrat;
            desplaçament += 2;
        }
        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        desplaçament = desplaçamentInicial;
        
        for(int i = 0; i < llistaXifrada.length ; i++){
            String desxifrat = desxifraRotX(llistaXifrada[i], desplaçament);
            System.out.printf("(%d)   %-30s => %s%n", desplaçament, llistaXifrada[i], desxifrat);
            desplaçament += 2;
        }

        System.out.println();
        System.out.println("Missatge xifrat => " + llistaXifrada[llistaXifrada.length - 1]);
        System.out.println("---------------------------------");
        String[] llistaDesxifrada = forcaBrutaRotX( llistaXifrada[llistaXifrada.length - 1]);
        for(int i = 0; i < llistaDesxifrada.length; i++){
            System.out.println(llistaDesxifrada[i]);
        }
    }

    public static String xifraRotX(String cadena, int desplacament){
        return RotXUno(cadena, desplacament, true);
    }

    public static String desxifraRotX(String cadena, int desplacament){
        return RotXUno(cadena, desplacament, false);
    }

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

    public static String[] forcaBrutaRotX(String cadenaXifrada){ 
        String[] textosDesxifrats = new String[abecedariMajuscules.length];
        for(int i = 0 ; i < abecedariMajuscules.length; i++){
            textosDesxifrats[i] = "("+ i + ") " + desxifraRotX(cadenaXifrada, i);
        }
        return textosDesxifrats;
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

        if(xifrar){
            numero = (numero + desplacament) % abecedariMajuscules.length;
        } else {
            numero = (numero - desplacament) % abecedariMajuscules.length;
            if (numero < 0) {
                numero += abecedariMajuscules.length;
            }
        }
        return numero;
    }
}
