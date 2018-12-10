import java.util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Emissor {
	private int maxTamanhoJanela, timeout, portaEmissor, portaReceptor;
	private LinkedList<Pacote> listaPacJanela;
	int tamanhoPacote = 1024;
	private int janelaACKs[];
	
	public Emissor(int mtj, int to, int pE, int pR) {
		this.maxTamanhoJanela = mtj;
		this.timeout = to;
		this.portaEmissor = pE;
		this.portaReceptor = pR;
		Socket = new DatagramSocket(portaReceptor);
		listaPacJanela = new LinkedList<Pacote>();
		janelaACKs[] = new int[maxTamanhoJanela];
		
		/* Construtor padrão contendo informações  
		 * necessárias para envio:
		 *  Tamanho da janela
		 *  Timeout (para re-envio)
		 *  Porta do emissor
		 *  Porta do receptor 
		 *  Socket padrão
		 *  Lista contendo pacotes para envio
		 *  Array contendo ACKs dos pacotes enviados
		 */
	}	
}
