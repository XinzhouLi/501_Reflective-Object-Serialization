import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Array_objects {

	public Just_object[] OPs;

	public Array_objects() {
	}

	public Array_objects(boolean t){
		OPs = new Just_object[]{new Just_object(1, true),
				new Just_object(2, false),
				new Just_object(3, true)
				};
	}
	public void init(){
		System.out.println("How many element you want to put in array");
		int num = Integer.parseInt(inputData());
		ArrayList<Just_object> temp = new ArrayList<Just_object>();
		for (int i = 0; i < num; i++) {
			Just_object t = new Just_object();
			t.init();
			temp.add(t);
		}
		System.out.println(temp.toArray().getClass().getTypeName());
		Just_object[] t = new Just_object[num];
		OPs = temp.toArray(t);
	}
	public String inputData(){
		Scanner scan = new Scanner(System.in);
		String str = new String();
		if(scan.hasNext()){
			str = scan.next();
		}
		return str;
	}
}
