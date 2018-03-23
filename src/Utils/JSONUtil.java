package Utils;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONUtil
{
  public static String Encode(Object obj)
  {
    if ((obj == null) || (obj.toString().equals("null"))) return null;
    if ((obj != null) && (obj.getClass() == String.class)) {
      return obj.toString();
    }
    JSONSerializer serializer = new JSONSerializer();
   // serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), new Class[] { Date.class });

    return serializer.deepSerialize(obj);
  }
  public static Object Decode(String json) {
    if (StringUtil.isNullOrEmpty(json)) return "";
    JSONDeserializer<?> deserializer = new JSONDeserializer<Object>();
   // deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));
    Object obj = deserializer.deserialize(json);
    if ((obj != null) && (obj.getClass() == String.class)) {
      return Decode(obj.toString());
    }
    return obj;
  }
}