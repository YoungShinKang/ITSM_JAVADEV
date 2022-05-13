package kr.or.hrdkorea.itsm.base.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class CipherAES
  implements CipherWorld
{
  private final String algorithm = "AES";
  private final String mod = "ECB";
  private final String padding = "PKCS5Padding";
  private byte[] key = null;

  public CipherAES(byte[] pass)
  {
    this.key = pass;
  }

  public byte[] getEncryptedByte(String message)
    throws Exception
  {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128);

    SecretKeySpec skeySpec = new SecretKeySpec(this.key, "AES");

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(1, skeySpec);

    byte[] encrypted = cipher.doFinal(message.getBytes());

    return encrypted;
  }

  public String getEncryptedHexString(String message)
    throws Exception
  {
    return CipherUtil.asHex(getEncryptedByte(message));
  }

  public String getDecryptedString(byte[] message)
    throws Exception
  {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128);

    SecretKeySpec skeySpec = new SecretKeySpec(this.key, "AES");

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(2, skeySpec);
    byte[] original = cipher.doFinal(message);

    return new String(original);
  }

  public String getDecryptedString(String message)
    throws Exception
  {
    return getDecryptedString(message.getBytes());
  }
}