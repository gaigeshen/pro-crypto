package me.gaigeshen.pro.crypto;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author gaigeshen
 */
public class HMACTest {
  private static final String PLAIN_TEXT = "My plain text for test";
  @Test
  public void testHMACMD5Jdk() throws Exception {
    // KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
    // SecretKey secretKey = keyGenerator.generateKey();
    // byte[] secretKeyEncoded = secretKey.getEncoded();
    byte[] secretKeyEncoded = Hex.decodeHex("aaaaaaabbb".toCharArray());
    SecretKey restoreSecretKey = new SecretKeySpec(secretKeyEncoded, "HmacMD5");
    Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
    mac.init(restoreSecretKey);
    byte[] result = mac.doFinal(PLAIN_TEXT.getBytes());
    // 3436ad4f45b0dc56da215e1141871fc8
    System.out.println("HmacMD5 Result(JDK): " + Hex.encodeHexString(result));
  }

  @Test
  public void testHMACMD5BC() {
    HMac hMac = new HMac(new MD5Digest());
    hMac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaabbb")));
    hMac.update(PLAIN_TEXT.getBytes(), 0, PLAIN_TEXT.getBytes().length);
    byte[] hmacBytes = new byte[hMac.getMacSize()];
    hMac.doFinal(hmacBytes, 0);
    // 3436ad4f45b0dc56da215e1141871fc8
    System.out.println("HmacMD5 Result(BC): " + org.bouncycastle.util.encoders.Hex.toHexString(hmacBytes));
  }
}
