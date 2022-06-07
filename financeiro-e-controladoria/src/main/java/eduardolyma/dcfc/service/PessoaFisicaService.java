package com.eduardolyma.dcfc.service;

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
import com.eduardolyma.dcfc.models.PessoaFisica;
import com.eduardolyma.dcfc.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public ResponseEntity<PessoaFisica> salvar(@Valid @RequestBody PessoaFisica pessoaFisica, HttpServletResponse response){
		PessoaFisica pessoaFisicaSalva = pessoaFisicaRepository.save(pessoaFisica);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaFisicaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaFisicaSalva);
	}
	
	public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisica pessoaFisica){
		PessoaFisica pessoaFisicaSalva = pessoaFisicaRepository.getOne(id);
		BeanUtils.copyProperties(pessoaFisica, pessoaFisicaSalva, "id");
		pessoaFisicaRepository.save(pessoaFisicaSalva);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaFisicaSalva);
	}
	
	
}
