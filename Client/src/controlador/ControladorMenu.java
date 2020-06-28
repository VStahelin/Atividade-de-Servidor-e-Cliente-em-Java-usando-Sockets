package controlador;

import conector.ConectorServer;
import modelos.Picole;
import tratadores.TratadorDeRetorno;

import javax.swing.*;
import java.util.List;

public class ControladorMenu {
    ConectorServer conectorServer = new ConectorServer();
    TratadorDeRetorno tratadorDeRetorno = new TratadorDeRetorno();
    //TODO: ATUALIZAR EVENTOS DE ERRO

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
            String retorno = conectorServer.enviarRequest(request);
            if (retorno.equals("true")){
                JOptionPane.showMessageDialog(null,"Cadastrado");
            } else {
                JOptionPane.showMessageDialog(null,"Deu earro");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Picole> softSelect(){
        //Modelo de formatacao da mensagem softSelect: softSelect##empyt##empty:empty
        String request = "softSelect##empyt##empty:empty";
        try {
            return tratadorDeRetorno.getSoftListaDePicoles(conectorServer.enviarRequest(request));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Picole> select(){
        //Modelo de formatacao da mensagem softSelect: softSelect##empyt##empty:empty
        String request = "select##empyt##empty:empty";
        try {
            return tratadorDeRetorno.getSoftListaDePicoles(conectorServer.enviarRequest(request));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Picole selectById(int id){
        //Modelo de formatacao da mensagem select: selectById##empyt##id:xx
        String request = "selectById##empyt##id:"+ id;
        try {
            return tratadorDeRetorno.tratarsSoftMensagem(conectorServer.enviarRequest(request));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
