package kr.or.hrdkorea.itsm.base.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringUtil
{
  public static String lpad(String str, int len, String addStr)
  {
    String result = str;
    int templen = len - result.length();

    for (int i = 0; i < templen; i++) {
      result = addStr + result;
    }
    return result;
  }

  public static boolean isNull(Object obj)
  {
    boolean result = false;
    if (obj == null) {
      result = true;
    }
    return result;
  }

  public static String checkSQLParam(String returnValue) {
    returnValue = returnValue.replace("--", "");
    return returnValue;
  }

  public static String replaceAll(String source, String ex, String rep)
  {
    if ((source == null) || 
      (source.trim().equals("")) || 
      (source.trim().equals("null")))
    {
      return "";
    }
    return source.replaceAll(ex, rep);
  }

  public static String[] explode(String delim, String buf)
  {
    StringTokenizer token = new StringTokenizer(buf, delim);
    ArrayList arrList = new ArrayList();
    while (token.hasMoreTokens()) {
      arrList.add(token.nextToken());
    }
    String[] retval = new String[arrList.size()];
    for (int i = 0; i < retval.length; i++) {
      retval[i] = ((String)arrList.get(i));
    }
    return retval;
  }

  public static String changeLineAlignmentForHtml(String value)
  {
    if (value != null) {
      value = value.replaceAll("\r\n", "<br>");
      value = value.replaceAll("\n", "<br>");
      value = value.replaceAll("\r", "<br>");
    } else {
      value = "";
    }
    return value;
  }

  public static int getLength(String value)
  {
    int length = 0;
    if (value != null) {
      length = value.length();
    }
    return length;
  }

  public static String removeSpecialLetters(String str)
  {
    String match = "[^가-힣xfe0-9a-zA-Z\\s]";
    str = str.replaceAll(match, "");
    return str;
  }

  public static boolean parseBoolean(Object obj)
  {
    boolean result = false;
    result = parseBoolean(obj, "true");
    return result;
  }

  public static boolean parseBoolean(Object obj, String trueTargetStr)
  {
    boolean result = false;
    if (obj != null) {
      if ((obj instanceof String)) {
        String objStr = obj.toString();
        if (objStr.equals(trueTargetStr))
          result = true;
        else
          result = false;
      }
      else if ((obj instanceof BigDecimal)) {
        BigDecimal c = (BigDecimal)obj;
        String val = String.valueOf(Integer.valueOf(c.intValue()));
        if (val.equalsIgnoreCase(trueTargetStr))
          result = true;
        else
          result = false;
      }
      else if ((obj instanceof Boolean)) {
        result = ((Boolean)obj).booleanValue();
      }
    }
    else result = false;

    return result;
  }

  public static int parseInteger(Object obj)
  {
    int result = 0;
    if (obj != null) {
      if ((obj instanceof BigDecimal)) {
        BigDecimal c = (BigDecimal)obj;
        result = Integer.valueOf(c.intValue()).intValue();
      } else if ((obj instanceof String)) {
        result = Integer.parseInt((String)obj);
      } else if ((obj instanceof Integer)) {
        result = ((Integer)obj).intValue();
      }
    }
    return result;
  }

  public static String parseString(Object obj)
  {
    String result = "";
    if (obj != null) {
      if ((obj instanceof BigDecimal)) {
        BigDecimal c = (BigDecimal)obj;
        int val = Integer.valueOf(c.intValue()).intValue();
        result = String.valueOf(val);
      } else if ((obj instanceof String)) {
        result = (String)obj;
      } else if ((obj instanceof Integer)) {
        int val = ((Integer)obj).intValue();
        result = String.valueOf(val);
      } else if ((obj instanceof Boolean)) {
        boolean val = ((Boolean)obj).booleanValue();
        result = String.valueOf(val);
      }
    }
    return result;
  }

  public static String replaceNullToNA(String str) {
    if ((str == null) || ("null".equals(str))) {
      str = "N/A";
    }
    return str;
  }

  public static String replaceNull(String source, String replace) {
    if ((source == null) || (source.trim().equals("")) || (source.trim().equals("null")) || (source.trim().equals("undefined"))) {
      return replace;
    }
    return source;
  }

  public static String trim(String str)
  {
    String result = "";
    if (str != null) {
      result = str.trim();
    }
    return result;
  }
}