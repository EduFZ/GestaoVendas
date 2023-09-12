package br.com.vrsoftware.domain;

import java.time.LocalDate;

public class Venda {

    private int id;

    private LocalDate data;

    private Cliente cliente;

    private Double valor_total;

    private String status;

    public Venda() {
    }

    public Venda(int id, LocalDate data, Cliente cliente, Double valor_total, String status) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor_total = valor_total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
