package br.com.vrsoftware.dao;

import br.com.vrsoftware.domain.Venda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendaDAO {

    private Connection conn;

    public VendaDAO(Connection connection){
        this.conn = connection;
    }

    public void saveVenda(Venda venda) {

        String sql = "INSERT INTO venda (data, cliente, valor_total, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(venda.getData()));
            preparedStatement.setString(2, venda.getCliente());
            preparedStatement.setDouble(3, venda.getValor_total());
            preparedStatement.setString(4, venda.getStatus());

            preparedStatement.execute();
            preparedStatement.close();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
