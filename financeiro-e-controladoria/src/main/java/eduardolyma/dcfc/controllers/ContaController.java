package com.eduardolyma.dcfc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.eduardolyma.dcfc.models.Conta;
import com.eduardolyma.dcfc.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public List<Conta> ListarContas(){
		return contaService.ListarContas();
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> buscarContaPeloNumero(@PathVariable Long numero){
		return contaService.buscarContaPeloNumero(numero);
	}
	
	@PostMapping
	public ResponseEntity<Conta> salvarConta(@Valid @RequestBody Conta conta, HttpServletResponse response){
		return contaService.salvarConta(conta, response);
	}
	
	@PutMapping("/{numero}")
	public ResponseEntity<Conta> atualizarConta(@PathVariable Long numero, @Valid @RequestBody Conta conta){
		return contaService.atualizarConta(numero, conta);
	}
	
	@DeleteMapping("/{numero}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerConta(@PathVariable Long numero) {
		contaService.removerConta(numero);
	}

}
