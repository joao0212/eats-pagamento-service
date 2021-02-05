package br.com.caelum.eats.pagamento;

import java.io.Serializable;

public class PagamentoConfirmado implements Serializable {

    private final Long id;
    private final Long pedidoId;

    public PagamentoConfirmado(Long id, Long pedidoId) {
        this.id = id;
        this.pedidoId = pedidoId;
    }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }
}
