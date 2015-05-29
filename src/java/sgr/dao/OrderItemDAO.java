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

    // HTTP Session
    private HttpSession session;

    public void newOrder(OrderItemsBean orderItem) throws SQLException, ExceptionDAO {

        // Inicia Services
        FacesContext ctx = FacesContext.getCurrentInstance();

        // Inicia HTTPSession
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        ConnectionBuilder connection = new ConnectionBuilder();
        Connection conn = connection.getConnection();
        String sql = "INSERT INTO pedido_itens(pedido_codigo,pedido_mesa_numero,itens_codigo,quantidade,status)"
                + "values (?,?,?,?,?)";

        System.out.println(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, orderItem.getCodigo_pedido());
        ps.setInt(2, (int) session.getAttribute("currentTable"));
        ps.setInt(3, orderItem.getCodigo_item());
        ps.setInt(4, orderItem.getQuantidade_item_pedido());
        ps.setString(5, orderItem.getStatus_pedido());

        ps.execute();
        ps.close();
        conn.close();
    }

}
