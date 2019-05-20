package me.gaigeshen.pro.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

/**
 * @author gaigeshen
 */
public class Base64Test {

  @Test
  public void testJdk() {
    String plainText = "Test plain text";

    Base64.Encoder encoder = Base64.getEncoder();
    String encoded = encoder.encodeToString(plainText.getBytes());
    System.out.println("Encoded: " + encoded);

    Base64.Decoder decoder = Base64.getDecoder();
    byte[] decoded = decoder.decode(encoded.getBytes());
    System.out.println("Decoded: " + new String(decoded));

    Assert.assertEquals(plainText, new String(decoded));
  }

  @Test
  public void testCommonsCodec() {
    String plainText = "Test plain text";
    String encoded = org.apache.commons.codec.binary.Base64.encodeBase64String(plainText.getBytes());
    System.out.println("Encoded: " + encoded);

    byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded);
    System.out.println("Decoded: " + new String(decoded));

    Assert.assertEquals(plainText, new String(decoded));
  }

  @Test
  public void testBouncyCastle() {
    String plainText = "Test plain text";
    byte[] encoded = org.bouncycastle.util.encoders.Base64.encode(plainText.getBytes());
    System.out.println("Encoded: " + new String(encoded));

    byte[] decoded = org.bouncycastle.util.encoders.Base64.decode(encoded);
    System.out.println("Decoded: " + new String(decoded));

    Assert.assertEquals(plainText, new String(decoded));
  }
}
