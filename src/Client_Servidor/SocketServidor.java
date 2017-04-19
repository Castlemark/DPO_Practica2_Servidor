package Client_Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {


    //Fa la mateixa funcio que el mètode conectaClient pero per part del Servidor
    public static void connectaServidor(){
        try {
            ServerSocket Servidor = new ServerSocket(6666);
            System.out.println("[SERVER] - Esperant peticions...");
            Socket sClient = Servidor.accept();
            System.out.println("[SERVER] - Petició rebuda i acceptada...");
            DataOutputStream doStream = new DataOutputStream(sClient.getOutputStream());
            DataInputStream diStream = new DataInputStream(sClient.getInputStream());
            String missatge = diStream.readLine();
            System.out.println("[SERVER] - Estat de la conexió:" + missatge);
            sClient.close();
            Servidor.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
}