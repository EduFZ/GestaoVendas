package br.com.vrsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection newConnection(){
        String url = "jdbc:postgresql://localhost:5432/gestao_vendas";
        String user = "postgres";
        String password = "ez110715";

        try {
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
