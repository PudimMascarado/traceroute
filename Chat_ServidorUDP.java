import java.io.Scanner;
import java.net.InetAddress;
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.util.Timer;

public class Lista{
  int IP;
  int Porta;
  Lista proximo;
}

public class Chat_ServidorUDP{
  public static void main (String[] args) throws IOException {
    try {
      DatagramSocket serverSocket = new DatagramSocket (5000);
      byte[] receiveData = new byte[100];
      byte[] mandaDados;
      InetAddress ClienteIP = null;
      System.out.println("Aguardando clientes...");
      
      int contadorClientes = 0;
      
      int a = 0;
      
      String mensagemCliente = "";
      
      String Clientes[] = new String[100];
      
      while(true && !mensagemCliente.equals("Sair")){
        DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
        
        serverSocket.receive(receivePacket);
        
        String recebido = new String (receivePacket.getData(), 0, receivePacket.getLength());
        
        ClienteIP = receivePacket.getAddress();
        
        portaUDP = receivePacket.getPort();
        
        String ClientePorta = "" + ClienteIP + Porta;
        

      }
    } catch(Exception e) {
       System.out.println("Macacos me mordam! Bem, isso me deixou envergonhado: " + e);
    }
  }
}
