package com.eduardolyma.dcfc.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
@DiscriminatorColumn(name = "Tipo_Pessoa", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", initialValue = 11, allocationSize = 1 )
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonInclude(Include.NON_EMPTY)
public abstract class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	private Long id;
	
	@Embedded
	private Endereco endereco;
	
	private String telefone;
	
    @OneToMany(mappedBy = "clientePF")
    private List<Conta> contasPF;
    
    @OneToMany(mappedBy = "clientePJ")
    private List<Conta> contasPJ;
	
	@Column(name = "Data_Cadastro")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataCadastro = LocalDate.now();
	
}
