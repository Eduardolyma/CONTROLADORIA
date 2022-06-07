package com.nielsonferreira.dcfc.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("FISICA")
public class PessoaFisica extends Cliente{

	@NotEmpty(message = "O campo CPF é obrigatório")
	private String cpf;
	
	@NotEmpty(message = "O campo NOME é obrigatório")
	private String nome;
	
	@Transient
	private String tipo = "Pessoa Física";

}
