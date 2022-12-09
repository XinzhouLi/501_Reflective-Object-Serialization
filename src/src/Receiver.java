import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	private Thread t;
	private String message;

	public void start () {

		if (t == null) {
			t = new Thread ((Runnable) this, "2");
			t.start ();
		}
	}
	public String run() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("Init Receiver....");
			Socket s = ss.accept();
			System.out.println("Receiver:"+s.getInetAddress().getLocalHost()+"connected to sender");

			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//读取客户端发送来的消息
			String mess = br.readLine();
			System.out.println("sender："+mess);
			return mess;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "nothing get";
	}


}
