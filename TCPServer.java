import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) {
		int port = 2020; String address = "localhost";

		try {
			ServerSocket tempsocket = new ServerSocket(port);
			//É a ponte que liga ambos os sockets
			
			System.out.println("Aguardando cliente...");
			String saidinha;

			while (true) {
				Socket socket = tempsocket.accept();
				System.out.println("Conexão estabelecida.");
				
				DataInputStream entrada = new DataInputStream(socket.getInputStream());

				long tempo = System.currentTimeMillis(); long tempomax = 10000;
				
				while (System.currentTimeMillis() - tempo < 10000) {
					saidinha = entrada.readUTF();
					System.out.println(saidinha);
				}

				System.out.println("10 segundos se passaram, conexão com servidor encerrando.");
																				socket.close();
			}

		} catch (BindException e) {
			System.out.println("Endereço em uso!");
		} catch (Exception e) {
			System.out.println("Erro: "+e);
		}	
	}		
}
