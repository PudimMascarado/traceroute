import java.io.Scanner;
import java.net.InetAddress;
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.util.Timer;

List <int, int> ClientePorta = new List<int, int>;

public class boolean ThreadClientes(int ClienteUDP, int portaUDP, int contadorClientes){
  new Thread(){
    @Override
    public void receberClientes(){
      if (!ClientePorta.include(ClienteUDP, portaUDP)){
        System.out.print("Cliente novo registrado: ");
        System.out.print(contadorClientes);         
        System.out.println();
        contadorClientes++;
        return True;
      } else {
        return False;
      }
    }
  }.start();
}

public class Chat_ServidorUDP{
  public static void main (String[] args) throws IOException {
    try {
      
      DatagramSocket serverSocket = new DatagramSocket (5000);
      new Thread(){
        @Override
        public void receberClientes(){
          List<int> Portas = 
        }
      }.start();
      
      byte[] receiveData = new byte[100];
      byte[] mandaDados;
      InetAddress ClienteIP = null;
      System.out.println("Aguardando clientes...");
      
      int contadorClientes = 0;
      
      int a = 0;
      
      String mensagemCliente = "";
      
      boolean jaEhCliente = false;
      
      String Clientes[] = new String[100];
      
      while(true && !mensagemCliente.equals("Sair")){
        DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
        
        serverSocket.receive(receivePacket);
        
        String recebido = new String (receivePacket.getData(), 0, receivePacket.getLength());
        
        ClienteIP = receivePacket.getAddress();
        portaUDP = receivePacket.getPort();
        
        DatagramPacket sendPacket = null;
        
        jaEhCliente = ThreadClientes(ClienteIP, portaUDP, contadorClientes);
        
        if (!jaEhCliente){
           String informacao = "Servidor: Olá novo cliente";
           informacao = informacao.getBytes();
           sendPacket = new DatagramPacket (informacao, informacao.length)
        }
        
        String ClientePorta = "" + ClienteIP + Porta;
        

      }
    } catch(Exception e) {
       System.out.println("Macacos me mordam! Bem, isso me deixou envergonhado: " + e);
    }
  }
}
