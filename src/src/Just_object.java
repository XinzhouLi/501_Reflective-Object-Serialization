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
		Scanner scan = new Scanner(System.in);
		System.out.println("set value for int id");
		String t = scan.next();
		id = Integer.parseInt(t);
		System.out.println("set value for boolean message");
		t= scan.next();
		message = Boolean.parseBoolean(t);

	}

}
