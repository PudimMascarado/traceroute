import java.util.Scanner;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
// Para zipagem:
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

boolean CompactaArquivo(String enderecoArquivo){
	String nomeArquivoDentro = "arquivoZipado";
	
    // Entrada: O endereço do arquivo, o qual usaremos para exportar o arquivo zipado
    try {
        FileInputStream entrada = new FileInputStream(enderecoArquivo);

        ZipOutputStream exportar = new ZipOutputStream(new FileOutputStream(enderecoArquivo));

        // Para nomear o arquivo dentro do arquivo Zip
        exportar.putNextEntry(new ZipEntry(nomeArquivoDentro));

        // Usando buffer pra gravar o arquivo efetivamente (não gravamos de uma vez só)
        byte[] buffer = new byte[1024];

        int contador;

        while(contador = in.read(buffer) > 0){
            exportar.write(buffer, 0, contador);
        } 
        // Vai preenchendo o arquivo enquanto houver buffer

        exportar.close();
        entrada.close();
        return true;
        
    } catch (Exception naoPudeCompactar){
        System.out.println("Não consegui compactar o arquivo por alguma causa!")
        return false;
    }
    // Saída: um boolean dizendo se deu certo ou não
}

public class Main {
	public static void Main(String args[]){
		Scanner in = new Scanner(System.in);
		System.out.printf("Você é o emissor (digite 1) ou receptor (digite 2): \n");
		int EmissorOuReceptor = in.nextInt();
		
		if (EmissorOuReceptor == 1) {
			/* É um emissor, devemos pegar um arquivo
			 * compactar o arquivo, transformá-lo em
			 * pacote (Datagram Packet) e fazer o envio
			 * */
			
			// Trouxe isso do Compacta.java localizado na pasta Projetos:
			String localArquivo;
			System.out.println("Qual o nome do arquivo a ser enviado (ex: arquivo.txt): ");
			localArquivo = in.next();
			Compacta(localArquivo);
		    
		    // Aqui vai o código para enviar o arquivo zipamos:
		    
		}
			
			
		} else if (EmissorOuReceptor == 2){
			/* É um receptor, devemos receber fragmentos
			 * montar o arquivo, descompactá-lo
			 * */
		} else {
			System.out.println("Parece que você colocou um número invalido, encerrando programa!");
		}
	}
}
