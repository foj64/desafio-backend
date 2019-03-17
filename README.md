# Desafio

Back-end da aplicação 

## Como funciona

Esse projeto busca dados dos pontos de venda através do endereço `http://selecao-involves.agilepromoter.com/pesquisas` e transforma os mesmos em informações estratégicas. Essas informações são salvas em um banco de dados para posterior análise.

**Atenção: Você precisa do Mongo DB instalado e rodando para prosseguir**

1. Rode o sistema
``` 
gradlew bootRun
```
2. Abra o navegador em `http://localhost:8080/alertas/processar` para que o dados das lojas sejam processados. 

3. Abra o navegador em `http://localhost:8080/alertas/pesquisar` e passe os parametros pontoDeVenda e tipo para filtrar.
