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
import sgr.bean.OrderItemsBean;
import sgr.dao.ExceptionDAO;
import sgr.service.ItemService;

@SessionScoped
@ManagedBean(name = "menuController")

public class MenuController {

    /* VARIAVEIS */
    private String selectedItemType = "Todos";
    private String itemName = "";
    private String itemType = "";
    private float itemPrice = 0;

    boolean itemExists = false;

    // Beans
    private ItemBean itemBean = new ItemBean();
    private ItemBean orderBuilderItem = new ItemBean();
    private OrderBuilderBean orderBuilderBean = new OrderBuilderBean();

    // Lists
    private List<ItemBean> itemTypes = new ArrayList<ItemBean>();
    private List<ItemBean> itemList = new ArrayList<ItemBean>();
    // private List<OrderBuilderBean> orderBuilderList = new ArrayList<OrderBuilderBean>();
    private List<ItemBean> orderBuilderList = new ArrayList<ItemBean>();

    // Inicia Services
    private ItemService itemService = new ItemService();

    public MenuController() throws ExceptionDAO {

        itemTypes = itemService.searchItemTypes();
        setItemList(itemService.listItems(selectedItemType));

    }

    public void showSelectedTypeItems() throws ExceptionDAO {

        setItemList(itemService.listItems(selectedItemType));

    }

    public void clearOrderBuilder() {
        getOrderBuilderList().clear();
        System.out.println("Pedido Limpado.");
    }

    public void addOrderItem(ItemBean item) throws ExceptionDAO {

        orderBuilderItem = item;        

        // Varre Array buscando por Item já Existente
        for (int i = 0; i < orderBuilderList.size(); i++) {
            if (orderBuilderItem.getNome() == orderBuilderList.get(i).getNome()) {

                itemExists = true;
                orderBuilderList.get(i).setQuantidade(orderBuilderList.get(i).getQuantidade() + 1);

            } else {
                
                itemExists = false;
                
            }
        }

        // Executado se item não existente
        if (itemExists == false) {

            if (orderBuilderItem.getQuantidade() == 0) {
                orderBuilderItem.setQuantidade(1);
            }

            orderBuilderList.add(orderBuilderItem);
        }
    }

    // <editor-fold desc="GET and SET">
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
    // </editor-fold>

    public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
    }
    /*
     public List<OrderBuilderBean> getOrderBuilderList() {
     return orderBuilderList;
     }

     public void setOrderBuilderList(List<OrderBuilderBean> orderBuilderList) {
     this.orderBuilderList = orderBuilderList;
     }
     */

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

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
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

}
