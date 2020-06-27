package controlador;

import dao.PicoleDao;
import modelos.Pedido;
import tratadores.TratadorDePedido;

import java.util.HashMap;
import java.util.Map;

public class ControladorDeDadosRecebidos {
    PicoleDao picoleDao = new PicoleDao();
    TratadorDePedido tratadorDePedido = new TratadorDePedido();
    public String trataPedido(String mensagem){
        String flag = "false";
        Pedido pedido = new Pedido(mensagem);

        switch (pedido.getAcao()){
            case "createTable":
                picoleDao.createTable();
                break;

            case "deleteTable":
                picoleDao.createTable();
                break;

            case "insert":
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
                try {
                    Map<String,String> dicionarioComplemento = tratadorDePedido.tradarComplemento(pedido.getComplemento());
                    if (picoleDao.updade(tratadorDePedido.tratarMensagem(pedido.getPedido()),Integer.parseInt(dicionarioComplemento.get("id")))){
                        flag = "true";
                    } else {
                        flag = "false";
                    }
                } catch (Exception e){
                    flag = "false";
                }

                break;
        }
        return flag;
    }
}
