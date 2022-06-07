package com.eduardolyma.dcfc.repository.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.eduardolyma.dcfc.models.Cliente;
import com.eduardolyma.dcfc.repositorymodels.Cliente;
import com.eduardolyma.dcfc.repository.filter.ClienteFilter;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cliente> filtrar(ClienteFilter clienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		
		Root<Cliente> root = criteria.from(Cliente.class);
		
		/*
		 * Restrições
		 */
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		
		System.out.println(query.getResultList().toArray());
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {

		List<Predicate> predicates = new ArrayList<>();

		/*
		 * JPA Metamodel
		 */
		if (clienteFilter.getDataCadastroDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Cliente_.dataCadastro), clienteFilter.getDataCadastroDe()));
		}
		
		if (clienteFilter.getDataCadastroAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Cliente_.dataCadastro), clienteFilter.getDataCadastroAte()));
			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
