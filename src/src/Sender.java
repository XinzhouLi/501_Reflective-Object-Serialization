import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sender extends Thread{
	private Thread t;
	private String message;

	Sender (String me){
		message = me;
	}
	public void start () {

		if (t == null) {
			t = new Thread (this, "1");
			t.start ();
		}
	}
	public void run() {
		try {
			Socket s = new Socket("127.0.0.1", 8888);

			//构建IO
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			//向服务器端发送一条消息
			bw.write(message);
			bw.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
