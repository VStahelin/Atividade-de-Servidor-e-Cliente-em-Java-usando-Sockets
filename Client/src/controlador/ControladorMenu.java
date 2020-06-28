package controlador;

import conector.ConectorServer;
import modelos.Picole;

public class ControladorMenu {
    ConectorServer conectorServer = new ConectorServer();

    public boolean inserir(String acao, Picole picole){

        String complemento;
        if(acao.toLowerCase().equals("inserir")){
            //Modelo de formatacao da mensagem insert: insert##id:sabor:preco:marca:validade:peso##empyt:empyt
            acao = "insert";
            complemento = "##empyt:empyt";
        } else {
            //Modelo de formatacao da mensagem imput: update##id:sabor:preco:marca:validade:peso##id:id
            complemento = "##id:" + picole.getId();
            acao = "update";
        }
        String request = acao + "##" + picole.softStringLogica() + complemento;
        System.out.println(request);
        try {
            if (conectorServer.enviarRequest(request)){
                System.out.println("Deu certo");
            } else {
                System.out.println("Deu erro");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
