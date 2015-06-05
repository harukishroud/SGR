/*
    SGR ALPHA - BEAN PACKAGE
    File: SESSIONBEAN.JAVA | Last Major Update: 05.05.2015
    Developer: Kevin Raian, Rafael Sousa
    IDINALOG REBORN © 2015
*/

package sgr.bean;

import javax.faces.bean.ManagedBean;

public class SessionBean {
    
    private long codigo;
    private int status;
    private int c_codigo;
    private String c_cpf;
    
    // <editor-fold defaultstate="collapsed" desc="GET and SET"> 
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }                

    public int getC_codigo() {
        return c_codigo;
    }

    public void setC_codigo(int c_codigo) {
        this.c_codigo = c_codigo;
    }

    public String getC_cpf() {
        return c_cpf;
    }

    public void setC_cpf(String c_cpf) {
        this.c_cpf = c_cpf;
    }    
    // </editor-fold>
    
}
