package Client_Servidor;
import java.io.Serializable;

//Aquestes son les dades que per exemple un usuari pasaria al Servidor. La implementació Serializable permet passar
//objectes i classes

public class ExempleUsuari implements Serializable {
    private String login;
    private String mail;
    private String password;

    public ExempleUsuari(String login, String mail, String password) {
        this.login = login;
        this.mail = mail;
        this.password = password;
    }
}