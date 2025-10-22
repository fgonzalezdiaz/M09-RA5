import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.*;
import java.security.*;
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte [MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    public static void main (String[] args) {
        rellenaBytes();
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andres como esta tu cunado",
        "Agora illa Otto"}; // He cambiat els caracters especials, hi ha una solucio passant-ho a base64
                            // pero no entenc realment el funcionament del metode 
                            // per solucionar aquest problema, donçs fare aquest petit canvi.

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte [] bXifrats = null;
            String desxifrat = "";
            try {

                bXifrats = xifraAES (msg, CLAU) ;
                desxifrat = desxifraAES (bXifrats, CLAU) ;
            } catch (Exception e) {
                System.err.println ("Error de xifrat: "
                + e.getLocalizedMessage () ) ;
            }
            System. out.println ("------------------------");
            System. out.println ("Msg: " + msg) ;
            System.out.println ("Enc: " + new String (bXifrats) );
            System. out.println ("DEC: " + desxifrat);
        }
    }
    
    public static byte[] xifraAES (String msg, String clau) throws Exception {
        //Obtenir els bytes de l'String
        byte[] bytesDeString = msg.getBytes();
        //Genera IvParameterSpec
        IvParameterSpec ivSpec = rellenaBytes();        
        //Genera hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        md.update(clau.getBytes("UTF-8"));
        byte[] digest = md.digest();
        SecretKeySpec secretKey = new SecretKeySpec(digest, ALGORISME_XIFRAT);
        // Encrypt.
        Cipher msgCipher = Cipher.getInstance(FORMAT_AES);
        msgCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] missatgeXifrat = msgCipher.doFinal(bytesDeString);
        // Combinar IV i part xifrada.
        byte[] msgXifrarIV = new byte[missatgeXifrat.length + iv.length];
        System.arraycopy(iv, 0, msgXifrarIV, 0, iv.length);
        System.arraycopy(missatgeXifrat, 0, msgXifrarIV, iv.length, missatgeXifrat.length);
        //return iv+msgxifrat
        return msgXifrarIV;
    }
    private static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreure l'IV.
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada.
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);

        // Fer hash de la clau (mateix procés que en xifraAES).
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        md.update(clau.getBytes("UTF-8"));
        byte[] digest = md.digest();
        SecretKeySpec secretKey = new SecretKeySpec(digest, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher msgCipher = Cipher.getInstance(FORMAT_AES);
        msgCipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] missatgeDesxifrat = msgCipher.doFinal(msgXifrat);

        // Retornar com a String.
        return new String(missatgeDesxifrat, "UTF-8");
    }


    private static IvParameterSpec rellenaBytes(){
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(iv);
        return new IvParameterSpec(iv);
    } 
}

/*
Cipher cipher = Cipher.getInstance("ALGORITMO/MODO/RELLENO");
cipher.init(MODO, CLAVE, OPCIONAL: IV);
byte[] resultado = cipher.doFinal(datos);
*/