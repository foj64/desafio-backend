package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.involves.selecao.alerta.EnumTipoAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		Pesquisa[] ps = getBuscaPesquisa();

		for (int i = 0; i < ps.length; i++){
			for (int j = 0; j < ps[i].getRespostas().size(); j++){
				Resposta resposta = ps[i].getRespostas().get(j);

				situacaoProduto(resposta, ps[i]);

				precoProduto(resposta, ps[i]);

				participacaoProduto(resposta, ps[i]);

			} 
		}
	}

	private Pesquisa[] getBuscaPesquisa() throws IOException {
		URL url = new URL("https://selecao-involves.agilepromoter.com/pesquisas");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
		  new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuilder content = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		return gson.fromJson(content.toString(), Pesquisa[].class);
	}

	private void situacaoProduto(Resposta resposta, Pesquisa pesquisa) {
		if (resposta.getPergunta().equals("Qual a situação do produto?")) {
			if(resposta.getResposta().equals("Produto ausente na gondola")){
				Alerta alerta = new Alerta();
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alerta.setDescricao("Ruptura detectada!");
				alerta.setProduto(pesquisa.getProduto());
				alerta.setFlTipo(EnumTipoAlerta.RUPTURA.getValue());
				gateway.salvar(alerta);
			}
		}

	}

	private void precoProduto(Resposta resposta, Pesquisa pesquisa) {
		if(resposta.getPergunta().equals("Qual o preço do produto?")) {
			int precoColetado = Integer.parseInt(resposta.getResposta());
			int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
			if(precoColetado > precoEstipulado){
				Alerta alerta = new Alerta();
				int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
				alerta.setMargem(margem);
				alerta.setDescricao("Preço acima do estipulado!");
				alerta.setProduto(pesquisa.getProduto());
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alerta.setFlTipo(EnumTipoAlerta.ACIMA_ESTIPULADO.getValue());
				gateway.salvar(alerta);
			} else if(precoColetado < precoEstipulado){
				Alerta alerta = new Alerta();
				int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
				alerta.setMargem(margem);
				alerta.setDescricao("Preço abaixo do estipulado!");
				alerta.setProduto(pesquisa.getProduto());
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alerta.setFlTipo(EnumTipoAlerta.ABAIXO_ESTIPULADO.getValue());
				gateway.salvar(alerta);
			}
		}
	}

	private void participacaoProduto(Resposta resposta, Pesquisa pesquisa) {
		if(resposta.getPergunta().equals("%Share")) {
			int participacaoColetado = Integer.parseInt(resposta.getResposta());
			int participacaoEstipulado = Integer.parseInt(pesquisa.getParticipacao_estipulada());
			if(participacaoColetado > participacaoEstipulado){
				Alerta alerta = new Alerta();
				int margem = participacaoEstipulado - Integer.parseInt(resposta.getResposta());
				alerta.setMargem(margem);
				alerta.setDescricao("Participação superior do estipulado!");
				alerta.setCategoria(pesquisa.getCategoria());
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alerta.setFlTipo(EnumTipoAlerta.ACIMA_ESTIPULADO.getValue());
				gateway.salvar(alerta);
			} else if(participacaoColetado < participacaoEstipulado){
				Alerta alerta = new Alerta();
				int margem = participacaoEstipulado - Integer.parseInt(resposta.getResposta());
				alerta.setMargem(margem);
				alerta.setDescricao("Participação inferior do estipulado!");
				alerta.setCategoria(pesquisa.getCategoria());
				alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
				alerta.setFlTipo(EnumTipoAlerta.ABAIXO_ESTIPULADO.getValue());
				gateway.salvar(alerta);
			}
		}
	}
}

