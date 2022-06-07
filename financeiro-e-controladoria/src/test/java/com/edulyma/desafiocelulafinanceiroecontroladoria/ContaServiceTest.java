package com.eduardolyma.desafiocelulafinanceiroecontroladoria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.eduardolyma.dcfc.DesafioCelulaFinanceiroEControladoriaApplication;
import com.eduardolima.dcfc.models.Conta;
import com.eduardolyma.dcfc.service.ContaService;

@SpringBootTest(classes = DesafioCelulaFinanceiroEControladoriaApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ContaServiceTest {

	@Autowired
	private ContaService contaService;
	
	@Test
	@Transactional
	public void buscarClientePeloIdTest() {
		
		ResponseEntity<Conta> conta = contaService.buscarContaPeloNumero(5L);
		
		assertEquals(5, conta.getBody().getNumero());
		assertEquals(10853.7, conta.getBody().getSaldo());
	}
}
