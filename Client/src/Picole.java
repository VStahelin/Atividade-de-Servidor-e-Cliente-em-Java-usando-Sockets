public class Picole {

    private int id; //4 chars
    private String sabor; //20 chars
    private String preco; //5 chars
    private String marca; //20 chars
    private String validade; //10 chars
    private String peso; //7 chars
    private boolean isDeleted = false;

    public Picole() {
        this(0, "", "", "", "", "");
    }

    public Picole(int id, String sabor, String preco, String marca, String validade, String peso) {
        setId(id);
        setSabor(sabor);
        setMarca(marca);
        setPreco(preco);
        setValidade(validade);
        setPeso(peso);
    }

    @Override
    public String toString() {
        return ("Id: " + getId() + " \\ Sabor: " + getSabor() + "\\ Marca: " + getMarca() + "\\ Preco: " + getPreco() + "\\ Validade: " + getValidade() + "\\ Peso: " + getPeso() + "\\ Deletado?: " + isDeleted());
    }

    public String logicalString() {
        return (getId() + " -  " + getSabor() + " - " + getMarca() + " - " + getPreco() + " - " + getValidade() + " - " + getPeso());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getId() {
        return id;
    }

    public String getSabor() {
        return sabor;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean isDeleted){ this.isDeleted = isDeleted; }
}
