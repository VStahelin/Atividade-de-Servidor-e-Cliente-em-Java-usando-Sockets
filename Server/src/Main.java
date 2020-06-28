import controlador.ControladorDeDadosRecebidos;
import dao.PicoleDao;
import modelos.Picole;
import tratadores.TratadorDePedido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static TratadorDePedido tratadorDePedido = new TratadorDePedido();

    private static ServerSocket server; //Instanciando o soquete
    private static int port = 9876; //Definindo uma porta para o servidor

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        new PicoleDao().createTable();
        server = new ServerSocket(port);  // Criando soquete

        //DEIXANDO O SERVIDOR EM LOOP PARA FICAR ONLINE
        while(true){

            System.out.println("Esperando as requisicoes");

            Socket socket = server.accept(); //Caso tenha uma requisicao ele aceita

            ObjectInputStream recebido = new ObjectInputStream(socket.getInputStream()); //Instacia a mensagem que foi recebida
            ObjectOutputStream paraEnviar = new ObjectOutputStream(socket.getOutputStream()); //Instacia a mensagem que vai ser enviada

            try {
                String flag = new ControladorDeDadosRecebidos().trataPedido((String) recebido.readObject()); //Trata o pedido e retorna uma flag: true, false, exit
                if(flag.equals("true")){
                    paraEnviar.writeObject("true");
                } else if(flag.equals("false")) {
                    System.out.println("Erro durante o processo");
                    paraEnviar.writeObject("false");
                }else if (flag.equals("exit")){
                    recebido.close();
                    paraEnviar.close();
                    socket.close();
                    server.close();
                    break;
                } else {
                    paraEnviar.writeObject(flag);
                }
            }catch (Exception e){
                paraEnviar.writeObject("Erro de execucao: " + e.toString());
            }

            //close resources
            recebido.close();
            paraEnviar.close();
            socket.close();
        }
    }

}