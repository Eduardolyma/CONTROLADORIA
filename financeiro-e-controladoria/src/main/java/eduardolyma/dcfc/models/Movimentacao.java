package com.eduardolyma.dcfc.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.eduardolyma.dcfc.models.enums.TipoMovimentacao;

import lombok.Data;

@Data
@Entity
@Table(name = "Movimentacao")
@SequenceGenerator(name = "SEQ_MOVIMENTACAO", sequenceName = "SEQ_MOVIMENTACAO", initialValue = 1, allocationSize = 1 )
@JsonInclude(Include.NON_NULL)
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIMENTACAO")
	private Long protocolo;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTA")
	@JsonBackReference
	private Conta conta;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	@NotNull
	private BigDecimal valor;
	private double taxa;
	
	@Column(name = "Data_Movimentacao")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataMovimentacao = LocalDate.now();

}
