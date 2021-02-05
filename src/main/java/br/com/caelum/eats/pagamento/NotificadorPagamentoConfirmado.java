package br.com.caelum.eats.pagamento;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificadorPagamentoConfirmado {

    private final RabbitTemplate rabbitTemplate;

    public NotificadorPagamentoConfirmado(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    void notificarPagamentoConfirmado(Pagamento pagamento) {
        Long pagamentoId = pagamento.getId();
        Long pedidoId = pagamento.getPedidoId();

        PagamentoConfirmado pagamentoConfirmado = new PagamentoConfirmado(pagamentoId, pedidoId);

        rabbitTemplate.convertAndSend(AmqpPagamentoConfig.topicName, "pagamentos", pagamentoConfirmado);
    }

}
