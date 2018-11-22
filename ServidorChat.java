import java.util.*;
import java.net.*;
import java.io.*;

int contador1, usuarios; DataInputStream entrada;

public class Mensagem {
    public String padrao = "";
    public Socket padrao;

    void receber(String padraoMensagem, Socket padraoMensagem){
        DataInputStream DISMensagem = new DataInputStream(padraoMensagem.getIntputStream());
    }

    void enviar(String padraoMensagem, Socket padraoMensagem){
        DataOutputStream DOSMensagem = new DataOutputStream(padraoMensagem.getOutputStream());
    }
}

public class difusaoMensagem extends Thread {
    private Mensagem enviarMensagem;
    public difusaoMensagem(String paraTodos, int quantosTem){
        // Aqui vai ser aquele DataInput blah
    }
    
    public void run(enviarMensagem){
        enviarMensagem.enviar(enviarMensagem);
    }
}


public class captarMensagem extends Thread {
    private Mensagem receberMensagem;
    String captarMensagem(DataInputStream entrada){
        // Aqui vai ser aquele DataInput blah
    }

    public void run(receberMensagem){
        receberMensagem.receber(receberMensagem);
    }
}

public class ServidorChat {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        int portas[] = new int[100]; // Porta de usuários
        String address = "localhost";
        ServetSocket sockets[] = new Socket[100];
        usuarios = 0; 
        try {
            while (true){
                ServerSocket tempsocket = sockets[usuarios];
                Socket socket = tempsocket.accept();
                if(sockets[usuarios] != socket){
                    usuarios++; // Vai preenchendo usuários 
                                // no array de sockets do server
                }

                Mensagem cliente1 = new Mensagem();
                Mensagem cliente2 = new Mensagem();
                Thread a = new captarMensagem(cliente1);
                Thread b = new captarMensagem(cliente2);
                Thread x = new difusaoMensagem(cliente1);
                Thread y = new difusaoMensagem(cliente2);

                a.start(); b.start(); x.start(); y.start();
                }
            }
        }
    }
}
