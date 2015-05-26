/*
    SGR ALPHA - DAO PACKAGE
    File: ORDERDAO.JAVA | Last Major Update: 12.05.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.dao;

import sgr.bean.OrderBean;
import sgr.bean.OrderItemsBean;
import sgr.util.ConnectionBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sgr.sql.QueryBuilder;

public class OrderDAO {
    
    /*
    public void newOrder(OrderBean order) throws SQLException {
            ConnectionBuilder connection = new ConnectionBuilder();
            Connection conn = connection.getConnection();
            String sql = "INSERT INTO PEDIDO("
                  + "status,"
                  + "data,"
                  + "mesa_numero,"
                  + "conta_cod,"
                  + "conta_cliente_codigo,"
                  + "conta_cliente_cpf)" 
                  + "values(?,?,?,?,?,?);";
                  
                 
          System.out.println(sql);
          PreparedStatement ps = conn.prepareStatement(sql);
          
          ps.setString(1, order.getStatus());
        
        //aqui ocorre uma conversao de java.util.date para sql.util.date sem essa validacao ocorre erro na hora do insert 
           try {
            ps.setDate(2, new java.sql.Date(order.getData_hora().getTime()));
        } catch (NullPointerException e) {
            ps.setDate(2, null);
        } 
          ps.setInt(3, order.getMesaCod());
          ps.setInt(4, order.getContaCod());
          ps.setInt(5, order.getClienteCod());
          ps.setString(6, order.getClienteCpf());
          ps.execute();
          ps.close();
          conn.close();
    }
    */
    
    /*
    public void alterarPedido(PedidoBean pPedido) throws SQLException {
         ConnectionBuilder conexao = new ConnectionBuilder();
        Connection conn = conexao.getConnection();
        String sql = "UPDATE PEDIDO set status=?,mesa_numero=? where codigo=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, pPedido.getStatus());
        ps.setInt(2, pPedido.getMesaCod());
        ps.setInt(3, pPedido.getCodigo());
        ps.execute();
        ps.close();
        conn.close();
        
    }
    */
    /*
    public void deletarPedido(PedidoBean pPedido) throws SQLException {
         ConnectionBuilder conexao = new ConnectionBuilder();
        Connection conn = conexao.getConnection();
        String sql = "DELETE FROM PEDIDO WHERE CODIGO = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pPedido.getCodigo());
        ps.execute();
        ps.close();
        conn.close();
    }
    */
    
    public List<OrderBean> listOrders(QueryBuilder query) throws SQLException, ExceptionDAO {
        
        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        List<OrderBean> listOrder = new ArrayList<OrderBean>();
        
        String sql = "SELECT p.Codigo, pi.Status FROM pedido p inner join pedido_itens pi on p.Codigo = pi.Pedido_Codigo" + query.buildQuery();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            OrderBean order = new OrderBean();
            order.setCodigo(rs.getInt("p.Codigo"));
            order.setStatus(rs.getString("pi.Status"));
            
            listOrder.add(order);
        }
        
        rs.close();
        ps.close();
        conn.close();
        
        return listOrder;
    
    }    
    
    public List<OrderItemsBean> listItems (QueryBuilder query) throws SQLException, ExceptionDAO {
        
        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        List<OrderItemsBean> itemList = new ArrayList<OrderItemsBean>();
        
        String sql = "select p.Codigo, it.Nome, pi.Quantidade, pi.Status from "
                + "pedido p inner join pedido_itens pi on p.Codigo = "
                + "pi.Pedido_Codigo inner join item it on pi.Itens_Codigo = "
                + "it.Codigo" + query.buildQuery();
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            OrderItemsBean orderItem = new OrderItemsBean();
            orderItem.setCodigo_pedido(rs.getInt("p.Codigo"));
            orderItem.setStatus_pedido(rs.getString("it.Status"));
            orderItem.setQuantidade_item_pedido(rs.getInt("pi.Quantidade"));
            orderItem.setNome_item(rs.getString("it.Nome"));
            
            itemList.add(orderItem);
        }
        
        rs.close();
        ps.close();
        conn.close();
        
        return itemList;
        
    }
    
}
