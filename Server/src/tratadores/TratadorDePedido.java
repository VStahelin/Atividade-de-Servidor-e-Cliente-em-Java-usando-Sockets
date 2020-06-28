package tratadores;

import modelos.Picole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TratadorDePedido {

    public Picole softTratarMensagem(String mensagem){
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

        // Modelo de mensgagem: id:sabor:preco:marca:validade:peso:isDeleted
        String splitMensagem[] = mensagem.split(":");
        picole.setId(Integer.valueOf(splitMensagem[0]));
        picole.setSabor(splitMensagem[1]);
        picole.setPreco(splitMensagem[2]);
        picole.setMarca(splitMensagem[3]);
        picole.setValidade(splitMensagem[4]);
        picole.setPeso(splitMensagem[5]);
        picole.setDeleted(Boolean.getBoolean(splitMensagem[6]));

        return picole;
    }

    public Map<String,String> tradarComplemento(String complemento){
        String[] split = complemento.split(":");
        Map<String,String> dicionarioComplemento = new HashMap<String,String>();

        for(int i=0; i < split.length; i+=2){
            dicionarioComplemento.put(split[i],split[i+1]);
        }

        return dicionarioComplemento;
    }

    public String trataSoftSeletcReturn(List<Picole> picoles){
        String mensagem = "";
        for (Picole picole : picoles){
            mensagem = mensagem + picole.softStringLogica() + "%%";
        }
        return mensagem;
    }

    public String trataSeletcReturn(List<Picole> picoles){
        String mensagem = "";
        for (Picole picole : picoles){
            mensagem = mensagem + picole.stringLogica() + "%%";
        }
        return mensagem;
    }


}
