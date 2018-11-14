import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;

public class ClienteUDP{
    public static void main(String[] args) throws IOException{
        DatagramSocket clientSocket = new DatagramSocket();
        //Criando o socket UDP, ouvindo numa porta random
        InetAddress IPServer = InetAddress.getByName("localhost");
        byte[] sendData;
        sendData = ("1").getBytes();
        //Definindo dados que vão ser enviados
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000);
        //Definindo os dados que vão ser enviados para o cliente e definindo para qual cliente vai enviar
        long tempoAntigo = System.nanoTime();
        clientSocket.send(sendPacket);
        //Enviando dados para o cliente
        byte[] receiveData = new byte[1];
        DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
        //Definindo onde os dados recebidos do cliente vão ser armazenados
        clientSocket.receive(receivePacket);
        System.out.println(("RTT: " + ((System.nanoTime() - tempoAntigo)/1000)));
        //Calculando RTT e printando
        clientSocket.close();
        //Fechando o socket
    }
}
