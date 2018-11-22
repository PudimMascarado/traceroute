import java.util.*;
import java.net.*;
import java.io.*;

int contador1, usuarios;

public class difusaoMensagem extends Thread {
    void enviar(String paraTodos, int quantosTem){
        for (contador1 = 0; contador1 < quantosTem; contador1++){

        } 
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
                // Funciona pra 2, então eu "replico" pra 100 usando 2 threads como base
                for (contador1 = 0; contador1 < usuarios; contador1++){
                    if (usuarios % 2 == 0){
                        Thread a = new DataInputStream(socket[contador].getIntputStream());
                        Thread b = new DataInputStream(socket[contador+1].getIntputStream());
                        a.start(); b.start();
                    } else {
                        Thread a = new DataInputStream(socket[contador].getIntputStream());
                        a.start();
                        if (contador < usuarios){
                            Thread b = DataInputStream(socket[contador+1].getIntputStream());
                            b.start();
                        }
                    }
                }
            }
        }
    }
}
