package br.com.lucasconceicao.desafioitau.ControllerTeste;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;

@SpringBootTest
@AutoConfigureMockMvc
public class EstatisticasControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void retornaOkQuandoEstatisticasApresentadas() throws Exception{
        Estatisticas estatisticas = new Estatisticas(10l,5436.0,1977.0,500.0,700.00);
        String json = objectMapper.writeValueAsString(estatisticas);

        mockMvc.perform(get("/estatistica")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
}
