import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;

public class Conta {
    public double saldo = 0;

    void creditar(double vc){
        saldo = saldo + vc;
    }

    void debitar(double vd){
        if (saldo > vd){
            saldo = saldo - vd;
        } else {
            System.out.printf("Você não tem dinheiro suficiente, deposite mais dinheiro.\n Tá OK?");
        }
    }
}
