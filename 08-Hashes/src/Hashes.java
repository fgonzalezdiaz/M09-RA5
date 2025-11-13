import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

public class Hashes {
    int npass = 0;
    char[] charset = "abcdefABCDEF1234567890!".toCharArray();
public static void main(String[] args) throws Exception {
    String salt = "qpoweiruamslkdfjz";
    String pw = "aabbF!";
    //String pw = "aaaaaa"; //Password contrasenya per proves curtes
    //String pw = "!" // Password contrasenya per proves molt llarga
    //String pw = "ccccc";

    Hashes h = new Hashes();

    String[] aHashes = {
        h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt)
    };

    String pwTrobat = null;
    String[] algorismes = {"SHA-512", "PBKDF2"};

    for (int i = 0; i < aHashes.length; i++) {
        System.out.println("========================================");
        System.out.printf("Algorisme: %s%n", algorismes[i]);
        System.out.printf("Hash: %s%n", aHashes[i]);
        System.out.println("--- Inici de força bruta ---");

        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();

        System.out.printf("Passos provats: %d%n", h.npass); // suposem que npass és un int públic
        System.out.printf("Temps: %s%n", h.getInterval(t1, t2)); // suposem que getInterval formata el temps
        System.out.printf("Contrasenya trobada: %s%n", pwTrobat);
        System.out.println("----------------------------------------\n");
        double segons = (t2 - t1) / 1000.0;
        System.out.println("Temps: " + segons + " segons");
    }
}

    public String getSHA512AmbSalt(String pw, String salt) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String contrasenyaSalt = pw + salt;
        md.update(contrasenyaSalt.getBytes());
        byte[] digest = md.digest();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(digest);
    }
        //HexFormat hex = HexFormat.of();
        //return hex.formatHex(digest);
    public String getPBKDF2AmbSalt(String pw, String salt)throws Exception{
        int iteracions = 10000;
        int keyLength = 512;
        
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iteracions, keyLength);

        SecretKeyFactory sKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        
        byte[] hash = sKeyFactory.generateSecret(spec).getEncoded();
        
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    public String forcaBruta(String alg, String hash, String salt)throws Exception{
        char[] contrasenya1 = new char[6];
        for (int i = 0; i < charset.length; i++) {
            npass++;
            contrasenya1[0] = charset[i];
            if(comprovaPasswd(contrasenya1, 1, hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1, 1);
            for (int j = 0; j < charset.length; j++) {
                npass++;
                contrasenya1[1] = charset[j];
                if(comprovaPasswd(contrasenya1, 2, hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1, 2);
                for (int k = 0; k < charset.length; k++) {
                        npass++;
                        contrasenya1[2] = charset[k];
                        if(comprovaPasswd(contrasenya1, 3, hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1, 3);
                    for (int l = 0; l < charset.length; l++) {
                        npass++;    
                        contrasenya1[3] = charset[l];
                        if(comprovaPasswd(contrasenya1, 4, hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1, 4);
                        for (int m = 0; m < charset.length; m++) {
                            npass++;
                            contrasenya1[4] = charset[m];
                            if(comprovaPasswd(contrasenya1, 5, hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1, 5);
                            for (int n = 0; n < charset.length; n++) {
                                    contrasenya1[5] = charset[n];
                                    if(comprovaPasswd(contrasenya1,6 , hash, alg, salt)) return "Contrasenya trobada " + charToString(contrasenya1 ,6);
                                    npass++;
                            }
                        }
                    }
                }
            }
        }
        return "Contrasenya NO desencriptada";


    }

    public String charToString(char[] contrasenya, int longitud){
        String contr = "";
        for(int i = 0; i < longitud; i++){
            contr += contrasenya[i];
        }
        return contr;
    }

    public boolean comprovaPasswd(char[] passw, int len, String hash, String alg, String salt) throws Exception {
        // crea la String de forma eficiente en una única operación
        String pw = new String(passw, 0, len);
        String pwHash;
        if ("SHA-512".equals(alg)) {
            pwHash = getSHA512AmbSalt(pw, salt);
        } else {
            pwHash = getPBKDF2AmbSalt(pw, salt);
        }
        return pwHash.equals(hash);
    }

  

    public String getInterval(long tl, long t2){
        return "...";

    }
}
