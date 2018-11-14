import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;

public class ServidorUDP{
    public static void main (String args[]) throws IOException{
        try{
            DatagramSocket serverSocket = new DatagramSocket (5000);
            byte[] receiveData = new byte[1];
            byte[] mandaDados;
            InetAddress ClienteIP;
            int portaUDP;

            System.out.println("Aguardando cliente...");
            long tempoAntigo = System.currentTimeMillis();

            while(true){
                DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
                //Definindo onde os dados recebidos do cliente vão ser armazenados
                serverSocket.receive(receivePacket);
                ClienteIP = receivePacket.getAddress();
                //Pegnado o IP do pacote que chegou
                portaUDP = receivePacket.getPort();
                //Pegando a porta do pacote que chegou
                mandaDados = ("2").getBytes();
                //Definindo dados que vão ser enviados
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ClienteIP, portaUDP);
                //Definindo os dados que vão ser enviados para o cliente e definindo para qual cliente vai enviar
                serverSocket.send(sendPacket);
            }
        } catch(Exception e) {
            System.out.println("Bem, isso me deixou envergonhado: " + e);
        }
    }
}