package com.eduardolyma.dcfc.repository.cliente;

import java.util.List;

import com.eduardolyma.dcfc.models.Cliente;
import com.eduardolyma.dcfc.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public List<Cliente> filtrar(ClienteFilter clienteFilter);
}
