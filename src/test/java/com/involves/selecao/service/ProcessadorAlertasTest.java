package com.involves.selecao.service;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.involves.selecao.alerta.Alerta;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessadorAlertasTest {

    private Alerta alerta, alerta2;

    @Autowired
    private ProcessadorAlertas processadorAlertas;

    private List<Alerta> alertas;

    @Before
    public void setUp() {
        alerta = new Alerta();
        // seta os dados do alerta
        alerta.setPontoDeVenda("Padaria do Alemão");
        alerta.setDescricao("Participação inferior do estipulado!");
        alerta.setProduto("");
        alerta.setCategoria("Refrigerantes");
        alerta.setFlTipo(3);
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

    }

    @Test
    public void processa() throws IOException {
        setUp();
        
        processadorAlertas.processa();
        assertEquals("","");
    }
}