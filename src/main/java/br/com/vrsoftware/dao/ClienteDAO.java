package br.com.vrsoftware.dao;

import br.com.vrsoftware.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    private Connection conn;

    public ClienteDAO(Connection connection){
        this.conn = connection;
    }

    public void saveCliente(Cliente cliente){

        java.lang.String sql = "INSERT INTO cliente (nome) VALUES (?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());

            preparedStatement.execute();
            preparedStatement.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
