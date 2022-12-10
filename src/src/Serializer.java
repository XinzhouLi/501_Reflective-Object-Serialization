
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;


public class Serializer {

	JSONObject root = new JSONObject();
	JSONArray rootArray = new JSONArray();
	private Object incomeO;

	public Serializer(Object O) {
		incomeO = O;
	}
	public Serializer() {
	}
	public void init(Object O){
		incomeO = O;
	}
	public static void main(String[] args) throws IllegalAccessException {
		Collection_object co = new Collection_object(true);
		Serializer ser = new Serializer(co);
		JSONObject x = ser.serialize();
		System.out.println(x);

	}

	public JSONObject serialize() throws IllegalAccessException {

		// serialize for no ref no array situation
		JSONObject noRef = serObj(incomeO);

		// add all Object to root ObjectArray
		rootArray.put(noRef);
		root.put("Object", rootArray);
		return root;
	}

	public JSONObject serObj(Object o) throws IllegalAccessException {
		JSONObject ret = new JSONObject();
		ret.put("class", o.getClass().getTypeName());
		if (o.getClass().isArray()) {
			ret.put("type", "array");
		} else {
			ret.put("type", "object");
		}
		JSONArray field = new JSONArray();


		for (Field fd : o.getClass().getDeclaredFields()) {
			if (Collection.class.isAssignableFrom(fd.getType()) == true){
				System.out.println(fd.getType().getTypeName());
				Gson serC = new Gson();
				JSONObject temp = new JSONObject();
				temp.put("class", fd.getType().getTypeName());
				temp.put("name", fd.getName());
				temp.put("obj", (String)serC.toJson(fd.get(o)));
				field.put(temp);
			}
			else if (fd.get(o) == null) {
				field.put(serNull(fd));
			} else if (fd.getType().isPrimitive()) {
				// handle primitive
				field.put(serPrimitiveField(o, fd));
			} else if (fd.getType().isArray()) {
				// handle array
				Object obj = fd.get(o);
				JSONObject ref = new JSONObject();
				JSONObject serObj = new JSONObject();
				if (fd.getType().getComponentType().isPrimitive()) {
					// handle primitive array
					JSONArray temp = new JSONArray(obj);
					serObj = serPrimitiveArray(fd, temp);
				} else {
					// handle Object array
					Object[] oa = castObjArray(obj);
					serObj = serObjArray(fd, oa);
				}
				ref.put("name", fd.getName());
				ref.put("declaringclass", fd.getType().getTypeName());
				ref.put("reference", serObj.get("id"));
				field.put(ref);
				rootArray.put(serObj);
			} else {
				// handle object
				Object obj = fd.get(o);
				JSONObject ref = new JSONObject();
				JSONObject serObj = serObj(obj);
				ref.put("name", fd.getName());
				ref.put("declaringclass", fd.getType().getTypeName());
				ref.put("reference", serObj.get("id"));
				field.put(ref);
				rootArray.put(serObj);

			}
		}

		ret.put("field", field);
		int hashId = field.hashCode();
		ret.put("id", hashId);
		return ret;
	}

	public JSONObject serObjArray(Field fd, Object[] oa) throws IllegalAccessException {
		JSONObject ret = new JSONObject();
		ret.put("class", fd.getType().getTypeName());
		ret.put("type", "array");
		ret.put("length", oa.length);
		JSONArray en = new JSONArray();
		for (var i : oa) {
			JSONObject ref = new JSONObject();
			JSONObject serObj = serObj(i);
			ref.put("reference", serObj.get("id"));
			rootArray.put(serObj);

			en.put(ref);
		}
		ret.put("entries", en);
		ret.put("id", en.hashCode());
		return ret;
	}

	public JSONObject serPrimitiveArray(Field fd, JSONArray ja) throws IllegalAccessException {

		JSONObject ret = new JSONObject();
		ret.put("class", fd.getType().getTypeName());
		ret.put("type", "array");
		ret.put("length", ja.length());
		JSONArray en = new JSONArray();
		for (var i : ja) {
			JSONObject temp = new JSONObject();
			temp.put("value", i);
			en.put(temp);
		}
		ret.put("entries", en);
		ret.put("id", en.hashCode());
		return ret;
	}


	public Object[] castObjArray(Object obj) {


		int length = Array.getLength(obj);
		Object[] os = new Object[length];
		for (int i = 0; i < os.length; i++) {
			os[i] = Array.get(obj, i);
		}
		return os;
	}

	public JSONObject serPrimitiveField(Object o, Field fd) throws IllegalAccessException {

		JSONObject temp = new JSONObject();
		temp.put("name", fd.getName());
		temp.put("declaringclass", fd.getType().getTypeName());
		temp.put("value", getValue(fd, o));

		return temp;
	}


	public JSONObject serNull(Field fd) {
		JSONObject temp = new JSONObject();
		temp.put("name", fd.getName());
		temp.put("declaringclass", fd.getType().getTypeName());
		temp.put("value", "null");
		return temp;
	}

	public Object getValue(Field fd, Object obj) throws IllegalAccessException {
		if (fd.getType().toString().equals("short")) {
			return fd.getShort(obj);
		} else if (fd.getType().toString().equals("int")) {
			return fd.getInt(obj);
		} else if (fd.getType().toString().equals("long")) {
			return fd.getLong(obj);
		} else if (fd.getType().toString().equals("float")) {
			return fd.getFloat(obj);
		} else if (fd.getType().toString().equals("double")) {
			return fd.getDouble(obj);
		} else if (fd.getType().toString().equals("byte")) {
			return fd.getByte(obj);
		} else if (fd.getType().toString().equals("boolean")) {
			return fd.getBoolean(obj);
		} else if (fd.getType().toString().equals("char")) {
			return fd.getChar(obj);
		}
		return "not pri";
	}
}
