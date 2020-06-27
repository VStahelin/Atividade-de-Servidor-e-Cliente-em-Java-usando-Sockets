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
                try {
                    if (picoleDao.createTable()){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "deleteTable":
                //Modelo de formatacao da mensagem deleteTable: deleteTable##empyt##empty
                try {
                    if (picoleDao.deleteTable()){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "insert":
                //Modelo de formatacao da mensagem insert: insert##id:sabor:preco:marca:validade:peso:isDeleted##empyt
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
                //Modelo de formatacao da mensagem softSelect: softSelect##empyt##id:xx
                try {
                    List<Picole> picoles = picoleDao.softSelect();
                    flag = tratadorDePedido.trataSoftSeletcReturn(picoles);
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "select":
                //Modelo de formatacao da mensagem select: select##empyt##id:xx
                try {
                    List<Picole> picoles = picoleDao.select();
                    flag = tratadorDePedido.trataSeletcReturn(picoles);
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "selectById":
                //Modelo de formatacao da mensagem select: selectById##empyt##id:xx
                try {
                    Picole picole = picoleDao.selectById(Integer.parseInt(dicionarioComplemento.get("id")));
                    flag = picole.softStringLogica();
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "delete":
                //Modelo de formatacao da mensagem select: delete##empyt##id:xx
                try {
                    if (picoleDao.softDelete(Integer.parseInt(dicionarioComplemento.get("id")))){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;

            case "rows":
                //Modelo de formatacao da mensagem select: rows##empyt##empyt
                try {
                    flag = String.valueOf(picoleDao.rows());
                } catch (Exception e){
                    e.printStackTrace();
                    flag = "false";
                }
                break;
        }
        return flag;
    }
}
