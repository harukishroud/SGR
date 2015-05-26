package sgr.bean;

public class OrderBuilderBean {
    
    private String nome;
    private String tipo;
    private float preco;
    private int quantidade_item;    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade_item() {
        return quantidade_item;
    }

    public void setQuantidade_item(int quantidade_item) {
        this.quantidade_item = quantidade_item;
    }
    
    
}
