import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class DeserializerTest {

	@Test
	public void deserialized() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		Only_primitives test = new Only_primitives(1,'c',true,0.11, (float) 0.22, (byte) 123, 1516);
		Serializer ser = new Serializer(test);
		JSONObject x = ser.serialize();
		System.out.println("income: "+x.toString());

		Deserializer deser = new Deserializer(x.toString());
		Object result = deser.deserialized();

		Serializer check = new Serializer(result);
		JSONObject y = check.serialize();
		System.out.println(y);
		assertEquals(test.ch,((Only_primitives)result).ch);
	}
	@Test
	public void deserialized1() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		Array_objects aotest = new Array_objects(true);

		Serializer ser = new Serializer(aotest);
		JSONObject x = ser.serialize();
		System.out.println("income: "+x.toString());

		Deserializer deser = new Deserializer(x.toString());
		Object result = deser.deserialized();

		Serializer check = new Serializer(result);
		JSONObject y = check.serialize();
		System.out.println(y);
//		assertEquals(aotest.OPs.id,((Array_objects)result).OPs[1].bt);
	}



}