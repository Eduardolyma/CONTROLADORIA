package com.eduardolyma.dcfc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eduardolyma.dcfc.models.Cliente;
import com.eduardolyma.dcfc.repository.filter.ClienteFilter;
import com.eduardolyma.dcfc.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> pesquisar(ClienteFilter clienteFilter){
		return clienteService.pesquisar(clienteFilter);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> buscarClientePeloId(@PathVariable Long id){
		return clienteService.buscarClientePeloId(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable Long id) {
		clienteService.removerCliente(id);
	}
}
