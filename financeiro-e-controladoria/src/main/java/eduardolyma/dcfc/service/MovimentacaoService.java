package com.eduardolyma.dcfc.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.eduardolyma.dcfc.event.RecursoCriadoEvent;
import com.eduardolyma.dcfc.models.Movimentacao;
import com.neduardolyma.dcfc.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Movimentacao> listarTodas(){
		return movimentacaoRepository.findAll();
	}
	
	public ResponseEntity<Movimentacao> buscarMovimentacaoPeloProtocolo(@PathVariable Long protocolo){
		Movimentacao movimentacao = movimentacaoRepository.getOne(protocolo);
		return movimentacao != null ? ResponseEntity.ok(movimentacao) : ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Movimentacao> salvarMovimentacao(@Valid @RequestBody Movimentacao movimentacao, HttpServletResponse response){
		Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, movimentacao.getProtocolo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoSalva);
	}
	
	public ResponseEntity<Movimentacao> atualizarConta(@PathVariable Long protocolo, @Valid @RequestBody Movimentacao movimentacao){
		Movimentacao movimentacaoSalva = movimentacaoRepository.getOne(protocolo);
		BeanUtils.copyProperties(movimentacao, movimentacaoSalva, "protocolo");
		movimentacaoRepository.save(movimentacaoSalva);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoSalva);
	}
	
	public void removerMovimentacao(@PathVariable Long protocolo) {
		movimentacaoRepository.deleteById(protocolo);
	}
}
