/*
 SGR ALPHA - SERVICE PACKAGE
 File: CLIENTSERVICE.JAVA | Last Major Update: 29.04.2015
 Developer: Kevin Raian, Washington Reis
 IDINALOG REBORN © 2015
 */
package sgr.service;

import sgr.bean.ClientBean;
import sgr.sql.QueryBuilder;
import sgr.sql.QueryGender;
import sgr.sql.QueryOperation;
import sgr.sql.QueryType;
import sgr.dao.ClientDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgr.dao.ExceptionDAO;

public class ClientService {

    // Login
    public List<ClientBean> doLogin(String clientUsername, String clientPassword) throws ExceptionDAO {

        List<ClientBean> currentClient = new ArrayList<ClientBean>();
        QueryBuilder query = new QueryBuilder();
        ClientDAO clientDAO = new ClientDAO();

        query.addQuery(QueryOperation.empty, "cliente.nome_usuario", QueryGender.equal, clientUsername, QueryType.text);
        query.addQuery(QueryOperation.and, "cliente.senha", QueryGender.equal, clientPassword, QueryType.text);

        try {
            try {
                System.out.println("[CLIENT SERVICE] Loading currentClient with clientDAO.loadClient...");
                currentClient = clientDAO.loadClient(query);
                System.out.println("[CLIENT SERVICE] Query loaded to ClientDAO: '" + query + "'.");
            } catch (ClassNotFoundException ex) {
                System.out.println("[CLIENT SERVICE] ERROR: Failed to load Query on ClientDAO.");
                Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {
            System.out.println("[CLIENT SERVICE] ERROR: Failed to execute currentClient query.'" + query + "'.");
            System.out.println(e.getMessage());

        }

        System.out.println("[CLIENT SERVICE] currentCliente value: '" + currentClient + "'.");
        return currentClient;
    }

    // Pull Data
    public List<ClientBean> pullClientData(String clientUsername) throws ExceptionDAO {

        List<ClientBean> currentClientData = new ArrayList<ClientBean>();
        QueryBuilder query = new QueryBuilder();
        ClientDAO clientDAO = new ClientDAO();

        query.addQuery(QueryOperation.empty, "cliente.nome_usuario", QueryGender.equal, clientUsername, QueryType.text);

        try {
            try {
                System.out.println("[CLIENT SERVICE] Loading currentClient with clientDAO.loadClient...");
                currentClientData = clientDAO.loadClient(query);
                System.out.println("[CLIENT SERVICE] Query loaded to ClientDAO: '" + query + "'.");
            } catch (ClassNotFoundException ex) {
                System.out.println("[CLIENT SERVICE] ERROR: Failed to load Query on ClientDAO.");
                Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {
            System.out.println("[CLIENT SERVICE] ERROR: Failed to execute currentClient query.'" + query + "'.");
            System.out.println(e.getMessage());

        }

        System.out.println("[CLIENT SERVICE] currentCliente value: '" + currentClientData + "'.");
        return currentClientData;

    }

    // Update Client
    public void updateClient(ClientBean client) {
        ClientDAO clientDAO = new ClientDAO();
        
        try {
            clientDAO.updateClient(client);
        } catch (ExceptionDAO ex) {
            System.out.println("[CLIENT SERVICE] ERRO: Falha ao atualizar cliente.");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            System.out.println("[CLIENT SERVICE] ERRO: Falha ao atualizar cliente.");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void newClient(ClientBean client) {
        ClientDAO clientDAO = new ClientDAO();
        
        try {
            clientDAO.newClient(client);
        } catch (ExceptionDAO ex) {
            System.out.println("[CLIENT SERVICE] ERRO: Falha ao inserir novo cliente.");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            System.out.println("[CLIENT SERVICE] ERRO: Falha ao inserir novo cliente.");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
}
