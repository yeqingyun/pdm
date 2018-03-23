package gnwf.utill;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 将ResultSet对象转换为List<Object>
 */
public class ResultSetToObject {
	public static List<Object> turnToMapList(ResultSet rs) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		try {
			ResultSetMetaData rsMeta = rs.getMetaData();
			for (int i = 0; i < rsMeta.getColumnCount(); ++i) {
				map.put(rsMeta.getColumnName(i + 1), new ArrayList<String>());
			}
			while (rs.next()) {
				for (int i = 0; i < rsMeta.getColumnCount(); ++i) {
					String columnName = rsMeta.getColumnName(i + 1);
					map.get(columnName).add(rs.getString(columnName));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List turnToMap(ResultSet rs) throws SQLException {  
	    List records = new ArrayList();  
	        ResultSetMetaData rsmd = rs.getMetaData();  
	        int fieldCount = rsmd.getColumnCount();  
	        while (rs.next()) {  
	            Map valueMap = new LinkedHashMap();  
	            for (int i = 1; i <= fieldCount; i++) {  
	                String fieldClassName = rsmd.getColumnClassName(i);  
	                String fieldName = rsmd.getColumnName(i);  
	                recordMappingToMap(fieldClassName, fieldName, rs, valueMap);  
	            }  
	            records.add(valueMap);  
	        }  
	        rs.close();  
	    
	    return records;  
	}  
	        
	private static void recordMappingToMap(String fieldClassName, String fieldName, ResultSet rs, Map fieldValue) throws SQLException {  
	    fieldName = fieldName.toLowerCase();  
	  
	    //优先规则：常用类型靠前  
	  
	    if (fieldClassName.equals("java.lang.String")) {  
	        String s = rs.getString(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Integer")) {  
	        int s = rs.getInt(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, 0);  
	        } else {  
	            fieldValue.put(fieldName, s);//早期jdk需要包装，jdk1.5后不需要包装  
	        }  
	    } else if (fieldClassName.equals("java.lang.Long")) {  
	        long s = rs.getLong(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Boolean")) {  
	        boolean s = rs.getBoolean(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Short")) {  
	        short s = rs.getShort(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Float")) {  
	        float s = rs.getFloat(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Double")) {  
	        double s = rs.getDouble(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Timestamp")) {  
	        java.sql.Timestamp s = rs.getTimestamp(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Date") || fieldClassName.equals("java.util.Date")) {  
	        java.util.Date s = rs.getDate(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Time")) {  
	        java.sql.Time s = rs.getTime(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Byte")) {  
	        byte s = rs.getByte(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, new Byte(s));  
	        }  
	    } else if (fieldClassName.equals("[B") || fieldClassName.equals("byte[]")) {  
	        //byte[]出现在SQL Server中  
	        byte[] s = rs.getBytes(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.math.BigDecimal")) {  
	        BigDecimal s = rs.getBigDecimal(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.lang.Object")  
	            || fieldClassName.equals("oracle.sql.STRUCT")) {  
	        Object s = rs.getObject(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Array")  
	            || fieldClassName.equals("oracle.sql.ARRAY")) {  
	        java.sql.Array s = rs.getArray(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Clob")) {  
	        java.sql.Clob s = rs.getClob(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else if (fieldClassName.equals("java.sql.Blob")) {  
	        java.sql.Blob s = rs.getBlob(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    } else {//对于其它任何未知类型的处理  
	        Object s = rs.getObject(fieldName);  
	        if (rs.wasNull()) {  
	            fieldValue.put(fieldName, ' ');  
	        } else {  
	            fieldValue.put(fieldName, s);  
	        }  
	    }  
	  
	}  
	public static List<Object> turnToObject(ResultSet resultSet,
			Class<?> objClass) {
		/** 存储转化后的实体类 */
		List<Object> listObjs = new ArrayList<Object>();

		/** resultSet数据表中的字段名称 */
		String[] columnNames = null;

		/** resultSet数据表中对应字段的数据类型 */
		String[] columnTypes = null;

		try {
			if (resultSet == null || !resultSet.next()) {
				return listObjs;
			} else {
				ResultSetMetaData metaResult = resultSet.getMetaData();
				int length = metaResult.getColumnCount();
				columnNames = new String[length];
				columnTypes = new String[length];

				for (int i = 0; i < columnNames.length; i++) {
					columnNames[i] = metaResult.getColumnName(i + 1);
					columnTypes[i] = metaResult.getColumnClassName(i + 1);
				}

				while (resultSet.next()) {
					try {
						/* 实例化实体类 */
						Object obj = objClass.newInstance();

						/* 根据字段名调用实体类中的set方法 */
						for (int j = 0; j < columnNames.length; j++) {
							Method method = objClass.getDeclaredMethod("set"
									+ upInitial(columnNames[j]),
									paraTypeClass(columnTypes[j]));
							method.invoke(obj,
									resultSet.getObject(columnNames[j]));
						}

						listObjs.add(obj);

					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listObjs;
	}

	/**
	 * 将首字母变为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upInitial(String str) {
		char[] chars = str.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	/**
	 * * 字段的数据类型
	 * 
	 * @param str
	 * @return
	 */
	public static Class<?> paraTypeClass(String str) {
		if (str.equals("java.lang.String")) {
			return java.lang.String.class;
		} else if (str.equals("java.lang.Integer")) {
			return java.lang.Integer.class;
		} else if (str.equals("java.lang.Character")) {
			return java.lang.Character.class;
		} else if (str.equals("java.lang.Double")) {
			return java.lang.Double.class;
		} else if (str.equals("java.lang.Short")) {
			return java.lang.Short.class;
		} else if (str.equals("java.lang.Byte")) {
			return java.lang.Byte.class;
		} else if (str.equals("java.lang.Float")) {
			return java.lang.Float.class;
		} else if (str.equals("java.lang.Boolean")) {
			return java.lang.Boolean.class;
		} else if (str.equals("java.util.Date")) {
			return java.util.Date.class;
		}
		return null;

	}
}