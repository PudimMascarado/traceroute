import java.util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Receptor {
	private int maxTamanhoJanela, timeout, portaEmissor, portaReceptor;
	int tamanhoPacote = 1024, ACK = 1, NAK = 0;
	private int janelaACKs[];
	
	public Receptor(int pE, int pR){
		this.portaEmissor = pE;
		this.portaReceptor = pR;
		Socket = new DatagramSocket(portaReceptor);
    /* Construtor padrão contendo informações
		 * necessárias para receber
		 *  Porta do emissor
		 *  Porta do receptor
		 *  Socket padrão
		 *  Tamanho máximo da janela
		 * */
	}
}
