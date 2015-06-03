package sgr.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.OrderItemsBean;
import sgr.dao.ExceptionDAO;
import sgr.service.OrderService;


@SessionScoped
@ManagedBean(name="mainController")

public class MainController {
    
    /* VARIÁVEIS */
    private long currentSessionCode;
    
    // HTTP Session
    private HttpSession session;
    
    // Beans
    private OrderItemsBean orderItemsBean = new OrderItemsBean();
    
    // Lists
    private List<OrderItemsBean> orderItemList = new ArrayList<OrderItemsBean>();
    
    public MainController() throws ExceptionDAO {
        
        // Inicia Services
        FacesContext ctx = FacesContext.getCurrentInstance();
        OrderService orderService = new OrderService();
        
        // Inicia HTTPSession
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        
        currentSessionCode = (long) session.getAttribute("currentSessionCode");
        
        orderItemList = orderService.listOrdersItems(currentSessionCode);
        
    }

    public long getCurrentSessionCode() {
        return currentSessionCode;
    }

    public void setCurrentSessionCode(long currentSessionCode) {
        this.currentSessionCode = currentSessionCode;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public OrderItemsBean getOrderItemsBean() {
        return orderItemsBean;
    }

    public void setOrderItemsBean(OrderItemsBean orderItemsBean) {
        this.orderItemsBean = orderItemsBean;
    }

    public List<OrderItemsBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemsBean> orderItemList) {
        this.orderItemList = orderItemList;
    }
    
}
