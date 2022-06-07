package com.eduardolyma.dcfc.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardolyma.dcfc.models.PessoaJuridica;
import com.eduardolyma.dcfc.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoa-juridica")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	@PostMapping
	@RequestMapping("/cadastrar")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@Valid @RequestBody PessoaJuridica pessoaJuridica, HttpServletResponse response){
		return pessoaJuridicaService.salvar(pessoaJuridica, response);
	}
	
	@PutMapping("editar/{id}")
	public ResponseEntity<PessoaJuridica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaJuridica pessoaJuridica){
		return pessoaJuridicaService.atualizar(id, pessoaJuridica);
	}
}
