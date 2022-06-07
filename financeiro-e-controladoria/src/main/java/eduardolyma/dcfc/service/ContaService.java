package com.edurdolyma.dcfc.service;

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
import com.eduardolyma.dcfc.models.Conta;
import com.eduardolyma.dcfc.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public List<Conta> ListarContas(){
		return contaRepository.findAll();
	}

	public ResponseEntity<Conta> buscarContaPeloNumero(@PathVariable Long numero){
		Conta conta = contaRepository.getOne(numero);
		return conta != null ? ResponseEntity.ok(conta) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Conta> salvarConta(@Valid @RequestBody Conta conta, HttpServletResponse response){
		Conta contaSalva = contaRepository.save(conta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, conta.getNumero()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
		
	}

	public ResponseEntity<Conta> atualizarConta(@PathVariable Long numero, @Valid @RequestBody Conta conta){
		Conta contaSalva = contaRepository.getOne(numero);
		BeanUtils.copyProperties(conta, contaSalva, "numero");
		contaRepository.save(contaSalva);
		return ResponseEntity.status(HttpStatus.OK).body(contaSalva);
	}

	public void removerConta(@PathVariable Long numero) {
		contaRepository.deleteById(numero);
	}

}
