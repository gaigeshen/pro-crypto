package me.gaigeshen.pro.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * @author gaigeshen
 */
public class PBETest {

  @Test
  public void testJDK() throws Exception {

    String plainText = "The plain test text";

    String password = "my password";

    SecureRandom secureRandom = new SecureRandom();
    byte[] seed = secureRandom.generateSeed(8);

    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
    System.out.println("Using provider: " + secretKeyFactory.getProvider());
    Key secretKey = secretKeyFactory.generateSecret(keySpec);

    PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(seed, 100);
    Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
    byte[] result = cipher.doFinal(plainText.getBytes());
    System.out.println("Encoded: " + Base64.getEncoder().encodeToString(result));

    cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
    result = cipher.doFinal(result);
    System.out.println("Decoded: " + new String(result));

    Assert.assertEquals(plainText, new String(result));
  }

  @Test
  public void testBC() throws Exception {
    Security.addProvider(new BouncyCastleProvider());

    String plainText = "The plain test text";

    String password = "my password";

    SecureRandom secureRandom = new SecureRandom();
    byte[] seed = secureRandom.generateSeed(8);

    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES", "BC");
    System.out.println("Using provider: " + secretKeyFactory.getProvider());
    Key secretKey = secretKeyFactory.generateSecret(keySpec);

    PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(seed, 100);
    Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
    byte[] result = cipher.doFinal(plainText.getBytes());
    System.out.println("Encoded: " + Base64.getEncoder().encodeToString(result));

    cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
    result = cipher.doFinal(result);
    System.out.println("Decoded: " + new String(result));

    Assert.assertEquals(plainText, new String(result));
  }
}
