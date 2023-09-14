package br.com.vrsoftware.dto;

import br.com.vrsoftware.domain.Produto;

public class VendaDto {
    private int id_produto;

    private int quantidade;

    private Double preco;

    private Double valor_total;

    public VendaDto() {
    }

    public VendaDto(int id_produto, int quantidade, Double preco) {
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.valor_total = quantidade * preco;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

}
