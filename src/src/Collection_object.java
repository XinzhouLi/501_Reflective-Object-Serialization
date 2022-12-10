import java.util.ArrayList;
import java.util.Scanner;

public class Collection_object {

	public ArrayList<String> arlist = new ArrayList<>();
	public ArrayList<String>  arlist2 = new ArrayList<>();

	public Collection_object() {
	}

	public Collection_object(boolean i) {
		arlist.add("a1");
		arlist.add("a2");
		arlist2.add("a2");
		arlist2.add("a3");
	}
	public void init(){
		System.out.println("How many String element want to put:");
		int num = Integer.parseInt(inputData());
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			System.out.println("Set value for arlist1: ");
			temp.add(inputData());
		}
		arlist = temp;
		System.out.println("How many String element want to put:");
		int num1 = Integer.parseInt(inputData());
		ArrayList<String> temp1 = new ArrayList<String>();
		for (int i = 0; i < num1; i++) {
			System.out.println("Set value for arlist2: ");
			temp.add(inputData());
		}
		arlist2 = temp1;
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
