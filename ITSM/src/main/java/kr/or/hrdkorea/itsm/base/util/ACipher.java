package kr.or.hrdkorea.itsm.base.util;

public class ACipher
{
  //public static String SEED_KEY = "nkia-nnp";
  public static String SEED_KEY = "hrdkorea-nnp";
  public static final String CIPHER_SEED = "dkduddlghkdlxldj";

  public static String encrypt(String seed, String plain)
    throws Exception
  {
    CipherWorld acw = null;
    byte[] abt = null;
    try
    {
      acw = new CipherAES(CipherUtil.generateAESKey(seed, "SHA-1"));
      abt = acw.getEncryptedByte(plain);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

    return CipherUtil.getBase64EncodeString(abt);
  }

  public static String decrypt(String seed, String msg)
    throws Exception
  {
    CipherWorld acw = null;
    byte[] temp = null;
    String result = null;
    try
    {
      acw = new CipherAES(CipherUtil.generateAESKey(seed, "SHA-1"));
      temp = CipherUtil.getBase64DecodeBuffer(msg);
      result = acw.getDecryptedString(temp);
    }
    catch (Exception e) {
      result = msg;
      e.printStackTrace();
      throw e;
    }

    return result;
  }
}