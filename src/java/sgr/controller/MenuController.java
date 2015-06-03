/*
 SGR ALPHA - CONTROLLER PACKAGE
 File: MENUCONTROLLER.JAVA | Last Major Update: 25.05.2015
 Developer: Kevin Raian e Rafael Sousa
 IDINALOG REBORN © 2015
 */
package sgr.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.ClientBean;
import sgr.bean.ItemBean;
import sgr.bean.OrderBean;
import sgr.bean.OrderBuilderBean;
import sgr.bean.OrderItemsBean;
import sgr.bean.TableBean;
import sgr.dao.ExceptionDAO;
import sgr.service.ClientService;
import sgr.service.ItemService;
import sgr.service.OrderItemService;
import sgr.service.OrderService;

@SessionScoped
@ViewScoped
@ManagedBean(name = "menuController")

public class MenuController {

    /* VARIAVEIS */
    private String currentUsername = "";
    private int currentTable;
    private long currentSessionCode;
    private String selectedItemType = "Todos";
    private String itemName = "";
    private String itemType = "";
    private float orderBuilderPrice = 0;
    boolean itemExists = false;
    private int orderCode;
    
    // Beans
    private ItemBean itemBean = new ItemBean();
    private ItemBean orderBuilderItem = new ItemBean();
    private OrderBean orderBean = new OrderBean();
    private OrderBuilderBean orderBuilderBean = new OrderBuilderBean();
    private ClientBean clientBean = new ClientBean();
    private OrderItemsBean orderItemBean = new OrderItemsBean();

    // Lists
    private List<ItemBean> itemTypes = new ArrayList<ItemBean>();
    private List<ItemBean> itemList = new ArrayList<ItemBean>();
    private List<ItemBean> orderBuilderList = new ArrayList<ItemBean>();
    private List<ClientBean> clientList = new ArrayList<ClientBean>();
    private List<OrderBean> orderList = new ArrayList<OrderBean>();

    // Inicia Services
    
    private ItemService itemService = new ItemService();
    private OrderService orderService = new OrderService();
    private FacesContext ctx = FacesContext.getCurrentInstance();
    private ClientService clientService = new ClientService();
    private OrderItemService orderItemService = new OrderItemService();

    
     // HTTP Session
    private HttpSession session;
    
    
    /* CONSTRUTOR */
    public MenuController() throws ExceptionDAO {

        itemTypes = itemService.searchItemTypes();
        setItemList(itemService.listItems(selectedItemType));
        
        // Inicia HTTPSession
        session = (HttpSession) ctx.getExternalContext().getSession(false);

    }

    /* MÉTODOS */
    // MÉTODO 01 - showSelectedTypeItems()
    // Carrega a lista de items de acordo com seleção de tipo.
    public void showSelectedTypeItems() throws ExceptionDAO {

        setItemList(itemService.listItems(selectedItemType));

    }

    // MÉTODO 02 - clearOrderBuilder()
    // Limpa pedido temporário.
    public void clearOrderBuilder() {
        
        System.out.println("[MENU CONTROLLER] Pedido temporário reiniciado.");
        orderBuilderList.clear();
        orderBuilderPrice = 0;
            
    }

    // MÉTODO 03 - addOrderItem()
    // Adiciona item ao pedido temporário.
    public void addOrderItem(ItemBean item) throws ExceptionDAO {

        orderBuilderItem = item;        

        // ### 01 ###
        // Verifica Repetição de Items
        for (int i = 0; i < orderBuilderList.size(); i++) {            
            // @ 01.1
            // Caso o item exista a quantidade do item existente é incrementada
            if (orderBuilderItem.getNome() == orderBuilderList.get(i).getNome()) {
                System.out.println("[MENU CONTROLLER][03][01.1] Item '" + orderBuilderItem.getNome() + "' existente no pedido temporário atual!" );
                itemExists = true;
                // Atualiza preço total do pedido temporário
                orderBuilderPrice = orderBuilderPrice + orderBuilderItem.getPreco();
                // Incrementa item
                orderBuilderList.get(i).setQuantidade(orderBuilderList.get(i).getQuantidade() + 1);
                System.out.println("[MENU CONTROLLER][03][01.1] Item '" + orderBuilderItem.getNome() + "' incrementado para '"
                        + orderBuilderList.get(i).getQuantidade() + "'." );
                break;
            // @ 01.2
            // Caso o item não exista a sequência ### 02 ### é habilitada
            } else {
                System.out.println("[MENU CONTROLLER][03][01.2] Item '" + orderBuilderItem.getNome() + "' não encontrado no pedido temporário atual.");
                itemExists = false;
            }
        }

        // ### 02 ###
        // Adiciona Item ao Pedido Temporário
        if (itemExists == false) {
            
            if (orderBuilderItem.getQuantidade() == 0) {
                orderBuilderItem.setQuantidade(1);
            }
            // Atualiza preço total do pedido temporário
            orderBuilderPrice = orderBuilderPrice + orderBuilderItem.getPreco();
            // Adiciona item
            orderBuilderList.add(orderBuilderItem);
            System.out.println("[MENU CONTROLLER][03][02] Item '" + orderBuilderItem.getNome() + "' adicionado ao pedido temporário atual.");
            System.out.println("[MENU CONTROLLER][03][02] Codigo do item: '" + orderBuilderItem.getCodigo() + "'.");

        }
    }
    
    // MÉTODO 04 - remOrderItem()
    // Remove item do pedido temporário.
    public void remOrderItem(ItemBean item) throws ExceptionDAO {        
        
        orderBuilderItem = item;        
        
        for (int i = 0; i < orderBuilderList.size(); i++) {            
            
            if (orderBuilderItem.getNome() == orderBuilderList.get(i).getNome()) {
                
                if (orderBuilderList.get(i).getQuantidade() == 1) {
                    orderBuilderList.remove(i);
                    System.out.println("[MENU CONTROLLER][04][01.1] Item '" + orderBuilderItem.getNome() + "' removido do pedido temporário atual.");                    
                    break;
                } else {
                    orderBuilderList.get(i).setQuantidade(orderBuilderList.get(i).getQuantidade() - 1);
                    System.out.println("[MENU CONTROLLER][04][01.2] Item '" + orderBuilderItem.getNome() + "' decrementado para '"
                            + orderBuilderList.get(i).getQuantidade() + "'." );
                    break;
                }
                
            }
        }
    }
    
    // MÉTODO 05 - buildOrder()
    // Realiza o pedido oficial com base no pedido temporário.
    public void buildOrder() throws ExceptionDAO, SQLException {      
        currentUsername = (String) session.getAttribute("currentUsername");
        currentSessionCode = (Long) session.getAttribute("currentSessionCode");
        currentTable = (int) session.getAttribute("currentTable");
        
        clientList = clientService.pullClientData(currentUsername);
        clientBean = clientList.get(0);
        
        orderBean.setCodigoCliente(clientBean.getCodigo());
        orderBean.setCodigoClienteCpf(clientBean.getCpf());
        orderBean.setCodigoConta(currentSessionCode);
        orderBean.setPedidoStatus("Aberto");
        orderBean.setMesa(currentTable);
        
        orderService.newOrder(orderBean);
        
        orderList = orderService.searchOpenOrder(currentSessionCode);
        orderBean.setCodigo(orderList.get(0).getCodigo());
        
        orderItemBean.setCodigo_pedido(orderBean.getCodigo());
        orderItemBean.setMesa(orderBean.getMesa());
        
        for (int i = 0; i < orderBuilderList.size(); i++) {
            orderItemBean.setCodigo_item(orderBuilderList.get(i).getCodigo());
            orderItemBean.setQuantidade_item_pedido(orderBuilderList.get(i).getQuantidade());
            orderItemBean.setStatus_pedido("Solicitado");
            
            orderItemService.addOrderItem(orderItemBean);            
        }
        
        clearOrderBuilder();
        
        System.out.println("[MENU CONTROLLER] Pedido realizado com sucesso!");
        
    }

    // <editor-fold desc="GET and SET" defaultstate="collapsed">
    public ItemBean getItemBean() {
        return itemBean;
    }

    public void setItemBean(ItemBean itemBean) {
        this.itemBean = itemBean;
    }

    public List<ItemBean> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(List<ItemBean> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public String getSelectedItemType() {
        return selectedItemType;
    }

    public void setSelectedItemType(String selectedItemType) {
        this.selectedItemType = selectedItemType;
    }
    
       public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
    }
    
    public OrderBuilderBean getOrderBuilderBean() {
        return orderBuilderBean;
    }

    public void setOrderBuilderBean(OrderBuilderBean orderBuilderBean) {
        this.orderBuilderBean = orderBuilderBean;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public float getOrderBuilderPrice() {
        return orderBuilderPrice;
    }

    public void setOrderBuilderPrice(float orderBuilderPrice) {
        this.orderBuilderPrice = orderBuilderPrice;
    }

    public ItemBean getOrderBuilderItem() {
        return orderBuilderItem;
    }

    public void setOrderBuilderItem(ItemBean orderBuilderItem) {
        this.orderBuilderItem = orderBuilderItem;
    }

    public List<ItemBean> getOrderBuilderList() {
        return orderBuilderList;
    }

    public void setOrderBuilderList(List<ItemBean> orderBuilderList) {
        this.orderBuilderList = orderBuilderList;
    }
   
    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }

    public List<ClientBean> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientBean> clientList) {
        this.clientList = clientList;
    }

    public FacesContext getCtx() {
        return ctx;
    }

    public void setCtx(FacesContext ctx) {
        this.ctx = ctx;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
    

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public OrderItemsBean getOrderItemBean() {
        return orderItemBean;
    }

    public void setOrderItemBean(OrderItemsBean orderItemBean) {
        this.orderItemBean = orderItemBean;
    }

    public OrderItemService getOrderItemService() {
        return orderItemService;
    }

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }   
    
    public long getCurrentSessionCode() {
        return currentSessionCode;
    }

    public void setCurrentSessionCode(long currentSessionCode) {
        this.currentSessionCode = currentSessionCode;
    }

    public int getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(int currentTable) {
        this.currentTable = currentTable;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

     // </editor-fold>
    
}
