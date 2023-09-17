package br.com.vrsoftware.dao;

import br.com.vrsoftware.ConnectionFactory;
import br.com.vrsoftware.domain.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            preparedStatement.setBigDecimal(2, produto.getPreco());
            preparedStatement.setInt(3, produto.getQuantidade());

            preparedStatement.execute();
            preparedStatement.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto findProdutoById(Long id) {

        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ? ";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Long findId = resultSet.getLong("id");
                String descricao = resultSet.getString("descricao");
                BigDecimal preco = resultSet.getBigDecimal("preco");
                Integer quantidade = resultSet.getInt("quantidade");

                produto = new Produto(findId, descricao, preco, quantidade);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produto;

    }

}
