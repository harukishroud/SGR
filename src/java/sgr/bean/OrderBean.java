/*
    SGR ALPHA - BEAN PACKAGE
    File: ORDERBEAN.JAVA | Last Major Update: 12.05.2015
    Developer: Rafael Sousa
    IDINALOG REBORN © 2015
*/

package sgr.bean;

public class OrderBean {
    
    private int codigo;
    private String pedidoStatus;
    private int mesa;
    private Long codigoConta;
    private int codigoCliente;
    private String codigoClienteCpf;
    private String itemStatus;
        

    // <editor-fold defaultstate="collapsed" desc="GET and SET"> 
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

   


    
          // </editor-fold>   



    public String getPedidoStatus() {
        return pedidoStatus;
    }

    public void setPedidoStatus(String pedidoStatus) {
        this.pedidoStatus = pedidoStatus;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public long getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(long codigoConta) {
        this.codigoConta = codigoConta;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoClienteCpf() {
        return codigoClienteCpf;
    }

    public void setCodigoClienteCpf(String codigoClienteCpf) {
        this.codigoClienteCpf = codigoClienteCpf;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }


}
