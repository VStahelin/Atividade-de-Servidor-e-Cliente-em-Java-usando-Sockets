package controlador;

import dao.PicoleDao;
import modelos.Pedido;
import modelos.Picole;
import tratadores.TratadorDePedido;

import java.util.List;
import java.util.Map;

public class ControladorDeDadosRecebidos {
    PicoleDao picoleDao = new PicoleDao();
    TratadorDePedido tratadorDePedido = new TratadorDePedido();
    public String trataPedido(String mensagem){
        String retorno = "false";
        Pedido pedido = new Pedido(mensagem);
        Map<String,String> dicionarioComplemento = tratadorDePedido.tradarComplemento(pedido.getComplemento());

        switch (pedido.getAcao()){
            case "createTable":
                //Modelo de formatacao da mensagem createTable: crateTeable##empyt##empty:empty
                try {
                    if (picoleDao.createTable()){
                        retorno = "true";
                    } else {
                        retorno = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "deleteTable":
                //Modelo de formatacao da mensagem deleteTable: deleteTable##empyt##empty:empty
                try {
                    if (picoleDao.deleteTable()){
                        retorno = "true";
                    } else {
                        retorno = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "insert":
                //Modelo de formatacao da mensagem insert: insert##id:sabor:preco:marca:validade:peso:isDeleted##empty:empty
                try {
                    if (picoleDao.inset(tratadorDePedido.softTratarMensagem(pedido.getPedido()))){
                        retorno = "true";
                    } else {
                        retorno = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "update":
                //Modelo de formatacao da mensagem imput: update##id:sabor:preco:marca:validade:peso:isDeleted##id:xx
                try {
                    if (picoleDao.updade(tratadorDePedido.softTratarMensagem(pedido.getPedido()),Integer.parseInt(dicionarioComplemento.get("id")))){
                        retorno = "true";
                    } else {
                        retorno = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "softSelect":
                //Modelo de formatacao da mensagem softSelect: softSelect##empyt##empty:empty
                try {
                    List<Picole> picoles = picoleDao.softSelect();
                    retorno = tratadorDePedido.trataSoftSeletcReturn(picoles);
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "select":
                //Modelo de formatacao da mensagem select: select##empyt##empty:empty
                try {
                    List<Picole> picoles = picoleDao.select();
                    retorno = tratadorDePedido.trataSeletcReturn(picoles);
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "selectById":
                //Modelo de formatacao da mensagem select: selectById##empyt##id:xx
                try {
                    Picole picole = picoleDao.selectById(Integer.parseInt(dicionarioComplemento.get("id")));
                    if (picole == null){
                        retorno = "false";
                    } else {
                        retorno = picole.softStringLogica();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "delete":
                //Modelo de formatacao da mensagem select: delete##empyt##id:xx
                try {
                    if (picoleDao.softDelete(Integer.parseInt(dicionarioComplemento.get("id")))){
                        retorno = "true";
                    } else {
                        retorno = "false";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;

            case "rows":
                //Modelo de formatacao da mensagem select: rows##empyt##empty:empty
                try {
                    retorno = String.valueOf(picoleDao.rows());
                } catch (Exception e){
                    e.printStackTrace();
                    retorno = "false";
                }
                break;
        }
        return retorno;
    }
}
