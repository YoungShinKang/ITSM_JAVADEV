package kr.or.hrdkorea.itsm.base.util;

public abstract interface CipherWorld
{
  public abstract byte[] getEncryptedByte(String paramString)
    throws Exception;

  public abstract String getEncryptedHexString(String paramString)
    throws Exception;

  public abstract String getDecryptedString(byte[] paramArrayOfByte)
    throws Exception;

  public abstract String getDecryptedString(String paramString)
    throws Exception;
}