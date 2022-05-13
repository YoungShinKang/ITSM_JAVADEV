package kr.or.hrdkorea.itsm.base.util;

import kr.or.hrdkorea.itsm.base.util.ACipher;
import java.io.PrintStream;
import org.springframework.stereotype.Component;

@Component("cryptionChange")
public class CryptionChange
{


  public String encryptPassWord(String value) {
	  String passWord = "";
	  try {
		  passWord = ACipher.encrypt("dkduddlghkdlxldj", value);
	  } catch(Exception e ) {
		  e.printStackTrace();
	  }
	  return passWord;
  }

  public String decryptPassWord(String value) {
	  
    String passWord = "";
    try {
    	passWord = ACipher.decrypt("dkduddlghkdlxldj", value);
    } catch(Exception e ) {
		  e.printStackTrace();
	}
    return passWord;
  }
}