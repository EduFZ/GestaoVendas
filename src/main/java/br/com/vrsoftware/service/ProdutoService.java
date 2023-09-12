package br.com.vrsoftware.service;

import br.com.vrsoftware.ConnectionFactory;
import br.com.vrsoftware.dao.ProdutoDAO;
import br.com.vrsoftware.domain.Produto;

import java.sql.Connection;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    private ConnectionFactory connectionFactory;

    public ProdutoService(ProdutoDAO produtoDAO){
        this.produtoDAO = produtoDAO;
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveProduto(Produto produto) {
        try (Connection connection = connectionFactory.newConnection()) {
            produtoDAO.saveProduto(produto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
