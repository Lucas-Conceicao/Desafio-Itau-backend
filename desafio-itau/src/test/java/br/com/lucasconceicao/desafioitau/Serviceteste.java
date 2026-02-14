package br.com.lucasconceicao.desafioitau;

import br.com.lucasconceicao.desafioitau.Model.Transacao;
import br.com.lucasconceicao.desafioitau.Service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste do Service")
public class Serviceteste {
    private Transacao transacao;
    private TransacaoService transacaoservice;

    @BeforeEach
    void setup(){
        transacao = new Transacao();
        transacaoservice = new TransacaoService();
    }

    @Test
    @DisplayName("Adicionando Transação com valor menor que 0")
    void adicionar_transacao_valor_menor_zero(){
        transacaoservice.salvar(transacao);
    }

    @Test
    @DisplayName("Adicionando Transação com valor igual que 0")
    void adicionar_transacao_valor_igual_zero(){

    }

    @Test
    @DisplayName("Adicionando Transação com valor maior que 0")
    void adicionar_transacao_valor_maior_zero(){

    }

    @Test
    @DisplayName("Adicionando Transação com data futura")
    void adicionar_transacao_futura(){

    }

    @Test
    @DisplayName("Adicionando Transação com data presente")
    void adicionar_transacao_presente(){

    }

    @Test
    @DisplayName("Adicionando Transação com data passada")
    void adicionar_transacao_passada(){

    }
}
