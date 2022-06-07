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

import com.eduardolyma.dcfc.models.PessoaFisica;
import com.eduardolyma.dcfc.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoa-fisica")
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	
	@PostMapping
	@RequestMapping("/cadastrar")
	public ResponseEntity<PessoaFisica> salvar(@Valid @RequestBody PessoaFisica pessoaFisica, HttpServletResponse response){
		return pessoaFisicaService.salvar(pessoaFisica, response);
	}
	
	@PutMapping
	@RequestMapping("editar/{id}")
	public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisica pessoaFisica){
		return pessoaFisicaService.atualizar(id, pessoaFisica);
	}
}
