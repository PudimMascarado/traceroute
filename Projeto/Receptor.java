import java.util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Receptor {
	private int maxTamanhoJanela, timeout, portaEmissor, portaReceptor, primeiroJanela;
	int tamanhoPacote = 1024, ACK = 1, NAK = 0;
	private int janelaACKs[];
	
	public Receptor(int pE, int pR, int mtj){
		this.portaEmissor = pE;
		this.portaReceptor = pR;
		Socket = new DatagramSocket(portaReceptor);
		this.maxTamanhoJanela = mtj;
		/* Construtor padrão contendo informações
		 * necessárias para receber
		 *  Porta do emissor
		 *  Porta do receptor
		 *  Socket padrão
		 *  Tamanho máximo da janela
		 * */
	}
	
	private boolean confirmarACK(int numeroPac){
		if(primeiroJanela <= numeroPac){
			/* O pacote o qual estamos 
			 * verificando ACK deve
			 * estar depois do primeiro
			 * ou no máximo ser o 
			 * primeiro da janela, se
			 * o primeiroJanela for depois 
			 * do nosso pacote então deu ruim
			 * */
			if(numeroPac - ultimoJanela < maxTamanhoJanela){
				janelaACKs[numeroPac - ultimoJanela] = 1;
				return true;
			}
		}
		return false;
		// Caso se tiver dado errado: 
		// O primeiroJanela > numeroPac
	}
	
	private void avancarJanela(){
		while(true) {
			if(janelaACKs[0] == 1) {
				// Se o primeiro valor da janela foi recebido:
				// Esse primeiro vai sendo alterado conforme
				// a janela anda
				
				for(int aJ = 0; aJ < maxTamanhoJanela - 1; aJ++) {
					janelaACKs[aJ] = janelaACKs[aJ] + 1;
					// Avança todo mundo uma posição
				}
				janelaACKs[maxTamanhoJanela - 1] = 0;
				// Último é considerado NAK, não recebemos ele
				
				primeiroJanela++;
				// O valor mais antigo da janela foi confirmado,
				// então avançamos o "gargalo" para frente
			} else {
				break;
				// Se não foi recebido (pode ser até que tenha acabado transmissão)
			}
		}
	}
}
