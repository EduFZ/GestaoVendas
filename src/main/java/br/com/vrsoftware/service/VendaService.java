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
import java.util.List;

public class VendaService {

    private Produto produto;

    private final VendaDAO vendaDAO;

    private ConnectionFactory connectionFactory;

    public VendaService(VendaDAO vendaDAO){
        this.vendaDAO = vendaDAO;
        this.connectionFactory = new ConnectionFactory();
    }

    public void saveVenda(VendaDto venda) {

        //Criar logica para integrar VendaDto em Venda
        venda.setId_produto(produto.getId());
        venda.setQuantidade(venda.getQuantidade());
        venda.setPreco(produto.getPreco());
        venda.setValor_total(venda.getValor_total());

        Venda vendas = new Venda();
        vendas.setItens((List<VendaDto>) venda);
        vendas.setData(LocalDate.now());
        vendas.setCliente(vendas.getCliente());
        vendas.setStatus("Digitando");


        try (Connection connection = connectionFactory.newConnection()) {
            vendaDAO.saveVenda(venda);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public VendaDto adicionarItem( int id_produto, int quantidade, Double preco){
        VendaDto item = new VendaDto(id_produto, quantidade, preco);
        return item;
    }

}
