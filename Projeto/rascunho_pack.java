import java.util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class pack {

	private int seq;
	private byte data[];
	long sendtime = System.currentTimeMillis();
	private String origemIP, destinyIP;

	public pack(int seq, byte[] mtj, String origemIP, String destinyIP){
		this.setSeq(seq);
		this.data = mtj;
		this.origemIP=origemIP;
		this.destinyIP=destinyIP;
	}

		public int getSeq() {
			return seq;
		}
	
		public void setSeq(int seq) {
			this.seq = seq;
		}

}
