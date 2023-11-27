package com.museu.museu.dto;

import com.museu.museu.domain.CategoriaIngresso;
import com.museu.museu.domain.Ingresso;
import com.museu.museu.domain.Vendedor;

public record DadosListagemIngressos(Integer id, Vendedor vendedor, CategoriaIngresso categoria, boolean compra_online,
        String pagamento) {
    public DadosListagemIngressos(Ingresso i) {
        this(i.getId(), i.getVendedor(), i.getCategoria(), i.getCompra_online(), i.getPagamento());
    }
}
