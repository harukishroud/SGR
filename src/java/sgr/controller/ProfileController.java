/*
 SGR ALPHA - CONTROLLER PACKAGE
 File: PROFILECONTROLLER.JAVA | Last Major Update: 29.05.2015
 Developer: Kevin Raian
 IDINALOG REBORN © 2015
 */

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.ClientBean;
import sgr.dao.ExceptionDAO;
import sgr.service.ClientService;

@SessionScoped
@ManagedBean(name = "profileController")

public class ProfileController {

    /* VARIAVEIS */
    // HTTP Session
    private HttpSession session;
    private String currentUsername;

    // Beans    
    private ClientBean clientBean = new ClientBean();
    private ClientBean updatedClientBean = new ClientBean();

    // Lists
    private List<ClientBean> listClient = new ArrayList<ClientBean>();

    public ProfileController() throws ExceptionDAO {

        // Inicia Services
        ClientService clientService = new ClientService();
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // Inicia HTTPSession
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        currentUsername = (String) session.getAttribute("currentUsername");
        
        // Busca dados do Cliente
        listClient = clientService.pullClientData(currentUsername);
        updatedClientBean = listClient.get(0);
            
    }
    
    public void updateProfile() {
        System.out.println("[PROFILE CONTROLLER] Iniciando atualização do cliente.");
        ClientService clientService = new ClientService();
        clientService.updateClient(updatedClientBean);
    }

    // <editor-fold desc="GET and SET" defaultstate="collapsed">

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

    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }

    public List<ClientBean> getListClient() {
        return listClient;
    }

    public void setListClient(List<ClientBean> listClient) {
        this.listClient = listClient;
    }
    
    public ClientBean getUpdatedClientBean() {
        return updatedClientBean;
    }

    public void setUpdatedClientBean(ClientBean updatedClientBean) {
        this.updatedClientBean = updatedClientBean;
    }
    
    // </editor-fold>

}
