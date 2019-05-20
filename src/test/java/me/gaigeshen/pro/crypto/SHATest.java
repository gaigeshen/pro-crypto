package me.gaigeshen.pro.crypto;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * @author gaigeshen
 */
public class SHATest {
  private static final String PLAIN_TEXT = "My plain text for test";
  @Test
  public void testSHA1JDK() throws Exception {
    MessageDigest messageDigest = MessageDigest.getInstance("SHA"); // SHA-256 SHA-384 SHA-512
    messageDigest.update(PLAIN_TEXT.getBytes());
    byte[] result = messageDigest.digest();
    // 0e024c0a898e481d8ce43becacbb9323ba2d24ec
    System.out.println("SHA1 Result(JDK): " + Hex.encodeHexString(result));
  }

  @Test
  public void testSHA1BC() {
    byte[] input = PLAIN_TEXT.getBytes();
    Digest digest = new SHA1Digest();
    digest.update(input, 0, input.length);

    byte[] out = new byte[digest.getDigestSize()];
    digest.doFinal(out, 0);
    // 0e024c0a898e481d8ce43becacbb9323ba2d24ec
    System.out.println("SHA1 Result(BC): " + org.bouncycastle.util.encoders.Hex.toHexString(out));
  }
}
