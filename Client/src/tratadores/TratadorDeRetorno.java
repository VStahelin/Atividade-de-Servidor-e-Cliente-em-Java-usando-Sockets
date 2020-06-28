package tratadores;

import modelos.Picole;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TratadorDeRetorno {

    public List<Picole> getSoftListaDePicoles(String retorno){
        List<Picole> picoles = new ArrayList<Picole>();
        if (retorno.equals("")){
            JOptionPane.showMessageDialog(null, "Nao ha registros!");
            return null;
        }
        String[] stringPicoles = retorno.split("%%");
        for (String linha : stringPicoles){
            picoles.add(tratarsSoftMensagem(linha));
        }
        return picoles;
    }

    public Picole tratarsSoftMensagem(String mensagem){
        Picole picole = new Picole();

        // Modelo de mensgagem: id:sabor:preco:marca:validade:peso
        String splitMensagem[] = mensagem.split(":");
        picole.setId(Integer.valueOf(splitMensagem[0]));
        picole.setSabor(splitMensagem[1]);
        picole.setPreco(splitMensagem[2]);
        picole.setMarca(splitMensagem[3]);
        picole.setValidade(splitMensagem[4]);
        picole.setPeso(splitMensagem[5]);

        return picole;
    }

    public Picole tratarMensagem(String mensagem){
        Picole picole = new Picole();

        // Modelo de mensgagem: id:sabor:preco:marca:validade:peso
        String splitMensagem[] = mensagem.split(":");
        picole.setId(Integer.valueOf(splitMensagem[0]));
        picole.setSabor(splitMensagem[1]);
        picole.setPreco(splitMensagem[2]);
        picole.setMarca(splitMensagem[3]);
        picole.setValidade(splitMensagem[4]);
        picole.setPeso(splitMensagem[5]);
        picole.setDeleted(Boolean.parseBoolean(splitMensagem[6]));

        return picole;
    }

    public List<Picole> getListaDePicoles(String retorno){
        List<Picole> picoles = new ArrayList<Picole>();
        if (retorno.equals("")){
            JOptionPane.showMessageDialog(null, "Nao ha registros!");
            return null;
        }
        String[] stringPicoles = retorno.split("%%");
        for (String linha : stringPicoles){
            picoles.add(tratarMensagem(linha));
        }
        return picoles;
    }
}
