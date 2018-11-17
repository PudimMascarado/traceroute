import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP{
	public static void main(String[] args) throws IOException {
		try { 
			while(true) {
				Scanner in = new Scanner(System.in);
				
				String entrada = in.nextLine();
				
				DatagramSocket clientSocket = new DatagramSocket();
				//Criando o socket UDP, ouvindo numa porta random
				
				InetAddress IPServer = InetAddress.getByName("localhost");
				byte[] sendData;
				sendData = (entrada).getBytes();
				//Definindo dados que vão ser enviados
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000);
				//Definindo os dados que vão ser enviados para o cliente e definindo para qual cliente vai enviar
				
				long tempoAntigo = System.nanoTime();
				clientSocket.send(sendPacket);
				//Enviando dados para o cliente
				
				byte[] receiveData = new byte[100];
				DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
				//Definindo onde os dados recebidos do cliente vão ser armazenados
				
				clientSocket.receive(receivePacket);
				long tempoRecebido = System.nanoTime();
                		String recebidoServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
				
				System.out.println(recebidoServer);
				System.out.printf("Enviei no tempo: %d  \nRecebi no tempo: %d \nlogo:              ", tempoAntigo,tempoRecebido);
				System.out.println(("RTT: " + ((tempoRecebido - tempoAntigo)/1000)));
				//Calculando RTT e printando
			}
		} catch (Exception e) {
			System.out.println("Problema no Cliente, chefe!");
		}
	}
}
