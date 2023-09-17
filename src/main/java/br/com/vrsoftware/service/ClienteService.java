package br.com.vrsoftware.service;

import br.com.vrsoftware.ConnectionFactory;
import br.com.vrsoftware.dao.ClienteDAO;
import br.com.vrsoftware.domain.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    private ConnectionFactory connectionFactory;

    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveCliente(Cliente cliente){
        try(Connection connection = connectionFactory.newConnection()) {
            clienteDAO.saveCliente(cliente);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
