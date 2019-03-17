package com.involves.selecao.gateway;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.involves.selecao.alerta.Alerta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertaMongoGatewayTest {

    @Autowired
    private AlertaMongoGateway alertaMongoGateway;

    private Alerta alerta, alerta2;
    private List<Alerta> alertas;

    @Before
    public void setUp() {
        alerta = new Alerta();
        // seta os dados do alerta
        alerta.setPontoDeVenda("Padaria do Alemão");
        alerta.setDescricao("Participação superior do estipulado!");
        alerta.setProduto("");
        alerta.setCategoria("Refrigerantes");
        alerta.setFlTipo(2);
        alerta.setMargem(2);

        alerta2 = new Alerta();
        // seta os dados do alerta
        alerta2.setPontoDeVenda("Padaria do seu João");
        alerta2.setDescricao("Preço abaixo do estipulado!");
        alerta2.setProduto("vo de Pascoa Kinder 48");
        alerta2.setCategoria("");
        alerta2.setFlTipo(3);
        alerta2.setMargem(5);

        // preenche a lista com alertas
        alertas = new ArrayList<>();
        alertas.add(alerta);
        alertas.add(alerta2);

        // Limpa o Banco?

    }

    @Test
    public void salvar() {
        setUp();

        // Testa o salvar
        alertaMongoGateway.salvar(alerta);
        alertaMongoGateway.salvar(alerta2);

        // Testa o buscar todos
        alertas = alertaMongoGateway.buscarTodos();
        assertEquals(alertas.size(), 2);
    }

    @Test
    public void buscarTipo() {
        setUp();

        alertaMongoGateway.salvar(alerta);
        alertaMongoGateway.salvar(alerta2);
        // Testa o buscar
        alertas = alertaMongoGateway.buscar("Padaria do seu João","3");

        assertEquals(alertas.size(), 1);
    }
}