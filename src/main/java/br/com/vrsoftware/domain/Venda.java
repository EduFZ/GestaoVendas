package br.com.vrsoftware.domain;

import br.com.vrsoftware.dto.VendaDto;

import java.lang.String;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private Long id;

    private LocalDate data;

    private Cliente cliente;

    private BigDecimal valor_total;

    private String status;

    private List<Produto> itens = new ArrayList<>();

    public Venda() {
    }

    public Venda(Long id, LocalDate data, Cliente cliente, BigDecimal valor_total, String status, List<Produto> itens) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor_total = valor_total;
        this.status = status;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", valor_total=" + valor_total +
                ", status='" + status + '\'' +
                ", itens=" + itens +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(Produto itens) {
        this.itens.add(itens);
    }


//    public static void main(String[] args) {
//        Produto produto1 = new Produto(1L, "Sal", new BigDecimal(5.0), 4);
//        Produto produto2 = new Produto(2L, "Arroz", new BigDecimal(15.0), 2);
//
//        Venda novaVenda = new Venda();
//        novaVenda.setData(LocalDate.now());
//        novaVenda.setCliente(new Cliente("Fulano"));
//        novaVenda.setItens(produto1);
//        novaVenda.setItens(produto2);
//
//        BigDecimal totalValue = novaVenda.getItens().stream()
//                .map(x -> x.getPreco())
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        novaVenda.setValor_total(totalValue);
//        novaVenda.setStatus("Digitando");
//
//        System.out.println(novaVenda);
//    }

}
