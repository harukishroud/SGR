/*
    SGR ALPHA - SERVICE PACKAGE
    File: ORDERSERVICE.JAVA | Last Major Update: 13.05.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.service;

import sgr.bean.OrderBean;
import sgr.bean.OrderItemsBean;
import sgr.dao.OrderDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgr.bean.ClientBean;
import sgr.dao.ExceptionDAO;
import sgr.sql.QueryBuilder;
import sgr.sql.QueryGender;
import sgr.sql.QueryOperation;
import sgr.sql.QueryType;

public class OrderService {
    
    public void newOrder(ClientBean client) throws SQLException, ExceptionDAO {
        try {
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.newOrder(client);
            System.out.println("[ORDER SERVICE] Novo pedido adicionado para o cliente '" + client.getNome_usuario() + "'.");
        } catch (SQLException ex){
            System.out.println("[ORDER SERVICE] ERRO: Falha ao criar novo pedido!");
        }
    }
    
    public List<OrderBean> listOrders(long sessionCode) throws ExceptionDAO {
        
        OrderDAO orderDAO = new OrderDAO();
        List<OrderBean> orderList = new ArrayList<OrderBean>();
        QueryBuilder query = new QueryBuilder();
        
         query.addQuery(QueryOperation.empty,"p.conta_codigo", QueryGender.equal, String.valueOf(sessionCode), QueryType.number);
         // query.addQuery(QueryOperation.and,"pedido.conta_cliente_codigo", QueryGender.equal, String.valueOf(clientCode), QueryType.number);
         
        try {
            orderList = orderDAO.listOrders(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderList;
    }
    
     public List<OrderItemsBean> listOrdersItems(long sessionCode) throws ExceptionDAO {
        
        OrderDAO orderDAO = new OrderDAO();
        List<OrderItemsBean> orderItemList = new ArrayList<OrderItemsBean>();
        QueryBuilder query = new QueryBuilder();
        
         query.addQuery(QueryOperation.empty,"p.conta_codigo", QueryGender.equal, String.valueOf(sessionCode), QueryType.number);
         // query.addQuery(QueryOperation.and,"pedido.conta_cliente_codigo", QueryGender.equal, String.valueOf(clientCode), QueryType.number);
         
        try {
            orderItemList = orderDAO.listItems(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderItemList;
    }
    
    
}
