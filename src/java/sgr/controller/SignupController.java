/*
    SGR ALPHA - CONTROLLER PACKAGE
    File: SIGNUPCONTROLLER.JAVA | Last Major Update: 05.06.15
    Developer: Kevin Raian
    IDINALOG REBORN © 2015
*/

package sgr.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sgr.bean.ClientBean;
import sgr.service.ClientService;

@SessionScoped
@ManagedBean (name="signupController")

public class SignupController {
    
    /* VARIAVEIS */
    
    // Beans
    private ClientBean clientBean = new ClientBean();
    
    public void newClient() {
        
        // Inicia Services
        ClientService clientService = new ClientService();
        
        System.out.println("[SIGNUP CONTROLLER] Cliente em fila para cadastro: '"
                + clientBean.getNome_usuario() + "'.");
        System.out.println("[SIGNUP CONTROLLER] Efetuando processo de cadastro...");
        
        clientService.newClient(clientBean);        
        
        
    }

    // <editor-fold desc="GET and SET" defaultstate="collapsed">
    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }
    // </editor-fold>    
    
}
