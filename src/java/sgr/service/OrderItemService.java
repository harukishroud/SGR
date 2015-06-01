/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgr.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgr.bean.OrderItemsBean;
import sgr.dao.ExceptionDAO;
import sgr.dao.OrderItemDAO;


public class OrderItemService {
 
    public void addOrderItem(OrderItemsBean orderItem) {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        
        try {
            System.out.println("[ORDERITEM SERVICE] Preparando para adicionar item ao pedido...");
            orderItemDAO.addOrderItem(orderItem);
        } catch (SQLException ex) {
            System.out.println("[ORDERITEM SERVICE] ERRO: Falha ao adicionar item ao pedido. LOG: " + ex.getSQLState());
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceptionDAO ex) {
            System.out.println("[ORDERITEM SERVICE] ERRO: Falha ao adicionar item ao pedido. LOG: " + ex.getMessage());
            Logger.getLogger(OrderItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
