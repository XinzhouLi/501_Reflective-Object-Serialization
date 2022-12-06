import org.json.JSONObject;
import org.json.JSONString;

public class Deserializer {

	String jsonStr;

	public Deserializer(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public void deserialized(){
		JSONObject de = new JSONObject(jsonStr);
		System.out.printf(String.valueOf(de.keys()));
	}


	public static void main(String[] args) throws IllegalAccessException {
		Only_primitives test = new Only_primitives(1,'c',true,0.11);
		Serializer ser = new Serializer(test);
		JSONObject x = ser.serialize();
//		Deserializer deser = new Deserializer();
	}
}
