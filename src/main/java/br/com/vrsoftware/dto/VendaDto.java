package br.com.vrsoftware.dto;

import br.com.vrsoftware.domain.Cliente;
import br.com.vrsoftware.domain.Produto;

import java.time.LocalDate;

public class VendaDto {

    private Long id;

    private LocalDate dataVenda;

    private Cliente nomeCliente;

}
