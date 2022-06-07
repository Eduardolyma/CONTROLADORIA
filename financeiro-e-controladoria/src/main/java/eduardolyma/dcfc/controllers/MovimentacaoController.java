package com.eduardolyma.dcfc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eduardolyma.dcfc.models.Movimentacao;
import com.eduardolyma.dcfc.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

	/*
	 * Injetando dependências pelo construtor
	 */
	public MovimentacaoService movimentacaoService;

	public MovimentacaoController(MovimentacaoService movimentacaoService) {
		this.movimentacaoService = movimentacaoService;
	}
	
	@GetMapping
	public List<Movimentacao> listarTodas(){
		return movimentacaoService.listarTodas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movimentacao> buscarMovimentacaoPeloProtocolo(@PathVariable Long protocolo){
		return movimentacaoService.buscarMovimentacaoPeloProtocolo(protocolo);
	}
	
	@PostMapping
	@RequestMapping("/cadastrar")
	public ResponseEntity<Movimentacao> salvarMovimentacao(@Valid @RequestBody Movimentacao movimentacao, HttpServletResponse response){
		return movimentacaoService.salvarMovimentacao(movimentacao, response);
	}
	
	@PutMapping
	@RequestMapping("editar/{id}")
	public ResponseEntity<Movimentacao> atualizarConta(@PathVariable Long protocolo, @Valid @RequestBody Movimentacao movimentacao){
		return movimentacaoService.atualizarConta(protocolo, movimentacao);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerMovimentacao(@PathVariable Long protocolo) {
		movimentacaoService.removerMovimentacao(protocolo);
	}
}
