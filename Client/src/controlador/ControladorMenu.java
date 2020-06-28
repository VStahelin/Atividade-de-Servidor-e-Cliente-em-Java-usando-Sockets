package controlador;

import conector.ConectorServer;
import modelos.Picole;
import tratadores.TratadorDeRetorno;

import javax.swing.*;
import java.io.IOException;
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

        try {
            String retorno = conectorServer.enviarRequest(request);
            if (retorno.equals("true")){
                JOptionPane.showMessageDialog(null,"Cadastrado");
            } else {
                JOptionPane.showMessageDialog(null,"Id ou dados invalidos");
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
            return tratadorDeRetorno.getListaDePicoles(conectorServer.enviarRequest(request));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Picole selectById(int id){
        //Modelo de formatacao da mensagem select: selectById##empyt##id:xx
        String request = "selectById##empyt##id:"+ id;
        try {
            if (conectorServer.enviarRequest(request).equals("false")){
                JOptionPane.showMessageDialog(null,"Registro inexistente");
                return null;
            } else {
                return tratadorDeRetorno.tratarsSoftMensagem(conectorServer.enviarRequest(request));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String criarTabela(){
        String request = "crateTeable##empyt##empty:empty";
        try {
            if (conectorServer.enviarRequest(request).equals("True")){
                return "Tabela criada com sucesso!";
            } else {
                return "Erro em criar tabela";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        }
    }

    public String getQuantidadeDeRegistros(){
        String request = "rows##empyt##empty:empty";
        try {
            return "A quantidade de resgistros no banco eh de: " + conectorServer.enviarRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        }
    }

    public String deleteById(int id){
        //Modelo de formatacao da mensagem select: delete##empyt##id:xx
        String request = "delete##empyt##id:" + id;
        try {
            if (conectorServer.enviarRequest(request).equals("true")){
                return "Registro deletado com sucesso";
            } else {
                return "Erro em deletar o registro";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        }
    }

    public String deleteTable(){
        String request = "deleteTable##empyt##empty:empty";
        try {
            if (conectorServer.enviarRequest(request).equals("true")){
                return "Tabela deleteda com sucesso!";
            } else {
                return "Erro em deletar tabela";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro: " + e.toString();
        }
    }
}
