package br.com.lucasconceicao.desafioitau.ControllerTeste;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTeste{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retorna422QuandoTransacaoValorNulo() throws Exception{
        Transacao transacao = new Transacao(null, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void retorna422QuandoTransacaoDataNulo() throws Exception{
        Transacao transacao = new Transacao(1000.00,null);
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void retorna422QuandoTransacaoDataFutura() throws Exception{
        Transacao transacao = new Transacao(1000.00, OffsetDateTime.now().plusDays(1));
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());        

    }

    @Test
    void retorna422QuandoTransacaoValorNegativo() throws Exception{
        Transacao transacao = new Transacao(-1000.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnprocessableEntity());        

    }

    @Test
    void retorna200QuandoTransacaoValida() throws Exception{
        Transacao transacao = new Transacao(1000.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);

        mockMvc.perform(post("/transacao")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());        
    }

    @Test
    void Retorna200QuandoApagarTransacoes() throws Exception{
        mockMvc.perform(delete("/transacao")).andExpect(status().isOk());
    }

}