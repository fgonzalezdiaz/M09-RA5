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
    String pw = "aaabF!";

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

        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        for (int i = 0; i < charset.length; i++) {
            for (int j = 0; j < charset.length; j++) {
                for (int k = 0; k < charset.length; k++) {
                    for (int l = 0; l < charset.length; l++) {
                        for (int m = 0; m < charset.length; m++) {
                            for (int n = 0; n < charset.length; n++) {
                                    String contrasenya = charset[i] + charset[j] + charset[k] + charset[l] + charset[m] + charset[n] + "";
                                    if()
                            }
                        }
                    }
                }
            }
        }
        return "Contrasenya NO desencriptada";

    }

    public boolean comprovaPasswd(String pw, String hash, String alg, String salt)throws Exception{
        String pwHash = alg.equals("SHA-512") ? getSHA512AmbSalt(pw, salt) : getPBKDF2AmbSalt(pw, salt);
        return pwHash.equals(hash);
    }

  

    public String getInterval(long tl, long t2){
        return "...";

    }
}
