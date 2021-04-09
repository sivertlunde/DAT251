package no.hvl.dat251.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

@Component
public class EncryptDecrypt {

    //Key secretKey;
    SecretKeySpec keySpec;
    @Value("${FIREBASE_SECRET}")
    private String secret;

    @PostConstruct
    public void initialize() {
        try {
            byte[] salt = {
                    (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                    (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            };

            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt, 65536, 256);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = f.generateSecret(spec).getEncoded();
            keySpec = new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }


    public void encrypt(String filename) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] text = fileToString(filename).getBytes("UTF-8");
            byte[] encryptedText = cipher.doFinal(text);
            stringToFile(Base64.getEncoder().encodeToString(encryptedText), filename);

        } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public void decrypt(String filename) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            String encrypted = fileToString(filename);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            stringToFile(new String(decrypted),filename);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }

    private void stringToFile(String content, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename,false));
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fileToString(String filename) {
        String content = "";
        try {
            Scanner sc = new Scanner(new File(filename)).useDelimiter("\\Z");
            content = sc.next();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}
