import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream envio = null;
        ObjectInputStream recebido = null;


        //establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);

        //write to socket using ObjectOutputStream
        envio = new ObjectOutputStream(socket.getOutputStream());

        // Modelo de mensgagem: id:sabor:preco:marca:validade:peso:isDeleted
        String picole = "666:limao:2,50:kibom:21/07/2020:30g:false";

        envio.writeObject(picole);

        //read the server response message
        recebido = new ObjectInputStream(socket.getInputStream());
        String message = (String) recebido.readObject();
        System.out.println("Message: " + message);

        //close resources
        recebido.close();
        envio.close();

    }
}