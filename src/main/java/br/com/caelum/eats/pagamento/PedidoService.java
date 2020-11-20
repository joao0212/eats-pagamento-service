package br.com.caelum.eats.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("monolito")
public interface PedidoService {

	@PutMapping("/pedidos/{id}")
	public void pagar(@PathVariable("id") Long id);

}
