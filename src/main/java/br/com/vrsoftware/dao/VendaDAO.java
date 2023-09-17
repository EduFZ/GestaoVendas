package br.com.vrsoftware.dao;

import br.com.vrsoftware.domain.Produto;
import br.com.vrsoftware.domain.Venda;

import java.math.BigDecimal;
import java.sql.*;

public class VendaDAO {

    private Connection conn;

    public VendaDAO(Connection connection){
        this.conn = connection;
    }

    public void saveVenda(Venda venda) {

        String sql = "INSERT INTO venda (data, cliente, valor_total, status) VALUES (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO venda_itens (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        String sqlItensUpdate = "UPDATE venda_itens SET quantidade = ?, valor_total = ? WHERE venda_id = ? AND produto_id = ?";
        String sqlQuantiaUpdate = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
        String sqlStatusUpdate = "UPDATE venda SET status = 'efetivada' WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(venda.getData()));
            preparedStatement.setObject(2, venda.getCliente());
            preparedStatement.setBigDecimal(3, venda.getValor_total());
            preparedStatement.setString(4, venda.getStatus());

            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            Long idVenda = 0L;

            if (generatedKeys.next()) {
                idVenda = generatedKeys.getLong(1);
                venda.setId(idVenda);
            }

            preparedStatement.close();

            for (Produto produto : venda.getItens()) {
                PreparedStatement preparedStatementSelectItens = conn.prepareStatement("SELECT quantidade FROM venda_itens WHERE venda_id = ? AND produto_id = ?");
                preparedStatementSelectItens.setLong(1, idVenda);
                preparedStatementSelectItens.setLong(2, produto.getId());
                ResultSet resultSet = preparedStatementSelectItens.executeQuery();

                if (resultSet.next()) {
                    Integer quantidadeExisente = resultSet.getInt("quantidade");
                    Integer novaQuantia = quantidadeExisente + produto.getQuantidade();
                    BigDecimal valorUnit = produto.getPreco();
                    BigDecimal valorTotal = valorUnit.multiply(BigDecimal.valueOf(novaQuantia));

                    PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlItensUpdate);
                    preparedStatementUpdate.setInt(1, novaQuantia);
                    preparedStatementUpdate.setBigDecimal(2, valorTotal);
                    preparedStatementUpdate.setLong(3, idVenda);
                    preparedStatementUpdate.setLong(4, produto.getId());

                    preparedStatementUpdate.executeUpdate();
                    preparedStatementUpdate.close();
                } else {
                    Integer novaQuantidade = produto.getQuantidade();
                    BigDecimal valorUnit = produto.getPreco();
                    BigDecimal valorTotal = valorUnit.multiply(BigDecimal.valueOf(novaQuantidade));

                    PreparedStatement preparedStatementInsert = conn.prepareStatement(sqlItens);
                    preparedStatementInsert.setLong(1, idVenda);
                    preparedStatementInsert.setLong(2, produto.getId());
                    preparedStatementInsert.setInt(3, novaQuantidade);
                    preparedStatementInsert.setBigDecimal(4, valorTotal);

                    preparedStatementInsert.execute();
                    preparedStatementInsert.close();
                }

                preparedStatementSelectItens.close();

                PreparedStatement preparedStatementQuantiaUpdate = conn.prepareStatement(sqlQuantiaUpdate);
                preparedStatementQuantiaUpdate.setInt(1, produto.getQuantidade());
                preparedStatementQuantiaUpdate.setLong(2, produto.getId());

                preparedStatementQuantiaUpdate.executeUpdate();
                preparedStatementQuantiaUpdate.close();

            }

            PreparedStatement preparedStatementStatusUpdate = conn.prepareStatement(sqlStatusUpdate);
            preparedStatementStatusUpdate.setLong(1, idVenda);

            preparedStatementStatusUpdate.executeUpdate();
            preparedStatementStatusUpdate.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
