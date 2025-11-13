public class Proves {
    public static void main(String[] args) throws Exception{
        forcaBruta();
    }
    public static String forcaBruta()throws Exception{
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();

        for (int i = 0; i < charset.length; i++) {
            
            for (int j = 0; j < charset.length && 1 == 1 ; j++) {
                for (int k = 0; k < charset.length; k++) {
                    for (int l = 0; l < charset.length; l++) {
                        for (int m = 0; m < charset.length; m++) {
                            for (int n = 0; n < charset.length; n++) {
                                    String contrasenya = "" + charset[i] + charset[j] + charset[k] + charset[l] + charset[m] + charset[n];
                                    System.out.println(contrasenya);
                            }
                        }
                    }
                }
            }
        }
        return "Contrasenya NO desencriptada";

    }
}
