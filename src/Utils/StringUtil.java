package Utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil
{
  public static boolean isNullOrEmpty(Object obj)
  {
    return (obj == null) || ("".equals(obj.toString()));
  }
  public static String toString(Object obj) {
    if (obj == null) return "null";
    return obj.toString();
  }
  public static String join(Collection s, String delimiter) {
    StringBuffer buffer = new StringBuffer();
    Iterator iter = s.iterator();
    while (iter.hasNext()) {
      buffer.append(iter.next());
      if (iter.hasNext()) {
        buffer.append(delimiter);
      }
    }
    return buffer.toString();
  }
//  public static int toInt(String s) {
//    String n;
//    try { double d1 = Double.parseDouble(s);
//      return Convert.toInt(Double.valueOf(d1)).intValue();
//    }
//    catch (Exception n)
//    {
//      n = "";
//      for (int i = 0; i < s.length(); i++)
//      {
//        String c = s.substring(i, i + 1);
//        if (!isInteger(c))
//          break;
//        n = n + c;
//      }
//
//    }
//
//    return Convert.toInt(n).intValue();
//  }
  public static boolean isInteger(String input) {
    try {
      Integer.parseInt(input);
      return true; } catch (Exception ex) {
    }
    return false;
  }
}