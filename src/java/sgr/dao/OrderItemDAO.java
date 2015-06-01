/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.OrderItemsBean;
import sgr.util.ConnectionBuilder;

public class OrderItemDAO {
        
        public void addOrderItem(OrderItemsBean orderItem) throws SQLException, ExceptionDAO {

        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        
        String sql = "INSERT INTO pedido_itens(Pedido_Codigo,Pedido_Mesa_Numero,Itens_Codigo,Quantidade,Status)"
                + " VALUES (?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, orderItem.getCodigo_pedido());
        ps.setInt(2, orderItem.getMesa());
        ps.setInt(3, orderItem.getCodigo_item());
        ps.setInt(4, orderItem.getQuantidade_item_pedido());
        ps.setString(5, orderItem.getStatus_pedido());

        ps.execute();
        ps.close();
        conn.close();
        
        System.out.println("[ORDERITEM DAO] Item código " + orderItem.getCodigo_item() + " adicionado ao pedido "
        + orderItem.getCodigo_pedido() + ".");
        
    }

}
