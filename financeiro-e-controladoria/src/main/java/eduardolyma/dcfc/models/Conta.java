package com.eduardolyma.dcfc.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "conta")
@SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", initialValue = 11, allocationSize = 1 )
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonInclude(Include.NON_EMPTY)
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
	private Long numero;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE_PF")
	@JsonBackReference(value = "conta-clientePF")
	private PessoaFisica clientePF;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE_PJ")
	@JsonBackReference(value = "conta-clientePJ")
	private PessoaJuridica clientePJ;
	
	private double saldo;
	
	@OneToMany(mappedBy = "conta")
	@JsonManagedReference
	private List<Movimentacao> movimentacao;
	
	@Column(name = "Data_Cadastro")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataCadastro = LocalDate.now();

}
