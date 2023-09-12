package br.com.vrsoftware;

import br.com.vrsoftware.GUI.CadastroProduto;
import br.com.vrsoftware.dao.ProdutoDAO;
import br.com.vrsoftware.service.ProdutoService;

import java.sql.Connection;

public class GestaoVendas {
    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        ProdutoService produtoService = new ProdutoService(produtoDAO);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CadastroProduto cadastroProduto = new CadastroProduto(produtoService);
                cadastroProduto.setVisible(true);
            }
        });

    }
}