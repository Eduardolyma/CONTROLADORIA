package com.eduardolima.dcfc.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ClienteFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastroDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastroAte;
	
	public LocalDate getDataCadastroDe() {
		return dataCadastroDe;
	}
	public void setDataCadastroDe(LocalDate dataCadastroDe) {
		this.dataCadastroDe = dataCadastroDe;
	}
	public LocalDate getDataCadastroAte() {
		return dataCadastroAte;
	}
	public void setDataCadastroAte(LocalDate dataCadastroAte) {
		this.dataCadastroAte = dataCadastroAte;
	}
}
