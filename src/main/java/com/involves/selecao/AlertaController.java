package com.involves.selecao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.service.BuscaAlertasService;
import com.involves.selecao.service.ProcessadorAlertas;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasService buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertas processador;
	
	@GetMapping
    public List<Alerta> alertas() {
		return buscaAlertasService.buscarTodos();
		}
		
	@GetMapping("/pesquisar")
		public List<Alerta> pesquisarAlertas(@RequestParam(value="pontoDeVenda", required=false, defaultValue="") String pontoDeVenda, 
																				 @RequestParam(value="tipo", required=false, defaultValue="") String tipo) {
		return buscaAlertasService.buscar(pontoDeVenda, tipo);
  }
	
	@GetMapping("/processar")
    public void processar() {
		try {
			processador.processa();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
