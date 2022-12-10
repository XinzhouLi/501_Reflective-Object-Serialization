import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Array_primitives {

	public int[] its;
	public float[] fls;


	public Array_primitives(boolean i) {
		its = new int[]{1,3,5,8,4};
		fls = new float[]{(float) 2.1, (float) 5.4, (float) 4.7, (float) 8.2};
	}

	public Array_primitives() {
	}

	public void init(){
		System.out.println("How many int element want to put in int array:");
		int num = Integer.parseInt(inputData());
		int[] temp = (int[]) Array.newInstance(int.class, num);
		for (int i = 0; i < num; i++) {
			System.out.println("input an int");
			int n = Integer.parseInt(inputData());
			Array.setInt(temp, i, n);
		}
		its = temp;
		System.out.println("How many float element want to put float array:");
		int num1 = Integer.parseInt(inputData());
		float[] temp1 = (float[]) Array.newInstance(float.class, num);
		for (int i = 0; i < num1; i++) {
			System.out.println("input an float");
			float n = Float.parseFloat(inputData());
			Array.setFloat(temp1, i, n);
		}
		fls = temp1;
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
