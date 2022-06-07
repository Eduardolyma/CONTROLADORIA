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
import com.eduardolyma.dcfc.models.PessoaJuridica;
import com.eduardolyma.dcfc.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public ResponseEntity<PessoaJuridica> salvar(@Valid @RequestBody PessoaJuridica pessoaJuridica, HttpServletResponse response){
		PessoaJuridica pessoaJuridicaSalva = pessoaJuridicaRepository.save(pessoaJuridica);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaJuridicaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaJuridicaSalva);
	}
	
	public ResponseEntity<PessoaJuridica> atualizar (@PathVariable Long id, @Valid @RequestBody PessoaJuridica pessoaJuridica){
		PessoaJuridica pessoaJuridicaSalva = pessoaJuridicaRepository.getOne(id);
		BeanUtils.copyProperties(pessoaJuridica, pessoaJuridicaSalva, "id");
		pessoaJuridicaRepository.save(pessoaJuridicaSalva);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridicaSalva);
	}
}
