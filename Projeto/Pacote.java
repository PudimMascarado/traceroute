public class Pacote {
	private int numSequencia, tamanhoPacote, reTransmissoes;
	private byte[] dados;
	private boolean ACK; 
	// Serve para identificar no Emissor
	
	public Pacote(int nS, byte[] d) {
		this.numSequencia = nS;
		this.tamanhoPacote = tP;
		this.dados = d;
		this.tamanhoPacote = d.length;
	}
}
