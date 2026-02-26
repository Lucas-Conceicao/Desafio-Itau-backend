package br.com.lucasconceicao.desafioitau.ServiceTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import br.com.lucasconceicao.desafioitau.Transacao.Service.TransacaoService;

@DisplayName("Testes da class TransacaoService")
public class TransacaoServiceTeste {
    @Autowired
    private TransacaoService transacaoService;
    private OffsetDateTime agora;

    @BeforeEach
    void setup(){
        transacaoService = new TransacaoService();
        agora = OffsetDateTime.now();
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException quando o valor da Transação for nulo")
    void salvar_retornaERROQuandoTransacaoValorNulo(){
        Transacao transacao = new Transacao(null, agora);
        IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> transacaoService.salvar(transacao));
        assertTrue(exception.getMessage().contains("Valor inválido"));
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException quando a data da Transação for nula")
    void salvar_retornaERROQuandoTransacaoDataNulo(){
        Transacao transacao = new Transacao(10.0, null);
        IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> transacaoService.salvar(transacao));
        assertTrue(exception.getMessage().contains("Data inválida"));
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException quando o valor da Transação for negativo")
    void salvar_retornaERROQuandoTransacaoValorNegativo(){
        Transacao transacao = new Transacao(-10.0, agora);
        IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> transacaoService.salvar(transacao));
        assertTrue(exception.getMessage().contains("Valor inválido"));
    }

    @Test
    @DisplayName("Deve retornar IllegalArgumentException quando a data da Transação for no futuro")
    void salvar_retornaERROQuandoTransacaoDataFutura(){
        Transacao transacao = new Transacao(10.0, agora.plusDays(1));
        IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> transacaoService.salvar(transacao));
        assertTrue(exception.getMessage().contains("Data inválida"));
    }

    @Test
    @DisplayName("Deve retronar ok quando Transação for válida")
    void salvar_retornaOKQuandoTransacaoValida(){
        Transacao transacao = new Transacao(10.0, agora);
        transacaoService.salvar(transacao);
        assertEquals(1, transacaoService.getListaTransacao().size());
        
    }
}
