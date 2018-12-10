import java.util.Scanner;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {
	public static void Main(String args[]){
		Scanner in = new Scanner(System.in);
		System.out.printf("Você é o emissor (digite 1) ou receptor (digite 2): \n");
		int EmissorOuReceptor = in.nextInt();
		
		if (EmissorOuReceptor == 1) {
			/* É um emissor, devemos pegar um arquivo
			 * compactar o arquivo, transformá-lo em
			 * pacote (Datagram Packet) e fazer o envio
			 * */
		} else if (EmissorOuReceptor == 2){
			/* É um receptor, devemos receber fragmentos
			 * montar o arquivo, descompactá-lo
			 * */
		} else {
			System.out.println("Parece que você colocou um número invalido, encerrando programa!");
		}
	}
}
