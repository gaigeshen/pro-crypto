package me.gaigeshen.pro.crypto;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.Security;

/**
 * @author gaigeshen
 */
public class MDTest {

  private static final String PLAIN_TEXT = "My plain text for test";

  @Test
  public void testMD5Jdk() throws Exception {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    byte[] result = messageDigest.digest(PLAIN_TEXT.getBytes());
    System.out.println("MD5 Result(JDK): " + Hex.encodeHexString(result));
  }

  // MD4 MessageDigest not available under JDK default
  @Test
  public void testMD4Jdk() throws Exception {
    Security.addProvider(new BouncyCastleProvider());// For MD4

    MessageDigest messageDigest = MessageDigest.getInstance("MD4");
    byte[] result = messageDigest.digest(PLAIN_TEXT.getBytes());
    System.out.println("MD4 Result(JDK): " + Hex.encodeHexString(result));
  }

  @Test
  public void testMD2Jdk() throws Exception {
    MessageDigest messageDigest = MessageDigest.getInstance("MD2");
    byte[] result = messageDigest.digest(PLAIN_TEXT.getBytes());
    System.out.println("MD2 Result(JDK): " + Hex.encodeHexString(result));
  }

  @Test
  public void testMD4BC() {
    byte[] input = PLAIN_TEXT.getBytes();
    Digest digest = new MD4Digest();
    digest.update(input, 0, input.length);

    byte[] out = new byte[digest.getDigestSize()];
    digest.doFinal(out, 0);
    System.out.println("MD4 Result(BC): " + org.bouncycastle.util.encoders.Hex.toHexString(out));
  }
}
