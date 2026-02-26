package br.com.lucasconceicao.desafioitau.ControllerTeste;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes da classe TransacaoController")
public class TransacaoControllerTeste{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //Testando o post Salvar
    @Test
    @DisplayName("Deve tentar fazer o post de uma Transação com valor nulo. UnprocessableEntity")
    void salvarTransacao_retorna422QuandoTransacaoValorNulo() throws Exception{
        Transacao transacao = new Transacao(null, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve tentar fazer o post de uma Transação com data nula. UnprocessableEntity")
    void salvarTransacao_retorna422QuandoTransacaoDataNulo() throws Exception{
        Transacao transacao = new Transacao(1000.00,null);
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve tentar fazer o post de uma Transação com data futura. UnprocessableEntity")
    void salvarTransacao_retorna422QuandoTransacaoDataFutura() throws Exception{
        Transacao transacao = new Transacao(1000.00, OffsetDateTime.now().plusDays(1));
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());        

    }

    @Test
    @DisplayName("Deve tentar fazer o post de uma Transação com valor menor que 0. UnprocessableEntity")
    void salvarTransacao_retorna422QuandoTransacaoValorNegativo() throws Exception{
        Transacao transacao = new Transacao(-1000.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());        

    }

    @Test
    @DisplayName("Deve tentar fazer o post de uma Transação válida. Created")
    void salvarTransacao_retorna200QuandoTransacaoValida() throws Exception{
        Transacao transacao = new Transacao(1000.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());        
    }

    //Testando o delete deletar
    @Test
    @DisplayName("Deve fazer o delete e apagar todas as transações salvas. Ok")
    void deletarTransacoes_retorna200QuandoApagarTransacoes() throws Exception{
        mockMvc.perform(delete("/transacao")).andExpect(status().isOk());
    }

}