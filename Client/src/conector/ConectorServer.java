package conector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConectorServer {

    public String enviarRequest(String request) throws IOException, ClassNotFoundException{
        try {
            //get the localhost IP address, if server is running on some other IP, you need to use that
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream envio = null;
            ObjectInputStream recebido = null;


            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);

            //write to socket using ObjectOutputStream
            envio = new ObjectOutputStream(socket.getOutputStream());

            envio.writeObject(request);

            //read the server response message
            recebido = new ObjectInputStream(socket.getInputStream());
            String retorno = (String) recebido.readObject();

            //close resources
            recebido.close();
            envio.close();
            return retorno;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "false";
    }
}