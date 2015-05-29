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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.ClientBean;
import sgr.bean.ItemBean;
import sgr.sql.QueryBuilder;

public class OrderDAO {
    
    // HTTP Session
    private HttpSession session;
    
    public void newOrder(ClientBean client) throws SQLException, ExceptionDAO {
        
        int mesaAtual = (int)  getSession().getAttribute("currentTable");
        long sessionAtual = (long) getSession().getAttribute("currentSessionCode");
        
        System.out.println("Mesa: " + mesaAtual);
        System.out.println("Session: " + sessionAtual);
        
        // Inicia Services
        FacesContext ctx = FacesContext.getCurrentInstance();

        // Inicia HTTPSessiosetSessions(ession = (HttpSession) ctx.getExternalContext().getSe)ssion(false);
        
        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        
        // Cria novo Pedido        
        String sql = "INSERT INTO pedido(MESA_NUMERO,CONTA_CODIGO,CONTA_CLIENTE_CODIGO,CONTA_CLIENTE_CPF) VALUES (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, mesaAtual);
        ps.setLong(2, sessionAtual);
        ps.setInt(3, client.getCodigo());
        ps.setLong(4, client.getCpf());
        ps.execute();
        ps.close();              
        
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
            order.setItemStatus(rs.getString("pi.Status"));
            order.setPedidoStatus(rs.getString("p.Status"));
            
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
              }

   
        
        rs.close();
        ps.close();
        conn.close();
        
        return itemList;
        
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
    
}
