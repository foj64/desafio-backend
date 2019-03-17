package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.alerta.Alerta;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
	List<Alerta> buscar(String pontoDeVenda, String tipo);
}
