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
import sgr.bean.ItemBean;
import sgr.sql.QueryBuilder;

public class OrderDAO {
    
    public void addOrder(ItemBean item) throws SQLException, ExceptionDAO {
        
    }
    
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
