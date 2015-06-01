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

    public void newOrder(OrderBean order) throws SQLException, ExceptionDAO {

        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO pedido (Mesa_Numero, Conta_Codigo,Conta_Cliente_Codigo,"
                + " Conta_Cliente_CPF, Status) VALUES (?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        
        System.out.println("[ORDER DAO] Dados armazenados para novo pedido: Mesa '" + order.getMesa() + "', Código da Conta: '" 
        + order.getCodigoConta() + "', Código do Cliente: '" + order.getCodigoCliente() + "', CPF: '" + order.getCodigoClienteCpf() + 
                "', Status: '" + order.getPedidoStatus() + "'.");

        ps.setInt(1, order.getMesa());
        ps.setLong(2, order.getCodigoConta());
        ps.setInt(3, order.getCodigoCliente());
        ps.setLong(4, order.getCodigoClienteCpf());        
        ps.setString(5, order.getPedidoStatus());

        ps.execute();
        ps.close();
        conn.close();
        
        System.out.println("[ORDER DAO] Pedido adicionado com sucesso ao código: " + order.getCodigoConta());
    }

    public List<OrderBean> listOrders(QueryBuilder query) throws SQLException, ExceptionDAO {

        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        List<OrderBean> listOrder = new ArrayList<OrderBean>();

        String sql = "SELECT * from pedido" + query.buildQuery();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            OrderBean order = new OrderBean();
            order.setCodigo(rs.getInt("Codigo"));
            order.setPedidoStatus(rs.getString("Status"));

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


}
