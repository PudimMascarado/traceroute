import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.time.*;

class Cliente {
    public String nomeCliente;
    public int portaCliente;
    public Cliente(String nomeCliente, int portaCliente){
        this.nomeCliente = nomeCliente;
        this.portaCliente = portaCliente;
    }
}

class EnvioMsgs extends Thread {
    private Cliente outro;

    public EnvioMsgs(Cliente outro){
        this.outro = outro;
    }
    
    public void run(){
        try {
            Scanner in = new Scanner (System.in);
            while(true){
                Socket doOutro = new Socket("localhost", outro.portaCliente);
                String inputMensagem = in.nextLine();
                DataOutputStream saidaEnviar = new DataOutputStream(doOutro.getOutputStream());
                saidaEnviar.writeUTF(inputMensagem);
            }
        } catch (Exception e) {
            System.out.print("Isso me deixou envergonhado: Erro no EnviarMsgs número ");
            System.out.println(e);
        }
    }
}

class ReceberMsgs extends Thread {
    private Cliente ele;
    private Cliente eu;

	public ReceberMsgs(Cliente ele, Cliente eu){
        this.ele = ele;
        this.eu = eu;
    }

    public void run(){
        try {
            ServerSocket servidorLocal = new ServerSocket(eu.portaCliente);
            while(true){
                Socket meuSocket = servidorLocal.accept();
                DataInputStream entradaEnviar = new DataInputStream(meuSocket.getInputStream());
                String inputMensagem2 = entradaEnviar.readUTF();
                System.out.print(ele.nomeCliente+" às "); // Printa no padrão: Fulano às HH:MM:SS \n mensagem
                System.out.printf(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()+"\n");
                System.out.printf("> "+inputMensagem2+"\n");
            }
        } catch (Exception e) {
            System.out.print("Isso me deixou envergonhado: Erro em ReceberMsgs número ");
            System.out.println(e);
        }   
    }
}

public class ClienteChat {
    public static void main(String args[]){
        int portaDefault = 1001;
        int portaSecundaria = 1002;
        int portaTerciaria = 1003;
        String usuario = "", destinatario = "", mensagem = "";
        String endereco = "localhost";

        Scanner in = new Scanner (System.in);

        try {
            Socket conectarServer = new Socket(endereco, portaDefault);
            DataOutputStream saidaDados = new DataOutputStream(conectarServer.getOutputStream());
            DataInputStream entradaDados = new DataInputStream(conectarServer.getInputStream());

            System.out.print("Bem vindo, insira seu username: "); usuario = in.nextLine(); saidaDados.writeUTF(usuario);
            System.out.println();

            Cliente outro = new Cliente(destinatario, portaDefault);
            Cliente eu = new Cliente(usuario, portaDefault);

            System.out.print("Estamos aguardando o outro cliente iniciar o Chat...");

            Thread paraReceber = new ReceberMsgs(eu, outro);
            Thread paraEnviar = new EnvioMsgs(outro);

            paraEnviar.start();
            paraReceber.start();

        } catch (Exception e) {
            System.out.print("Isso me deixou envergonhado: Erro no ClienteChat número ");
            System.out.println(e);
        }
    }
}
