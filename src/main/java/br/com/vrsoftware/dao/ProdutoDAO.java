package br.com.vrsoftware.dao;

import br.com.vrsoftware.ConnectionFactory;
import br.com.vrsoftware.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO(Connection connection){
        this.conn = connection;
    }

    public void saveProduto(Produto produto) {

        String sql = "INSERT INTO produto (descricao, preco, quantidade) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(produto.getDescricao()));
            preparedStatement.setDouble(2, produto.getPreco());
            preparedStatement.setInt(3, produto.getQuantidade());

            preparedStatement.execute();
            preparedStatement.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
