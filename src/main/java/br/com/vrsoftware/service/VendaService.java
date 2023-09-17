package br.com.vrsoftware.service;

import br.com.vrsoftware.ConnectionFactory;
import br.com.vrsoftware.dao.ProdutoDAO;
import br.com.vrsoftware.dao.VendaDAO;
import br.com.vrsoftware.domain.Produto;
import br.com.vrsoftware.domain.Venda;
import br.com.vrsoftware.dto.ConvertVendaToDto;
import br.com.vrsoftware.dto.VendaDto;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaService {

    private Produto produto;

    private final VendaDAO vendaDAO;

    private ConnectionFactory connectionFactory;

    public VendaService(VendaDAO vendaDAO){
        this.vendaDAO = vendaDAO;
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveVenda(Venda novaVenda) {

        try (Connection connection = connectionFactory.newConnection()) {
            vendaDAO.saveVenda(novaVenda);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
