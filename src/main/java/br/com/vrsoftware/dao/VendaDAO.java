package br.com.vrsoftware.dao;

import br.com.vrsoftware.domain.Produto;
import br.com.vrsoftware.domain.Venda;

import java.sql.*;

public class VendaDAO {

    private Connection conn;

    public VendaDAO(Connection connection){
        this.conn = connection;
    }

    public void saveVenda(Venda venda) {

        String sql = "INSERT INTO venda (data, cliente, valor_total, status) VALUES (?, ?, ?, ?)";
        String sqlItens = "INSERT INTO venda_itens (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
                PreparedStatement preparedStatementItens = conn.prepareStatement(sqlItens);
                preparedStatementItens.setLong(1, idVenda);
                preparedStatementItens.setLong(2, produto.getId());
                preparedStatementItens.setInt(3, produto.getQuantidade());

                preparedStatementItens.execute();
                preparedStatementItens.close();
            }

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
