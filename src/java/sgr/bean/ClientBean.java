/*
    SGR ALPHA - BEAN PACKAGE
    File: CLIENTBEAN.JAVA | Last Major Update: 29.04.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.bean;

import java.util.Date;
import javax.faces.bean.ManagedBean;

public class ClientBean {
    
    private int codigo;
    private String nome;
    private Date data_nasc;
    private String endereco;
    private int numero;
    private String complemento;
    private String cidade;
    private String bairro;
    private String estado;
    private long cpf;
    private long rg;
    private long tel_res;
    private long tel_mov;
    private String email;
    private String nome_usuario;
    private String senha;

    // <editor-fold defaultstate="collapsed" desc="GET and SET"> 
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public long getTel_res() {
        return tel_res;
    }

    public void setTel_res(long tel_res) {
        this.tel_res = tel_res;
    }

    public long getTel_mov() {
        return tel_mov;
    }

    public void setTel_mov(long tel_mov) {
        this.tel_mov = tel_mov;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // </editor-fold>   
    
    
}
