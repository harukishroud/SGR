/*
 SGR ALPHA - CONTROLLER PACKAGE
 File: MENUCONTROLLER.JAVA | Last Major Update: 25.05.2015
 Developer: Kevin Raian e Rafael Sousa
 IDINALOG REBORN © 2015
 */
package sgr.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sgr.bean.ItemBean;
import sgr.bean.OrderBuilderBean;
import sgr.dao.ExceptionDAO;
import sgr.service.ItemService;

@SessionScoped
@ManagedBean(name = "menuController")

public class MenuController {

    /* VARIAVEIS */
    private String selectedItemType = "Todos";
    private String itemName = "";
    private String itemType = "";
    private float orderBuilderPrice = 0;
    boolean itemExists = false;
    

    // Beans
    private ItemBean itemBean = new ItemBean();
    private ItemBean orderBuilderItem = new ItemBean();
    private OrderBuilderBean orderBuilderBean = new OrderBuilderBean();

    // Lists
    private List<ItemBean> itemTypes = new ArrayList<ItemBean>();
    private List<ItemBean> itemList = new ArrayList<ItemBean>();
    private List<ItemBean> orderBuilderList = new ArrayList<ItemBean>();

    // Inicia Services
    private ItemService itemService = new ItemService();

    /* CONSTRUTOR */
    public MenuController() throws ExceptionDAO {

        itemTypes = itemService.searchItemTypes();
        setItemList(itemService.listItems(selectedItemType));

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
                itemExists = true;
                // Atualiza preço total do pedido temporário
                orderBuilderPrice = orderBuilderPrice + orderBuilderItem.getPreco();
                // Incrementa item
                orderBuilderList.get(i).setQuantidade(orderBuilderList.get(i).getQuantidade() + 1);
                break;
            // @ 04.2
            // Caso o item não exista a sequência ### 02 ### é habilitada
            } else {
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
        }
    }
    
    // MÉTODO 04 - doOrder()
    // Tranforma o pedido temporário em um pedido definitivo.
    public void doOrder() throws ExceptionDAO {
        
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
    // </editor-fold>

}
