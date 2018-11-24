import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.time.*;

class ServidorChat {
    public static void main(String[] args) {
        String usuario1, usuario2;

        int portaDefault = 1000;
        String mensagemInterceptada, usuario1Porta, usuario2Porta;

        try {
            ServerSocket servidor = new ServerSocket(portaDefault);
            
            while(true){
                Socket aceitarConexao = servidor.accept();
                System.out.println("Conexão iniciada com sucesso.");
                mensagemInterceptada = "a";

                while(!mensagemInterceptada.equals("")){
                    DataInputStream inputTexto = new DataInputStream(aceitarConexao.getInputStream());
                    DataOutputStream outputTexto = new DataOutputStream(aceitarConexao.getOutputStream());

                    // Cliente se identificou como cliente 1 ou 2 de maneira corretamente

                    if(mensagemInterceptada.toLowerCase().equals("cliente 1")){
                        mensagemInterceptada = "Cliente 1";
                        usuario1Porta = "1234";
                        usuario2Porta = "5678";
                    } else {
                        mensagemInterceptada = "Cliente 2";
                        usuario1Porta = "5678";
                        usuario2Porta = "1234";
                    }

                    mensagemInterceptada = inputTexto.readUTF();

                    outputTexto.writeUTF(mensagemInterceptada);
                    outputTexto.writeUTF(usuario2Porta); // Destino
                    outputTexto.writeUTF(usuario1Porta); // Você

                    if(mensagemInterceptada.toLowerCase().equals("encerrar conexão")){
                        System.out.printf("Parece que o outro cliente quis encerrar a conexão!\n Adeus, ARPANet desligando!");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Isso me deixou envergonhado: Erro no servidor número ");
        }
    }
}
