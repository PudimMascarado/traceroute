import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;

public class Credito extends Thread {
    private Conta conta;
    private double val;

    public Credito (Conta c, double v){
        conta = c;
        val = v;
    }

    public void run(){
        conta.creditar(val);
        System.out.printf("Valor creditado com sucesso.\n Forte abraço!");
    }
}