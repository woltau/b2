import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class B2_EchoService {

	public static void main(String[] args) throws IOException {
		ServerSocket echoServer = new ServerSocket(7);
		String startMsg = "Echo SERVICE ";
		
		echoServer.setSoTimeout(9000);
		try {
			System.out.println("Starting EchoService");
			Socket clientSocket = echoServer.accept();
			String cl_info = clientSocket.getInetAddress().getHostAddress()
					+" "+clientSocket.getPort() + " "+clientSocket.getLocalPort();
			System.out.println(cl_info);
			System.out.println("... accepted");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(clientSocket.getOutputStream()));
			out.write(startMsg);
			out.newLine();
			out.flush();
			String inputLine;
			boolean ende = false;
			while (!ende) {
				inputLine = in.readLine();
				if (inputLine == null || inputLine.equals("ende")) {
					System.out.println("das Ende ist nah ....");
					ende = true;
					out.write("Ende");
					out.newLine();
					out.flush();
					break;
				}
				out.write("Eingabe: "+inputLine+" length: "+inputLine.length()
						+" Ausgabe: "+inputLine.toUpperCase());
				out.newLine();
				out.flush();
			} // end while
			echoServer.close();
			System.out.println("EchoService beendet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // end class EchoService
