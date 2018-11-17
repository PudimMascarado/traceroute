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
import java.util.*;

public class ServidorUDP{
    public static void main (String args[]) throws IOException{
       try {
            DatagramSocket serverSocket = new DatagramSocket (5000);
            byte[] receiveData = new byte[100];
            byte[] mandaDados;
            InetAddress ClienteIP = null;
            int portaUDP;

            System.out.println("Aguardando cliente...");
            long tempoAntigo = System.currentTimeMillis();

            boolean encerrarConexao = false;
            
            while(true && encerrarConexao == false){
                DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
                //Definindo onde os dados recebidos do cliente vão ser armazenados
                
                serverSocket.receive(receivePacket);
                String recebido = new String(receivePacket.getData(), 0, receivePacket.getLength());
                
                ClienteIP = receivePacket.getAddress();
                //Pegando o IP do pacote que chegou
                
                portaUDP = receivePacket.getPort();
                //Pegando a porta do pacote que chegou
                
                System.out.print("Foi recebido o texto: ");
                System.out.print(recebido);
                System.out.println();
                
                mandaDados = ("").getBytes();
                
                if (recebido.toLowerCase().equals("oi")){
                	mandaDados = ("Olá.").getBytes();
                } else if (recebido.toLowerCase().equals("tchau") || recebido.toLowerCase().equals("xau")|| recebido.toLowerCase().equals("adeus")) {
                	mandaDados = ("Até a próxima.").getBytes();
                } else if (recebido.toLowerCase().equals("bom dia")) {
                	mandaDados = ("Bom dia.").getBytes();
                } else if (recebido.toLowerCase().equals("boa tarde")) {
                	mandaDados = ("Boa tarde.").getBytes();
                } else if (recebido.toLowerCase().equals("boa noite")) {
                	mandaDados = ("Boa noite.").getBytes();
                } else if (recebido.toLowerCase().equals("qual seu zap?")) {
                	mandaDados = ("Só funciono em Telegram.").getBytes();
                } else if (recebido.toLowerCase().equals("encerrar conexão")) {
                	mandaDados = ("Encerrando conexão...").getBytes();
                	encerrarConexao = true;
                } else {
                	mandaDados = ("Não entendi, tente outro comando.").getBytes();
                }
                
                DatagramPacket sendPacket = new DatagramPacket(mandaDados, mandaDados.length, ClienteIP, portaUDP);
                //Definindo os dados que vão ser enviados para o cliente e definindo para qual cliente vai enviar
                
                serverSocket.send(sendPacket);   
                
                if (encerrarConexao == true) {
                	break;
                }
            }
        } catch(Exception e) {
            System.out.println("Macacos me mordam! Bem, isso me deixou envergonhado: " + e);
        }
    }
}
