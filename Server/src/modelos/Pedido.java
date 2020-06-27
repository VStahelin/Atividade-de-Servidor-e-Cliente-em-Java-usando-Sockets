package modelos;

public class Pedido {
    String acao;
    String pedido;
    String complemento;

    public Pedido(){
    }

    public Pedido(String acao, String pedido, String complemento){
        this.acao = acao;
        this.pedido = pedido;
        this.complemento = complemento;
    }

    public Pedido(String pedido){
        //Modedo de mensagem: xxx##xx:xx:xx..xx##xx:xxx..xx
        String split[] = pedido.split("##");
        try {
            this.acao = split[0];
            this.pedido = split[1];
            this.complemento = split[2];
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
