//import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerializerTest {

	//All the test can not run all of them because the hash code will be disorderd.
	//It should be run one by one
	@Test
	public void serialize() throws IllegalAccessException {
		JSONObject jb = new JSONObject();
		Only_primitives op = new Only_primitives(474,'a',true,0.22, (float) 0.22, (byte) 324, 496516);
		Serializer se = new Serializer(op);
		jb = se.serialize();
		assertEquals(jb.toString(), "{\"Object\":[{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":474},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"a\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.22},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.22},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":68},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":496516}],\"id\":1018547642,\"type\":\"object\",\"class\":\"Only_primitives\"}]}");
	}

	@Test
	public void serObj() throws IllegalAccessException {
		JSONObject jb = new JSONObject();
		Only_object ao = new Only_object();
		Serializer se = new Serializer(ao);
		jb = se.serialize();
		assertEquals(jb.toString(), "{\"Object\":[{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":474},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"a\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.22},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.22},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":68},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":496516}],\"id\":288665596,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":75},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"c\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.55},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.27},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":42},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":453}],\"id\":13648335,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":7787},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"d\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.72},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.89},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":13},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":25}],\"id\":312116338,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"field\":[{\"declaringclass\":\"class Only_primitives\",\"reference\":288665596,\"name\":\"p1\"},{\"declaringclass\":\"class Only_primitives\",\"reference\":13648335,\"name\":\"p2\"},{\"declaringclass\":\"class Only_primitives\",\"reference\":312116338,\"name\":\"p3\"}],\"id\":453211571,\"type\":\"object\",\"class\":\"Only_object\"}]}");


	}

	@Test
	public void serObjArray() throws IllegalAccessException {
		JSONObject jb = new JSONObject();
		Array_objects ao = new Array_objects();
		Serializer se = new Serializer(ao);
		jb = se.serialize();
		assertEquals(jb.toString(), "{\"Object\":[{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":1},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"c\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.11},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.22},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":123},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":1516}],\"id\":288665596,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":1},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"c\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.11},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.22},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":123},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":1516}],\"id\":13648335,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"field\":[{\"declaringclass\":\"int\",\"name\":\"id\",\"value\":1},{\"declaringclass\":\"char\",\"name\":\"ch\",\"value\":\"c\"},{\"declaringclass\":\"boolean\",\"name\":\"bool\",\"value\":true},{\"declaringclass\":\"double\",\"name\":\"db\",\"value\":0.11},{\"declaringclass\":\"float\",\"name\":\"fl\",\"value\":0.22},{\"declaringclass\":\"byte\",\"name\":\"bt\",\"value\":123},{\"declaringclass\":\"long\",\"name\":\"ln\",\"value\":1516}],\"id\":312116338,\"type\":\"object\",\"class\":\"Only_primitives\"},{\"entries\":[{\"reference\":288665596},{\"reference\":13648335},{\"reference\":312116338}],\"length\":3,\"id\":453211571,\"type\":\"array\",\"class\":\"class [LOnly_primitives;\"},{\"field\":[{\"declaringclass\":\"class [LOnly_primitives;\",\"reference\":453211571,\"name\":\"OPs\"}],\"id\":796684896,\"type\":\"object\",\"class\":\"Array_objects\"}]}");

	}


	@Test
	public void serPrimitiveArray() throws IllegalAccessException {
		JSONObject jb = new JSONObject();
		Array_primitives ao = new Array_primitives(new int[]{1, 2, 4, 5, 6}, new float[]{(float) 0.1, (float) 0.2, (float) 0.3});
		Serializer se = new Serializer(ao);
		jb = se.serialize();
		assertEquals(jb.toString(), "{\"Object\":[{\"entries\":[{\"value\":1},{\"value\":2},{\"value\":4},{\"value\":5},{\"value\":6}],\"length\":5,\"id\":2101440631,\"type\":\"array\",\"class\":\"class [I\"},{\"entries\":[{\"value\":0.1},{\"value\":0.2},{\"value\":0.3}],\"length\":3,\"id\":2109957412,\"type\":\"array\",\"class\":\"class [F\"},{\"field\":[{\"declaringclass\":\"class [I\",\"reference\":2101440631,\"name\":\"its\"},{\"declaringclass\":\"class [F\",\"reference\":2109957412,\"name\":\"fls\"},{\"declaringclass\":\"class [F\",\"name\":\"nl\",\"value\":\"null\"}],\"id\":901506536,\"type\":\"object\",\"class\":\"Array_primitives\"}]}");

	}
}