package com.example.iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {


    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");

        generador.initialize(2048);

        KeyPair parellDeClaus = generador.generateKeyPair();
        return parellDeClaus;
        //PublicKey clauPublica = parellDeClaus.getPublic();
        //PrivateKey clauPrivada = parellDeClaus.getPrivate();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
        Cipher xifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        xifrador.init(Cipher.ENCRYPT_MODE, clauPublica);
        
        byte[] missatgeXifrat = xifrador.doFinal(msg.getBytes());
        return missatgeXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada)throws Exception{
        Cipher desxifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        desxifrador.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] bytesMissatgeDesxifrat = desxifrador.doFinal(msgXifrat);
        String missatge = new String(bytesMissatgeDesxifrat);
        return missatge;
    }
}