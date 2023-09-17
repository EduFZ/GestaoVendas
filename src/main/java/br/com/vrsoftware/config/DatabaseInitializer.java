package br.com.vrsoftware.config;

import br.com.vrsoftware.ConnectionFactory;

import java.sql.*;

public class DatabaseInitializer {

    private final ConnectionFactory connectionFactory;

    public DatabaseInitializer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void initializeDatabase(){
        createDatabaseIfNotExists();
        createTablesIfNotExists();
    }

    private void createDatabaseIfNotExists() {
        try {
            Connection connection = connectionFactory.newConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = connection.getMetaData().getCatalogs();
            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if ("gestao_vendas".equalsIgnoreCase(databaseName)) {
                    databaseExists = true;
                    break;
                }
            }

            if (!databaseExists) {
                String createDatabaseSQL = "CREATE DATABASE gestao_vendas";
                statement.execute(createDatabaseSQL);
            }

        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists")){
                throw new RuntimeException(e);
            }
        }
    }

    private void createTablesIfNotExists() {

        try {
            Connection connection = connectionFactory.newConnection();
            Statement statement = connection.createStatement();

            String createTableProdutoSQL = "CREATE TABLE IF NOT EXISTS produto ("
                    + "id SERIAL PRIMARY KEY,"
                    + "descricao VARCHAR(255) NOT NULL,"
                    + "preco DECIMAL(10, 2) NOT NULL,"
                    + "quantidade INT NOT NULL"
                    + ")";
            statement.execute(createTableProdutoSQL);

            String createTableClienteSQl = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(255) NOT NULL " +
                    ")";
            statement.execute(createTableClienteSQl);

            String createTableVendaSQL = "CREATE TABLE IF NOT EXISTS venda (" +
                    "id SERIAL PRIMARY KEY, " +
                    "data DATE NOT NULL, " +
                    "cliente VARCHAR(255) NOT NULL, " +
                    "valor_total DECIMAL(10, 2) NOT NULL, " +
                    "status VARCHAR(50) NOT NULL" +
                    ")";
            statement.execute(createTableVendaSQL);

            String createTableVendaItensSQL = "CREATE TABLE IF NOT EXISTS venda_item (" +
                    "id SERIAL PRIMARY KEY, " +
                    "venda_id BIGINT, " +
                    "produto_id BIGINT, " +
                    "quantidade INT, " +
                    "FOREIGN KEY (venda_id) REFERENCES venda(id), " +
                    "FOREIGN KEY (produto_id) REFERENCES produto(id)" +
                    ") ";
            statement.execute(createTableVendaItensSQL);

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
