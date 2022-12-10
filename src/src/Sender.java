import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Sender {


	public static void main(String[] args) {
		String serverName = "localhost";
		int port = 6066;
		try {
/*			Only_primitives a = new Only_primitives();
			a.init();*/
			System.out.println("Connect to " + serverName + " ，Port：" + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Host ip：" + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			// write the message
			Scanner scan = new Scanner(System.in);
			String str = "";
			System.out.println("Choose the # of times you want to send");
			int times = Integer.parseInt(scan.next());
			for (int i = 0; i < times; i++) {
				System.out.println("Choose the type of the object to serialize\n" +
						"1. Only primitive object\n" +
						"2. Only object ref object\n" +
						"3. Array of object \n" +
						"4. Array of primitive data\n" +
						"5. Collection");

				String choice = scan.next();
				String result = "";
				Serializer ser = new Serializer();
				if (Objects.equals(choice, "1")) {
					Only_primitives temp = new Only_primitives();
				temp.init();
					ser.init(temp);
				} else if (Objects.equals(choice, "2")) {
					Only_object temp = new Only_object();
				temp.init();
					ser.init(temp);
				} else if (Objects.equals(choice, "3")) {
					Array_objects temp = new Array_objects();
				temp.init();
					ser.init(temp);

				} else if (Objects.equals(choice, "4")) {
					Array_primitives temp = new Array_primitives();
				temp.init();
					ser.init(temp);
				} else {
					Collection_object temp = new Collection_object();
				temp.init();
					ser.init(temp);
				}

				result = ser.serialize().toString();
				out.writeUTF(result);
			}


			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
