import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;

public class NuBankBolso2019 {
    public static void main(String[] args){
        int portaUDP = 3030;
        Thread a = new Credito();
        Thread b = new Debito();
        a.start();
        b.start();

        int ExecuteDezVezes = 10;

        try {

        } catch (Exception e){
            System.out.println("Bem, isso me deixou envergonhado: " + e);
        }
    }
}