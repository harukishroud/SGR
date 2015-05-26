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
    
    /*
    public void abrirPedido(PedidoBean pPedido) {
        PedidoDAO pDAO = new PedidoDAO();
        
        if(pPedido.getCodigo() == 0) {
            
            try {
                pDAO.abrirPedido(pPedido);
            } catch (SQLException ex) {
                Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            
            try {
                pDAO.alterarPedido(pPedido);
            } catch (SQLException ex) {
                Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    */
    /*
    public void deletarPedido(PedidoBean pPedido) {
        PedidoDAO pDAO = new PedidoDAO();
        
        try {
            pDAO.deletarPedido(pPedido);
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    */
    
}
