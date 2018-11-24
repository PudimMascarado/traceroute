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
    // Construtor básico
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
    // Estrutura da thread para execução de enviar mensagens
}

class ReceberMsgs extends Thread {
    private Cliente ele;
    private Cliente eu;

	public ReceberMsgs(Cliente ele, Cliente eu){
        this.ele = ele;
        this.eu = eu;
    }
    // Construtor padrão

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
    // Estrutura da thread para execução de receber mensagens
}

public class ClienteChat {
    public static void main(String args[]){
        int portaDefault = 1001;
        int portaSecundaria = -1;
        int portaTerciaria = -1;
        String usuario = "", destinatario = "", mensagem = "";
        String endereco = "localhost";

        // Precisamos da porta do servidor, de uma para cada cliente, e os nomes dos clientes

        Scanner in = new Scanner(System.in);

        try {
            Socket conectarServer = new Socket(endereco, portaDefault);
            DataOutputStream saidaDados = new DataOutputStream(conectarServer.getOutputStream());
            DataInputStream entradaDados = new DataInputStream(conectarServer.getInputStream());

            System.out.printf("Bem vindoa rede da ARPANet, \nantes de trocar mensagens, \nIDENTIFIQUE-SE, \nvocê é o Cliente: 1 ou 2 ? "); usuario = in.nextLine(); saidaDados.writeUTF(usuario);

            if (usuario.toLowerCase().equals("cliente 1") || usuario.toLowerCase().equals("cliente 2")){
                System.out.printf("Você preencheu de forma correta, \niremos prosseguir com a configuração do chat.\n");
            } else {
                System.out.printf("Ops, você não preencheu de forma correta, não garantimos o funcionamento \n, EXECUTE POR SUA CONTA E RISCO.\n");
            }

            usuario = in.nextLine();
            saidaDados.writeUTF(usuario);

            destinatario = entradaDados.readUTF();

            String temp = entradaDados.readUTF();
            portaSecundaria = Integer.parseInt(temp);
            temp = entradaDados.readUTF();
            portaTerciaria = Integer.parseInt(temp);
            
            // Até aqui recebi detalhes como nome, e porta

            Cliente outro = new Cliente(destinatario, portaSecundaria);
            Cliente eu = new Cliente(usuario, portaTerciaria);
            
            // Crio os clientes utilizando dados

            System.out.println("Estamos efetuando a ligação, em instantes comece a digitar...");

            Thread paraReceber = new ReceberMsgs(eu, outro);
            Thread paraEnviar = new EnvioMsgs(outro);

            // Crio as threads para manter a conexão funcionando

            paraEnviar.start(); paraEnviar.sleep(6543);
            // Inicio as threads e dou um tempo para o usuário abrir os consoles
            paraReceber.start(); paraReceber.sleep(6543);

        } catch (Exception e) {
            System.out.print("Isso me deixou envergonhado: Erro no ClienteChat número ");
            System.out.println(e);
        }
    }
}
