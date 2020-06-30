import controlador.ControladorDeDadosRecebidos;
import dao.PicoleDao;
import telas.Server;
import tratadores.TratadorDePedido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Main {

    private static TratadorDePedido tratadorDePedido = new TratadorDePedido();

    private static ServerSocket server; //Instanciando o soquete
    private static int port = 9876; //Definindo uma porta para o servidor

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);  // Criando soquete
        Server tela = new Server();
        tela.run();
        String db = "";
        try {
            new PicoleDao().createTable();
            db = "Conectado";
        } catch (Exception e){
            db = "Erro de conexao";
        }
        tela.addRow(pegaHora(),"Conect BD", db);

        //DEIXANDO O SERVIDOR EM LOOP PARA FICAR ONLINE
        while(true){

            System.out.println("Esperando as requisicoes");

            Socket socket = server.accept(); //Caso tenha uma requisicao ele aceita

            ObjectInputStream recebido = new ObjectInputStream(socket.getInputStream()); //Instacia a mensagem que foi recebida
            ObjectOutputStream paraEnviar = new ObjectOutputStream(socket.getOutputStream()); //Instacia a mensagem que vai ser enviada

            try {
                Map<String,String> retorno = new ControladorDeDadosRecebidos().trataPedido((String) recebido.readObject()); //Trata o pedido e retorna uma flag: true, false, exit
                String flag = retorno.get("retorno");
                if(flag.equals("true")){
                    tela.addRow(pegaHora(),retorno.get("acao"),"Sim");
                    if (retorno.containsKey("data")){
                        paraEnviar.writeObject(retorno.get("data"));
                    } else {
                        paraEnviar.writeObject("true");
                    }
                } else if(flag.equals("false")) {
                    tela.addRow(pegaHora(),retorno.get("acao"),retorno.get("data"));
                    System.out.println("Erro durante o processo");
                    paraEnviar.writeObject("false");
                }else if (flag.equals("exit")){
                    recebido.close();
                    paraEnviar.close();
                    socket.close();
                    server.close();
                    break;
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

    private static String pegaHora(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}