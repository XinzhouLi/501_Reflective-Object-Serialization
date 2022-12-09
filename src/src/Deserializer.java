import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class Deserializer {

	String jsonStr;

	public Deserializer(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public Object deserialized() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		JSONObject de = new JSONObject(jsonStr);
		String className = (String) de.get("Class");
		Class<?> objClass = Class.forName(className);
		Constructor objCst = objClass.getDeclaredConstructor(null);
		Object result = objCst.newInstance();


		// for primavitve type
		for (String i : de.keySet()){
			if (i.equals("Class"))
				continue;
			Field tempF = result.getClass().getDeclaredField(i);
			tempF.setAccessible(true);
			setValue(tempF, result, de.get(i));
		}
		return result;
	}


	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		Only_primitives test = new Only_primitives(1,'c',true,0.11, (float) 0.22, (byte) 123, 1516);
		Serializer ser = new Serializer(test);
		JSONObject x = ser.serialize();
		Deserializer deser = new Deserializer(x.toString());
		Object result = deser.deserialized();
		System.out.println();
	}
	public void setValue(Field fd, Object obj, Object value) throws IllegalAccessException {
		if (fd.getType().toString().equals("short")) {
			short temp = (short) value;
			 fd.setShort(obj,temp);
		} else if (fd.getType().toString().equals("int")) {
			int temp = (int) value;
			 fd.setInt(obj,temp);
		} else if (fd.getType().toString().equals("long")) {
			Integer temp = (Integer) value;
			 fd.setLong(obj,temp.longValue());
		} else if (fd.getType().toString().equals("float")) {
			BigDecimal bd = (BigDecimal) value;
			float temp = bd.floatValue();
			 fd.setFloat(obj,temp);
		} else if (fd.getType().toString().equals("double")) {
			BigDecimal bd = (BigDecimal) value;
			double temp = bd.doubleValue();
			 fd.setDouble(obj,temp);
		} else if (fd.getType().toString().equals("byte")) {
			Integer bt = (Integer) value;
			byte temp = ((Integer) value).byteValue();
			 fd.setByte(obj,temp);
		} else if (fd.getType().toString().equals("boolean")) {
			boolean temp = (boolean) value;
			 fd.setBoolean(obj,temp);
		} else if (fd.getType().toString().equals("char")) {
			String str = (String) value;
			char temp = str.charAt(0);
			 fd.setChar(obj,temp);
		}

	}
}
