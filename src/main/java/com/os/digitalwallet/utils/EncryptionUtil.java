package com.os.digitalwallet.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "gH4aB9LpXq8Wz7Kd".getBytes();

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedVal = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedVal = cipher.doFinal(decodedValue);
        return new String(decryptedVal);
    }

    private static SecretKeySpec generateKey() {
        return new SecretKeySpec(keyValue, ALGORITHM);
    }
}
