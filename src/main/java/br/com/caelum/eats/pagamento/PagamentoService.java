package br.com.caelum.eats.pagamento;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private NotificadorPagamentoConfirmado notificadorPagamentoConfirmado;

	public PagamentoDto detalhar(Long id) {
		Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return transformarParaDTO(pagamento);
	}

	public ResponseEntity<PagamentoDto> criar(PagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
		Pagamento pagamento = transformarParaObjeto(pagamentoDto);
		pagamento.setStatus(Status.CRIADO);
		Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoSalvo.getId()).toUri();
		return ResponseEntity.created(path).body(transformarParaDTO(pagamentoSalvo));
	}

	public PagamentoDto confirmar(Long id) {
		Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pagamento.setStatus(Status.CONFIRMADO);
		pagamentoRepository.save(pagamento);
		//pedidoService.pagar(pagamento.getPedidoId());

		notificadorPagamentoConfirmado.notificarPagamentoConfirmado(pagamento);

		return transformarParaDTO(pagamento);
	}

	public PagamentoDto cancelar(Long id) {
		Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		pagamento.setStatus(Status.CANCELADO);
		pagamentoRepository.save(pagamento);
		return transformarParaDTO(pagamento);
	}

	private PagamentoDto transformarParaDTO(Pagamento pagamento) {
		return new PagamentoDto(pagamento.getId(), pagamento.getValor(), pagamento.getNome(), pagamento.getNumero(),
				pagamento.getExpiracao(), pagamento.getCodigo(), pagamento.getStatus(),
				pagamento.getFormaDePagamentoId(), pagamento.getPedidoId());
	}

	private Pagamento transformarParaObjeto(PagamentoDto pagamentoDto) {
		return new Pagamento(pagamentoDto.getId(), pagamentoDto.getValor(), pagamentoDto.getNome(),
				pagamentoDto.getNumero(), pagamentoDto.getExpiracao(), pagamentoDto.getCodigo(),
				pagamentoDto.getStatus(), pagamentoDto.getPedidoId(), pagamentoDto.getFormaDePagamentoId());
	}
}
