import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class B2_EchoSocketInteractiveTester {

	public static void main(String[] args) throws IOException {

		Socket client = new Socket("localhost", 7);
		System.out.println("Socket gestartet");
		String cl_info = client.getInetAddress().getHostAddress()
				+" "+client.getPort() + " "+client.getLocalPort();
		System.out.println(cl_info);


        BufferedWriter out =
                new BufferedWriter(
                		new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        System.out.println(in.readLine());
        Scanner scn = new Scanner(System.in);
        while (true) {
        	String input = scn.nextLine();
        	System.out.println("Eingabe: "+" length: "+input.length()+" "+input);
        	if (input.equals("ende") | input.length() == 0) {
        		out.flush();
        		scn.close();
        		in.close();
        		out.close();
        		break;
        	} else {
        		out.write(input);
        		if (input != null) out.newLine();
        		out.flush();
        		String recv = in.readLine();
        		System.out.println(recv);
        	}
        } // end while
        
        System.out.println("Ende Echo-Tester");
        client.close();
	}

}
