package br.com.vrsoftware.dto;

import br.com.vrsoftware.domain.Produto;
import br.com.vrsoftware.domain.Venda;

public class ConvertVendaToDto {

    public VendaDto convertVendaToDto(Produto produto){
        VendaDto vendaDto = new VendaDto();
        vendaDto.setId_produto(produto.getId());
        vendaDto.setQuantidade(produto.getQuantidade());
        vendaDto.setPreco(produto.getPreco());
        vendaDto.setValor_total(vendaDto.valorTotal(produto.getQuantidade(), produto.getPreco()));

        return vendaDto;
    }

}
