package me.gaigeshen.pro.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * @author gaigeshen
 */
public class AESTest {

  @Test
  public void testJDK() throws Exception {
    String plainText = "The plain test text";
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    // keyGenerator.init(128);
    keyGenerator.init(new SecureRandom());
    System.out.println("Using provider: " + keyGenerator.getProvider());
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] secretKeyEncoded = secretKey.getEncoded();
    Key keySpec = new SecretKeySpec(secretKeyEncoded, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] result = cipher.doFinal(plainText.getBytes());
    System.out.println("Encoded: " + Base64.getEncoder().encodeToString(result));

    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    result = cipher.doFinal(result);
    System.out.println("Decoded: " + new String(result));

    Assert.assertEquals(plainText, new String(result));
  }


  @Test
  public void testBC() throws Exception {

    Security.addProvider(new BouncyCastleProvider());

    String plainText = "The plain test text";
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
    // keyGenerator.init(128);
    keyGenerator.init(new SecureRandom());
    System.out.println("Using provider: " + keyGenerator.getProvider());
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] secretKeyEncoded = secretKey.getEncoded();
    Key keySpec = new SecretKeySpec(secretKeyEncoded, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] result = cipher.doFinal(plainText.getBytes());
    System.out.println("Encoded: " + Base64.getEncoder().encodeToString(result));

    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    result = cipher.doFinal(result);
    System.out.println("Decoded: " + new String(result));

    Assert.assertEquals(plainText, new String(result));
  }

}
