/*
    SGR ALPHA - CONTROLLER PACKAGE
    File: CLIENTCONTROLLER.JAVA | Last Major Update: 29.04.2015
    Developer: Kevin Raian, Washington Reis
    IDINALOG REBORN © 2015
*/

package sgr.controller;
import sgr.bean.ClientBean;
import sgr.service.ClientService;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sgr.bean.OrderBean;
import sgr.bean.SessionBean;
import sgr.bean.TableBean;
import sgr.bean.OrderItemsBean;
import sgr.dao.ExceptionDAO;
import sgr.service.SessionService;
import sgr.service.TableService;
import sgr.service.OrderService;
import sgr.util.MACReader;

@SessionScoped
@ManagedBean(name="clientController")

public class  ClientController {
    
    /* VARIAVEIS */
    // Formulário de Login 
    private String clientUsername = "";
    private String clientPassword = "";
    
    // MAC Reader
    private String currentMac = "";
    
    // Métodos Específicos
    private boolean sessionOpened;    
    String currentDate = "";
    
    // HTTP Session
    private HttpSession session;
    
    // Beans    
    ClientBean clientBean = new ClientBean();    
    TableBean tableBean = new TableBean();
    SessionBean sessionBean = new SessionBean();
    OrderBean orderBean = new OrderBean();
    OrderItemsBean orderItemsBean = new OrderItemsBean();
    
    // Lists
    List<ClientBean> listClient = new ArrayList<ClientBean>();
    List<TableBean> listTable = new ArrayList<TableBean>();
    List<SessionBean> listSession = new ArrayList<SessionBean>();
    List<OrderBean> orderList = new ArrayList<OrderBean>();
    private List<OrderItemsBean> orderItemList = new ArrayList<OrderItemsBean>();
    
    /* MÉTODOS */
    // MÉTODO 01 - doLogin()
    // Realiza login do usuário cofigurando todos os parâmetros necesários
    // para iniciar uma nova seção.
    public void doLogin() throws ExceptionDAO, UnknownHostException, SQLException, IOException {

        // Inicia Services
        ClientService clientService = new ClientService();
        TableService tableService = new TableService();
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        // Inicia HTTPSession
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        
        // ### 01 ###
        // Identifica Mesa        
        currentMac = MACReader.readMAC();
        System.out.println("[CLIENT CONTROLLER][01] MAC atual para execução em doTableSearch(): '" + currentMac + "'.");
        listTable = tableService.doTableSearch(currentMac);
        tableBean = listTable.get(0);
        System.out.println("[CLIENT CONTROLLER][01] Mesa identificada: '" + tableBean.getNumero() + "'.");
        
        // ### 02 ###
        // Busca Cliente      
        System.out.println("[CLIENT CONTROLLER][02] Dados do cliente para execução em doLogin():  clientUsername '" + clientUsername + "' e clientPassword '" + clientPassword + "'.");
        listClient = clientService.doLogin(clientUsername, clientPassword);
        clientBean = listClient.get(0);    

        // ### 03 ###
        // Valida dados do Cliente
        if(clientUsername.equals(clientBean.getNome_usuario()) && (clientPassword.equals(clientBean.getSenha()))) {

            System.out.println("[CLIENT CONTROLLER][03] Cliente encontrado!");
            System.out.println("[CLIENT CONTROLLER][03] Preparando seção...");

            // Inicia Session Service
            SessionService sessionService = new SessionService();            
            
            // ### 04 ###
            // Abertura de Seção
            
            // @ 04.1 
            // Caso não existam seções abertas uma nova é iniciada
            if(sessionService.doOpenedSessionSearch(clientBean.getCodigo(), 1) == false) {

                // Define dados da nova seção
                sessionBean.setStatus(1);
                sessionBean.setC_codigo(clientBean.getCodigo());
                sessionBean.setC_cpf(clientBean.getCpf());

                System.out.println("[CLIENT CONTROLLER][04.1] Dados carregados para nova seção: Código do Cliente '" + clientBean.getCodigo() + "' e CPF '" +
                        clientBean.getCpf() + "'.");

                // Cria nova seção
                sessionService.newSession(sessionBean);

                // Adquiri dados da nova seção
                listSession = sessionService.doOpenedSessionInfoSearch(clientBean.getCodigo(), 1);
                sessionBean = listSession.get(0);
                
                System.out.println("[CLIENT CONTROLLER][04.1] Dados da seção atual: Código da Seção '" + sessionBean.getCodigo() + "', Status '" + sessionBean.getStatus() +
                        "' e Código do Cliente '" + clientBean.getCodigo() + "'."); 
                
                    // PASSTHROUGH
                    // Carrega lista de pedidos
                    OrderService orderService = new OrderService();
                    
                    // Inicia Order Service
                    orderList = orderService.listOrders(sessionBean.getCodigo()) ;
                    
                    // PASSTHROUGH
                    // Carrega lista de items

                    // Inicia Order Service
                    setOrderItemList(orderService.listOrdersItems(sessionBean.getCodigo())) ;

                
                // Salva dados atuais no HTTPSession
                session.setAttribute("currentClientName", clientBean.getNome());
                session.setAttribute("currentUserCode", clientBean.getCodigo());
                session.setAttribute("currentTable", tableBean.getNumero());
                session.setAttribute("currentSessionCode", sessionBean.getCodigo());

            // @ 04.2
            // Caso uma seção aberta seja encontrada ela é recuperada
            } else {

                System.out.println("[CLIENT CONTROLLER][04.2] Seção aberta encontrada! Restaurando seção...");
                // Recupera dados da seção aberta
                listSession = sessionService.doOpenedSessionInfoSearch(clientBean.getCodigo(), 1);
                sessionBean = listSession.get(0);     
                System.out.println("[CLIENT CONTROLLER][04.2] Dados da seção atual: Código da Seção '" + sessionBean.getCodigo() + "', Status '" + sessionBean.getStatus() +
                        "' e Código do Cliente '" + clientBean.getCodigo() + "'.");                  
                System.out.println("[CLIENT CONTROLLER[04.2] Seção restaurada com sucesso!");
                
                    // PASSTHROUGH
                        // Carrega lista de pedidos
                        OrderService orderService = new OrderService();

                        // Inicia Order Service
                        orderList = orderService.listOrders(sessionBean.getCodigo()) ;
                        
                    // PASSTHROUGH
                        // Carrega lista de items

                        // Inicia Order Service
                        setOrderItemList(orderService.listOrdersItems(sessionBean.getCodigo())) ;
                
                // Salva dados atuais no HTTPSession
                session.setAttribute("currentClientName", clientBean.getNome());
                session.setAttribute("currentUserCode", clientBean.getCodigo());
                session.setAttribute("currentTable", tableBean.getNumero());
                session.setAttribute("currentSessionCode", sessionBean.getCodigo());
                session.setAttribute("currentUsername", clientBean.getNome_usuario());
                
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/mainclient.xhtml");

                } catch (IOException ex) {
                    System.out.println("[CLIENT CONTROLLER] ERRO: Não foi possível encontrar a página especificada.");
                    System.out.println(ex.getMessage());
                }


        } else {

            System.out.println("[CLIENT CONTROLLER] User not found or invalid access data.");        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Informações inválidas para acesso. Por favor, tente novamente!"));
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/index.xhtml");
        }

    }

    
    // MÉTODO 02 - doLogout()
    // Realiza logout do usuário caso não exista pedidos realizados e fecha
    // a conta e seção atual.
    public void doLogout() throws SQLException, ExceptionDAO, ClassNotFoundException {
        
        SessionService sessionService = new SessionService();
        FacesContext fc = FacesContext.getCurrentInstance();
        
        listSession = sessionService.doOpenedSessionInfoSearch(clientBean.getCodigo(), 1);
        sessionBean = listSession.get(0); 
        
        sessionService.closeSession(sessionBean);
        
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        s.invalidate();
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // MÉTODO 03 - goTo()
    // Método para navegação entre páginas.
    public void goTo(String page) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + page);
    }
  
    public ClientController() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dataAtual = Calendar.getInstance().getTime();
        currentDate = df.format(dataAtual);
    }
    
    // <editor-fold desc="GET and SET" defaultstate="collapsed">

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    
    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }
    
    public TableBean getTableBean() {
        return tableBean;
    }
    
    public void setSessionBean(SessionBean sessionBean){
        this.sessionBean = sessionBean;
    }
    
    public SessionBean getSessionBean(){
        return sessionBean;
    }
    
    public void setTableBean(TableBean tableBean) {
        this.tableBean = tableBean;
    }
    
    public String getCurrentMac(){
        return currentMac;
    }
    
    public void setCurrentMac(String currentMac){
        this.currentMac = currentMac;
    }
    

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }   
    
    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setListaPedido(List<OrderBean> orderList) {
        this.orderList = orderList;
    }
    // </editor-fold>   

    public List<OrderItemsBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemsBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    
    
}
