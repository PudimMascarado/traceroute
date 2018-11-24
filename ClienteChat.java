import java.io.*;   // Para lidar com inputs e outputs
import java.net.*;  // Para Sockets e Sockets Server
import java.util.*; // Para Scanner
import java.lang.*; // Para exibir mensagens corretamente 
import java.time.*; // Para exibir hora

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

	public ReceberMsgs(Cliente ele, Cliente eu) {
		this.ele = ele;
		this.eu = eu;
	}
	// Construtor padrão

	public void run(){
		try {
			ServerSocket servidorLocal = new ServerSocket(eu.portaCliente);
			while(true) {
				Socket meuSocket = servidorLocal.accept();
				DataInputStream entradaEnviar = new DataInputStream(meuSocket.getInputStream());
				String inputMensagem2 = entradaEnviar.readUTF();
				System.out.print(ele.nomeCliente+" às "); // Printa no padrão: Fulano às HH:MM:SS \n mensagem
				System.out.printf(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()+"\n");
				System.out.printf("> "+inputMensagem2+"\n\n");
			}
		} catch (Exception e) {
			System.out.print("Isso me deixou envergonhado: Erro em ReceberMsgs número ");
			System.out.println(e);
		}   
	}
	// Estrutura da thread para execução de receber mensagens
}

public class ClienteChat {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int portaPadrao = 8787, portaSecundaria = 0, portaTerciaria = 0;
		String usuario, destinatario;

                // Precisamos da porta do servidor, de uma para cada cliente, e os nomes dos clientes
		// Há um pequeno roleplay, em homenagem a ARPANet
		
		try {
			Socket socket = new Socket("localhost", portaPadrao);
			DataOutputStream saidaDados = new DataOutputStream(socket.getOutputStream()); 
			DataInputStream entradaDados = new DataInputStream(socket.getInputStream());

			System.out.printf("Bem vindo a rede da ARPANet, \nantes de trocar mensagens, \nIDENTIFIQUE-SE, \nvocê é o Cliente: 1 ou 2 ? "); 
			usuario = in.nextLine(); 
			saidaDados.writeUTF(usuario);

			if (usuario.toLowerCase().equals("1") || usuario.toLowerCase().equals("2")) {
				System.out.printf("Você preencheu de forma correta, \niremos prosseguir com a configuração do chat.\n");
			} else {
				System.out.printf("Ops, você não preencheu de forma correta, não garantimos o funcionamento \n, EXECUTE POR SUA CONTA E RISCO.\n");
			}

			destinatario = entradaDados.readUTF();
			portaSecundaria = Integer.parseInt(entradaDados.readUTF());
			portaTerciaria = Integer.parseInt(entradaDados.readUTF());

                        // Até aqui recebi detalhes como nome, e porta
			
			Cliente outro = new Cliente(destinatario, portaTerciaria);
			Cliente eu = new Cliente (usuario, portaSecundaria);

			Thread paraReceber = new ReceberMsgs(outro, eu);
			Thread paraEnviar = new EnvioMsgs(outro);

                        // Crio os clientes utilizando dados
			
			System.out.println("Estamos efetuando a ligação, em instantes comece a digitar...");

			paraReceber.start(); paraReceber.sleep(6543);
			// Inicio as threads e dou um tempo 6s 543ms para o usuário abrir os consoles
			paraEnviar.start(); paraEnviar.sleep(6543);

		} catch(Exception e) {
			System.out.print("Isso me deixou envergonhado: Erro no ClienteChat número ");
			System.out.println(e);
		}
	}
}
