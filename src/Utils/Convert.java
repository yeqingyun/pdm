package Utils;

public class Convert
{
  public static String toString(Object o)
  {
    if (o == null) return "";
    return o.toString();
  }
  public static Integer toInt(Object o) {
    if (o == null) o = Integer.valueOf(0);

    double d = Double.parseDouble(o.toString());
    int i = 0;
    i = (int)(i - d);
    return Integer.valueOf(-i);
  }
  public static Long toLong(Object o) {
    if (o == null) o = Integer.valueOf(0);
    return Long.valueOf(Long.parseLong(o.toString()));
  }
  public static Float toFloat(Object o) {
    if (o == null) o = Integer.valueOf(0);
    return Float.valueOf(Float.parseFloat(o.toString()));
  }
  public static Double toDouble(Object o) {
    if (o == null) o = Integer.valueOf(0);
    return Double.valueOf(Double.parseDouble(o.toString()));
  }
  public static Short toShort(Object o) {
    if (o == null) o = Integer.valueOf(0);
    return Short.valueOf(Short.parseShort(o.toString()));
  }
  public static Boolean toBoolean(Object o) {
    if (o == null) o = Boolean.valueOf(false);
    return Boolean.valueOf(Boolean.parseBoolean(o.toString()));
  }
  public static Character toChar(Object o) {
    if (o == null) o = "";
    return (Character)o;
  }
}