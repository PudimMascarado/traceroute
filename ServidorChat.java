import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.time.*;

class ServidorChat {
    public static void main(String[] args) throws IOException {
        String usuario1, usuario2;

        int portaDefault = 1001;
        String mensagem, usuario1Porta = "", usuario2Porta = "";
        ServerSocket servidor = new ServerSocket(portaDefault);
        
        try {
            Socket aceitarConexao = servidor.accept();
            while(true){
                System.out.println("Conexão iniciada com sucesso.");
                int porcentagemEnvio = 0;
                mensagem = "a";
                while(!mensagem.equals("")){
                    DataInputStream inputTexto = new DataInputStream(aceitarConexao.getInputStream());
                    DataOutputStream outputTexto = new DataOutputStream(aceitarConexao.getOutputStream());

                    mensagem = inputTexto.readUTF();

                    outputTexto.writeUTF(mensagem);
                    outputTexto.writeUTF("1010"); // Destino
                    outputTexto.writeUTF("1011"); // Você

                    if(!mensagem.equals("a")){
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Isso me deixou envergonhado: Erro no servidor número ");
        }
    }
}
