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
    public Map<String,String> trataPedido(String mensagem){
        Pedido pedido = new Pedido(mensagem);
        Map<String,String> dicionarioComplemento = tratadorDePedido.tradarComplemento(pedido.getComplemento());
        Map<String,String> retorno = new HashMap<String,String>();
        retorno.put("retorno","falso");
        retorno.put("acao", pedido.getAcao());
        switch (pedido.getAcao()){
            case "createTable":
                //Modelo de formatacao da mensagem createTable: crateTeable##empyt##empty:empty
                try {
                    if (picoleDao.createTable()){
                        retorno.replace("retorno", "true");
                    } else {
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "deleteTable":
                //Modelo de formatacao da mensagem deleteTable: deleteTable##empyt##empty:empty
                try {
                    if (picoleDao.deleteTable()){
                        retorno.replace("retorno", "true");
                    } else {
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "insert":
                //Modelo de formatacao da mensagem insert: insert##id:sabor:preco:marca:validade:peso:isDeleted##empty:empty
                try {
                    if (picoleDao.inset(tratadorDePedido.softTratarMensagem(pedido.getPedido()))){
                        retorno.replace("retorno", "true");
                    } else {
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "update":
                //Modelo de formatacao da mensagem imput: update##id:sabor:preco:marca:validade:peso:isDeleted##id:xx
                try {
                    if (picoleDao.updade(tratadorDePedido.softTratarMensagem(pedido.getPedido()),Integer.parseInt(dicionarioComplemento.get("id")))){
                        retorno.replace("retorno", "true");
                    } else {
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "softSelect":
                //Modelo de formatacao da mensagem softSelect: softSelect##empyt##empty:empty
                try {
                    List<Picole> picoles = picoleDao.softSelect();
                    retorno.put("retorno", "true");
                    retorno.put("data",tratadorDePedido.trataSoftSeletcReturn(picoles));
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "select":
                //Modelo de formatacao da mensagem select: select##empyt##empty:empty
                try {
                    List<Picole> picoles = picoleDao.select();
                    retorno.put("retorno", "true");
                    retorno.put("data",tratadorDePedido.trataSeletcReturn(picoles));
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "selectById":
                //Modelo de formatacao da mensagem select: selectById##empyt##id:xx
                try {
                    Picole picole = picoleDao.selectById(Integer.parseInt(dicionarioComplemento.get("id")));
                    if (picole == null){
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    } else {
                        retorno.replace("retorno", "true");
                        retorno.put("data",picole.softStringLogica());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "delete":
                //Modelo de formatacao da mensagem select: delete##empyt##id:xx
                try {
                    if (picoleDao.softDelete(Integer.parseInt(dicionarioComplemento.get("id")))){
                        retorno.replace("retorno", "true");
                    } else {
                        retorno.replace("retorno", "false");
                        retorno.put("data", "Nao foi possivel executar a acao");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;

            case "rows":
                //Modelo de formatacao da mensagem select: rows##empyt##empty:empty
                try {
                    retorno.replace("retorno", "true");
                    retorno.put("data",String.valueOf(picoleDao.rows()));
                } catch (Exception e){
                    e.printStackTrace();
                    retorno.replace("retorno", "false");
                    retorno.put("data",e.toString());
                }
                break;
        }
        return retorno;
    }
}
