package Client_Servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by Andreu on 18/04/2017.
 */
public class SocketClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 6666;

    //Des del meu PC no puc obrir un altre intellij per fer la conexió client/servidor, deixo el codi aquí
    //Crea un socket(connexio) al extrem del client que intenta conectar-se al servidor localhost i enviar un missatge
    public static void connectaClient(){
        try {
            System.out.println("[CLIENT] - Petició de connexió...");
            Socket e = new Socket("localhost", 6666);
            DataOutputStream doStream = new DataOutputStream(e.getOutputStream());
            DataInputStream diStream = new DataInputStream(e.getInputStream());
            String respostaServer = diStream.readUTF();
            System.out.println("[CLIENT] - Resposta del servidor = " + respostaServer);
            doStream.writeChars("Conexió establerta");
            e.close();
        } catch (UnknownHostException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }
}
