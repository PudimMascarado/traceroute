import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Servidor extends pack{

	public Servidor(int seq, byte[] mtj, String origemIP, String destinyIP) {
		super(seq, mtj, origemIP, destinyIP);
	}

	//------------------Extende pacote	
	//-----------Recebe pacote, e transforma em pack----------------------
	int toObject(byte[] bytes) throws IOException, ClassNotFoundException{
		pack obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = (pack) ois.readObject();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		return obj.getSeq();
	}
}
