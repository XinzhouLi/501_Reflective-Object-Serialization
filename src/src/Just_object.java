import java.util.Scanner;

public class Just_object {
	int id;
	boolean message;

	public Just_object() {
	}

	public Just_object(int id, boolean message) {
		this.id = id;
		this.message = message;
	}

	public void init (){
		System.out.println("set value for id");
		id = Integer.parseInt(inputData());
		System.out.println("set value for message");
		message = Boolean.parseBoolean(inputData());
	}
	public String inputData(){
		Scanner scan = new Scanner(System.in);
		String str = new String();
		if(scan.hasNext()){
			str = scan.next();
		}
		scan.close();
		return str;
	}
}
