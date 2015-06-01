/*
    SGR ALPHA - SERVICE PACKAGE
    File: ORDERSERVICE.JAVA | Last Major Update: 13.05.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.service;

import sgr.bean.OrderBean;
import sgr.dao.OrderDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgr.bean.OrderItemsBean;
import sgr.dao.ExceptionDAO;
import sgr.sql.QueryBuilder;
import sgr.sql.QueryGender;
import sgr.sql.QueryOperation;
import sgr.sql.QueryType;

public class OrderService {
    
    public List<OrderBean> listOrders(long sessionCode) throws ExceptionDAO {
        
        OrderDAO orderDAO = new OrderDAO();
        List<OrderBean> orderList = new ArrayList<OrderBean>();
        QueryBuilder query = new QueryBuilder();
        
         query.addQuery(QueryOperation.empty,"Conta_Codigo", QueryGender.equal, String.valueOf(sessionCode), QueryType.number);
         // query.addQuery(QueryOperation.and,"pedido.conta_cliente_codigo", QueryGender.equal, String.valueOf(clientCode), QueryType.number);
         
        try {
            orderList = orderDAO.listOrders(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderList;
    }
    
    public List<OrderBean> listOrders(int pContaCodigo,int pMesa)  {
        
        OrderDAO orderDAO = new OrderDAO();
        List<OrderBean> orderList = new ArrayList<OrderBean>();
        QueryBuilder query = new QueryBuilder();
        
         query.addQuery(QueryOperation.empty,"pedido.conta_codigo", QueryGender.equal, String.valueOf(pContaCodigo), QueryType.number);
         query.addQuery(QueryOperation.and,"pedido.mesa_numero", QueryGender.equal, String.valueOf(pMesa), QueryType.number);
         
        try {
            orderList = orderDAO.listOrders(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionDAO ex) {
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
    
    public void newOrder (OrderBean order) throws SQLException, ExceptionDAO {
        try {
            System.out.println("[ORDER SERVICE] Preprando pra adicionar novo pedido...");
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.newOrder(order);           
        } catch (SQLException ex){
            System.out.println("[ORDER SERVICE] ERRO: Falha ao criar novo pedido!");
        }
    }
    
    public List<OrderBean> searchOpenOrder(long sessionCode) throws ExceptionDAO {
        
        OrderDAO orderDAO = new OrderDAO();
        List<OrderBean> orderList = new ArrayList<OrderBean>();
        QueryBuilder query = new QueryBuilder();
        
        String orderStatus = "Aberto";
        
        query.addQuery(QueryOperation.empty,"Conta_Codigo", QueryGender.equal, String.valueOf(sessionCode), QueryType.number);
        query.addQuery(QueryOperation.and,"Status", QueryGender.equal, orderStatus, QueryType.text);
         
        try {
            orderList = orderDAO.listOrders(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orderList;
    }
    
}