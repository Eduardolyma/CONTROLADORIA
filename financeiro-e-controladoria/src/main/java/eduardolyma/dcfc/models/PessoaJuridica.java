package com.eduardolyma.dcfc.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("JURIDICA")
public class PessoaJuridica extends Cliente{

	@NotEmpty
	private String cnpj;
	
	@NotEmpty
	private String razaoSocial;
	
	private String nomeFantasia;

	@Transient
	private String tipo = "Pessoa Jurídica";
}
