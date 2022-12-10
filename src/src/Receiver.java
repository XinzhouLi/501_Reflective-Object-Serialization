import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Receiver extends Thread{
	private ServerSocket serverSocket;

	public Receiver(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(300000);
	}

	public void run()
	{
		while(true)
		{
			try
			{


					System.out.println("Wait for connection, port#：" + serverSocket.getLocalPort() + "...");
					Socket server = serverSocket.accept();
					System.out.println("Sender ip address：" + server.getRemoteSocketAddress());
					// recive the message
				while (true) {
					DataInputStream in = new DataInputStream(server.getInputStream());
					String input = in.readUTF();

					System.out.println("input JSON serializer Object: "+input);
					Deserializer deser = new Deserializer(input);
					Object result = deser.deserialized();
					Serializer ser = new Serializer(result);
					JSONObject output = ser.serialize();
					System.out.println("output JSON deserializer Object : "+output.toString());
				}


			}
			catch (EOFException f){
				break;
			}
			catch(SocketTimeoutException s)
			{
				System.out.println("Socket timed out!");
				break;
			}catch(IOException e)
			{
				e.printStackTrace();
				break;
			} catch (NoSuchFieldException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public static void main(String [] args)
	{
		int port = 6066;
		try
		{
			Thread t = new Receiver(port);
			t.run();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
