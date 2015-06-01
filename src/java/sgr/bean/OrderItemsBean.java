/*
    SGR ALPHA - BEAN PACKAGE
    File: ORDERITEMSBEAN.JAVA | Last Major Update: 13.05.2015
    Developer: Kevin Raian
    IDINALOG REBORN © 2015
*/

package sgr.bean;

public class OrderItemsBean {
    
    private int codigo_pedido;
    private String status_pedido;
    private int codigo_item;
    private String nome_item;
    private int quantidade_item_pedido;
    private int mesa;

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }

    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public String getNome_item() {
        return nome_item;
    }

    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    public int getQuantidade_item_pedido() {
        return quantidade_item_pedido;
    }

    public void setQuantidade_item_pedido(int quantidade_item_pedido) {
        this.quantidade_item_pedido = quantidade_item_pedido;
    }

    public int getCodigo_item() {
        return codigo_item;
    }

    public void setCodigo_item(int codigo_item) {
        this.codigo_item = codigo_item;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    
    
}
