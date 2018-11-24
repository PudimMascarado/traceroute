import java.io.*;   // Para lidar com inputs e outputs
import java.net.*;  // Para Sockets e Sockets Server
import java.util.*; // Para Scanner
import java.lang.*; // Para exibir mensagens corretamente 
import java.time.*; // Para exibir hora

class ServidorChat {
	public static void main(String[] args) {
		String clienteUm = "1", clienteDois = "2";
		boolean mensagemNaoVazia;
		int portaServidor = 8787;
		String usuario1Porta = "", usuario2Porta = "", mensagemEncaminhada;

		try {
			ServerSocket conexaoServidor = new ServerSocket(portaServidor);
			
			while(true) {
				Socket servidor = conexaoServidor.accept(); 
				System.out.println("Conexão iniciada com sucesso: ");
				// Cliente se identificou como cliente 1 ou 2 de maneira corretamente

				
				mensagemNaoVazia=false;
				mensagemEncaminhada="";
				while (!mensagemNaoVazia) {
					DataInputStream inputTexto = new DataInputStream(servidor.getInputStream()); 
					DataOutputStream outputTexto = new DataOutputStream(servidor.getOutputStream()); 

					mensagemEncaminhada = inputTexto.readUTF();
					
					// Qual cliente se conectou 
					
					System.out.println(mensagemEncaminhada);

					
					if (mensagemEncaminhada.equals("1")) {
						mensagemEncaminhada = "2";
						usuario1Porta = "1234";
						usuario2Porta = "5678";
					} else {
						mensagemEncaminhada = "1";
						usuario1Porta = "5678";
						usuario2Porta = "1234";
					} 
                    
					outputTexto.writeUTF(mensagemEncaminhada);
					outputTexto.writeUTF(usuario1Porta); // Destino
					outputTexto.writeUTF(usuario2Porta); // Você
					
					if (mensagemEncaminhada != null) {
						 mensagemNaoVazia=true; 
						 //Se a mensagem foi recebida e não está vazia, recomece
					}
					
					if (mensagemEncaminhada.toLowerCase().equals("encerrar conexão")) {
						System.out.println("Ops, parece que alguém optou por encerrar a conexão");
						break;
					}
					
				}
			}
		} catch (Exception e) {
			System.out.print("Isso me deixou envergonhado: Erro no servidor número ");
			System.out.println(e);
		}
	}
}
