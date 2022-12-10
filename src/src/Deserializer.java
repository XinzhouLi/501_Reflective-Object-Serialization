import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Deserializer {

	JSONArray rootArray;

	public Deserializer(String jsonStr) {
		JSONObject temp = new JSONObject(jsonStr);
		JSONArray ja = (JSONArray) temp.get("Object");
		this.rootArray = ja;
	}

	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		Collection_object co = new Collection_object(true);
		Serializer ser = new Serializer(co);
		JSONObject x = ser.serialize();
		Deserializer dx = new Deserializer(x.toString());
		dx.deserialized();

	}
	public Object deserialized() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		JSONObject root = (JSONObject) rootArray.get(rootArray.length()-1);
		int rootId = root.getInt("id");
		Object ret = deserObj(root, rootId);
		return ret;
	}

	public Object deserObj(JSONObject o, int id) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		Object ret= new Object();
		if (Objects.equals(o.get("type"), "object")){
			//handle object
			Class classType = Class.forName((String) o.get("class"));
			Constructor cns = classType.getDeclaredConstructor(null);
			ret = cns.newInstance();
			JSONArray jFields = o.getJSONArray("field");
			for (Object i : jFields){
				JSONObject jfd = (JSONObject)i;

				if (isPrimitive(jfd)){
					// handle the primitive
					setPrimitive(ret, jfd);
				}
				else {
					// handle the object
					// need to handle collection in hear

					if (((JSONObject) i).get("class").equals("java.util.ArrayList") ){

						Type arrlisttype = new TypeToken<List<String>>() {}.getType();
						Gson gson = new Gson();
						List<String> alist = gson.fromJson((String) ((JSONObject)i).get("obj"), arrlisttype);
						ret = alist;
						continue;
					}
					//find the corresponded JSON object
					int refId = jfd.getInt("reference");
					JSONObject jref = new JSONObject();
					for (int j = 0; j < rootArray.length(); j++) {
						jref = (JSONObject) rootArray.get(j);
						if ((int)jref.get("id") == refId)
							break;
					}
					// deserialize the ref object
					Object temp = new Object();
					if (Objects.equals(jref.get("type"), "array")){
						// for array object
						temp = deserObj(jref, refId);
					}else{
						// for normal obj
						Class fieldClass = Class.forName((String) jfd.get("declaringclass"));
						Constructor con = fieldClass.getDeclaredConstructor(null);
						temp = con.newInstance();
						temp = deserObj(jref, refId);

						// set created deserialize object back to field

					}
					ret.getClass().getDeclaredField((String) jfd.get("name")).set(ret,temp);
				}
			}
		}else{
			//handle array
			String StrclassComponent = ((String) o.get("class")).replace("[]","");
			int arrayLen = (int) o.get("length");
			if (primitiveType(StrclassComponent).isPrimitive()){
				// handle primitive array
				JSONArray ja = o.getJSONArray("entries");
				JSONArray temp = new JSONArray();
				for (int i = 0; i < ja.length(); i++) {
					temp.put(((JSONObject)ja.get(i)).get("value"));
				}
				Object ins = Array.newInstance(primitiveType(StrclassComponent), arrayLen);
				if (StrclassComponent.equals("float")){
					for (int i = 0; i < arrayLen; i++) {
						BigDecimal bd = (BigDecimal) temp.get(i);
						float t = bd.floatValue();
						Array.set(ins, i, t);
					}
				} else if (StrclassComponent.equals("int")) {
					for (int i = 0; i < arrayLen; i++) {
						Array.set(ins, i, ((int) temp.get(i)));
					}
				}
				ret = ins;
			}
			else {
				// handle object array
				Class comType = Class.forName(StrclassComponent);
				Object ins = Array.newInstance(comType, arrayLen);
				for (int i = 0; i < arrayLen; i++) {
					int refid = (int) ((JSONObject)((JSONArray)o.get("entries")).get(i)).get("reference");
					JSONObject jref = new JSONObject();
					for (int j = 0; j < rootArray.length(); j++) {
						jref = (JSONObject) rootArray.get(j);
						if ((int)jref.get("id") == refid)
							break;
					}
					Object temp = deserObj(jref, id);
					Array.set(ins, i, temp);
				}
				ret = ins;


			}


		}
		return ret;
	}

	public void setPrimitive (Object ret, JSONObject jfd) throws NoSuchFieldException, IllegalAccessException {
		Field ofd = ret.getClass().getDeclaredField((String) jfd.get("name"));
		ofd.setAccessible(true);
		setValue(ofd,ret,jfd.get("value"));
	}
	public boolean isPrimitive (JSONObject jfd){
		try {
			jfd.get("value");
			return true;
		} catch (Exception e){}
		return false;
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


	public <T> T[] newInst(Class<T> componentType, int length){
		return (T[]) Array.newInstance(componentType,length);
	}
	public Class primitiveType(String ty){
		if (ty.equals("int"))
			return int.class;
		else if (ty.equals("float"))
			return float.class;
		return Object.class;
	}

}
