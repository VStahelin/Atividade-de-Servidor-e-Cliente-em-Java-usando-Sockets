package controlador;

import dao.PicoleDao;
import modelos.Pedido;
import modelos.Picole;
import tratadores.TratadorDePedido;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDeDadosRecebidos {
    PicoleDao picoleDao = new PicoleDao();
    TratadorDePedido tratadorDePedido = new TratadorDePedido();
    public String trataPedido(String mensagem){
        String flag = "false";
        Pedido pedido = new Pedido(mensagem);
        Map<String,String> dicionarioComplemento = tratadorDePedido.tradarComplemento(pedido.getComplemento());

        switch (pedido.getAcao()){
            case "createTable":
                //Modelo de formatacao da mensagem createTable: crateTeable##empyt##empty
                picoleDao.createTable();
                break;

            case "deleteTable":
                //Modelo de formatacao da mensagem deleteTable: deleteTable##empyt##empty

                picoleDao.createTable();
                break;

            case "insert":
                //Modelo de formatacao da mensagem insert: insert##id:sabor:preco:marca:validade:peso:isDeleted##id:xx
                try {
                    if (picoleDao.inset(tratadorDePedido.tratarMensagem(pedido.getPedido()))){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "updade":
                //Modelo de formatacao da mensagem imput: update##id:sabor:preco:marca:validade:peso:isDeleted##id:xx
                try {
                    if (picoleDao.updade(tratadorDePedido.tratarMensagem(pedido.getPedido()),Integer.parseInt(dicionarioComplemento.get("id")))){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "softSelect":
                //Modelo de formatacao da mensagem softSelect: softSelect##id:sabor:preco:marca:validade:peso:isDeleted##id:xx
                try {
                    List<Picole> picoles = picoleDao.softSelect();
                    flag = tratadorDePedido.trataSoftSeletcReturn(picoles);
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;
        }
        return flag;
    }
}
