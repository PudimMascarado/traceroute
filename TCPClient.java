import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
	public static void main (String[] args) {
		
		String address = "localhost"; int port = 2020;
		
	    try {
	        Socket socket = new Socket (address, port);
	        
	        Scanner in = new Scanner (System.in);
	    	String texto;
	    	
	    	DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
	    	
	    	while(true) {
		    	texto = in.nextLine();
		    	saida.writeUTF(texto);
	    	}
	    	
	    } catch (ConnectException e){
	        System.out.println("Não foi possível chegar ao destino");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
