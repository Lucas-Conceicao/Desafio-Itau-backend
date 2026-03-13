package br.com.lucasconceicao.desafioitau.ServiceTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Estatisticas.Service.EstatisticasService;
import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;

@DisplayName("Testes da classe EstatisticaService")
public class EstatisticasServiceTeste {

    private EstatisticasService estatisticasService;
    private OffsetDateTime agora, tempoMin;

    @BeforeEach
    void setup(){
        estatisticasService = new EstatisticasService();
        agora = OffsetDateTime.now();
        tempoMin = agora.minusSeconds(60);

    }

    //Testando o método setEstatisitcas
    @Test
    @DisplayName("Deve retornar as estatisticas corretamente")
    void setEstatisticas_retornaEstatisticasCorretamente(){
        List<Transacao> listaTeste = List.of(
            new Transacao(1000.00, agora),
            new Transacao(2000.00, agora)
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste, tempoMin);
        assertEquals(2L, resultado.count());
        assertEquals(3000.0, resultado.sum());
        assertEquals(1500.0, resultado.avg());
        assertEquals(2000.0, resultado.max());
        assertEquals(1000.0, resultado.min());
    }

    @Test 
    @DisplayName("Deve retornar todas as Estatisticas zeradas quando a lista recebida for vazia")
    void setEstatisticas_retornaEstatisticasZeradasQuandoNaoTiverTransacao(){
        List<Transacao> listaTeste= new ArrayList<>();
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste, tempoMin);
        assertEquals(0L, resultado.count());
        assertEquals(0.0, resultado.sum());
        assertEquals(0.0, resultado.avg());
        assertEquals(0.0, resultado.max());
        assertEquals(0.0, resultado.min());
    }

    @Test
    @DisplayName("Deve mostrar os mesmos valores para máximo e mínimo caso haja apenas uma Transacao")
    void setEstatisticas_retornaMesmoValorMinMaxSeForemIguais(){
        List<Transacao> listaTeste = List.of(
            new Transacao(2000.00, agora)
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste, tempoMin);
        assertEquals(1L, resultado.count());
        assertEquals(2000.0, resultado.sum());
        assertEquals(2000.0, resultado.avg());
        assertEquals(2000.0, resultado.max());
        assertEquals(2000.0, resultado.min());
    }
}
