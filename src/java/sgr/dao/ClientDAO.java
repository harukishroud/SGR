/*
    SGR ALPHA - DAO PACKAGE
    File: CLIENTDAO.JAVA | Last Major Update: 30.04.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.dao;

import sgr.bean.ClientBean;
import sgr.sql.QueryBuilder;
import sgr.util.ConnectionBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    
    public List<ClientBean>loadClient(QueryBuilder query) throws SQLException, ClassNotFoundException, ExceptionDAO {
        
        ConnectionBuilder conexao = new ConnectionBuilder();
        Connection conn = conexao.getConnection();
        List<ClientBean> clientList = new ArrayList<ClientBean>();
        String sql = "SELECT * FROM cliente " + query.buildQuery();
        
        System.out.println("[CLIENT DAO] SQL being executed: '" + sql + "'.");
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();        
                
        while(rs.next()) {
            
            ClientBean clientBean = new ClientBean();    
            
            clientBean.setCodigo(rs.getInt("codigo"));
            clientBean.setNome(rs.getString("nome"));
            clientBean.setData_nasc(rs.getDate("DATA_NASC"));
            clientBean.setEndereco(rs.getString("endereco"));
            clientBean.setNumero(rs.getInt("numero"));
            clientBean.setComplemento(rs.getString("complemento"));
            clientBean.setCidade(rs.getString("cidade"));
            clientBean.setBairro(rs.getString("Bairro"));
            clientBean.setEstado(rs.getString("estado"));
            clientBean.setCpf(rs.getLong("cpf"));
            clientBean.setRg(rs.getLong("rg"));
            clientBean.setTel_res(rs.getLong("tel_residencial"));
            clientBean.setTel_mov(rs.getLong("tel_movel"));
            clientBean.setData_nasc(rs.getDate("DATA_NASC"));
            clientBean.setEmail(rs.getString("email"));
            clientBean.setNome_usuario(rs.getString("nome_usuario"));
            clientBean.setSenha(rs.getString("senha"));
            
            System.out.println("[CLIENT DAO] Data fetched from SQL result: Codigo '" + clientBean.getCodigo() + "', Nome '" + clientBean.getNome() + "', Data Nasc. '" 
                    + clientBean.getData_nasc() + "', Endereço '" + clientBean.getEndereco() + "', Numero '" + clientBean.getNumero() + "', Complemento '" + clientBean.getComplemento()
            + "', Cidade '" + clientBean.getCidade() + "', Bairro '" + clientBean.getBairro() + "', Estado '" + clientBean.getEstado() + "', CPF '" + clientBean.getCpf() +
                    "', RG '" + clientBean.getRg() + "', Telefone Residêncial '" + clientBean.getTel_res() + "', Telefone Celular '" + clientBean.getTel_mov() + "', Email '"
            + clientBean.getEmail() + "', Nome Usuário '" + clientBean.getNome_usuario() + "', Senha '" + clientBean.getSenha() + "'." );
            
            clientList.add(clientBean);
        
        }
        
        rs.close();
        ps.close();
        conn.close();
        return clientList;
       
    }
    
    public void updateClient(ClientBean client) throws ExceptionDAO, SQLException {
        
        ConnectionBuilder conexao = new ConnectionBuilder();
        Connection conn = conexao.getConnection();
        
        System.out.println("[CLIENT DAO] Preparando para atualizar cliente.");
        
        String sql = "UPDATE cliente SET endereco=?,numero=?,complemento=?,"
                + "cidade=?,bairro=?,estado=?,tel_residencial=?,tel_movel=?,"
                + "email=?,senha=? where codigo=?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, client.getEndereco());
        ps.setInt(2, client.getNumero());
        ps.setString(3, client.getComplemento());
        ps.setString(4, client.getCidade());
        ps.setString(5, client.getBairro());
        ps.setString(6, client.getEstado());
        ps.setLong(7, client.getTel_res());
        ps.setLong(8, client.getTel_mov());
        ps.setString(9, client.getEmail());
        ps.setString(10, client.getSenha());
        ps.setInt(11, client.getCodigo());
        ps.execute();
        ps.close();
        conn.close();      
        
        System.out.println("[CLIENT DAO] Cliente atualizado.");
        
    }
}
