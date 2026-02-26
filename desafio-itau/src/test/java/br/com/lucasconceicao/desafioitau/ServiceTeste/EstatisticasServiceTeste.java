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
    private OffsetDateTime agora;

    @BeforeEach
    void setup(){
        estatisticasService = new EstatisticasService();
        agora = OffsetDateTime.now();

    }

    //Testando o método setEstatisitcas
    @Test
    @DisplayName("Deve retornar as estatisticas corretamente")
    void setEstatisticas_retornaEstatisticasCorretamente(){
        List<Transacao> listaTeste = List.of(
            new Transacao(1000.00, agora),
            new Transacao(2000.00, agora)
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(2L, resultado.getCount());
        assertEquals(3000.0, resultado.getSum());
        assertEquals(1500.0, resultado.getAvg());
        assertEquals(2000.0, resultado.getMax());
        assertEquals(1000.0, resultado.getMin());
    }

    @Test 
    @DisplayName("Deve retornar todas as Estatisticas zeradas quando a lista recebida for vazia")
    void setEstatisticas_retornaEstatisticasZeradasQuandoNaoTiverTransacao(){
        List<Transacao> listaTeste= new ArrayList<>();
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(0L, resultado.getCount());
        assertEquals(0.0, resultado.getSum());
        assertEquals(0.0, resultado.getAvg());
        assertEquals(0.0, resultado.getMax());
        assertEquals(0.0, resultado.getMin());
    }

    @Test
    @DisplayName("Deve mostrar os mesmos valores para máximo e mínimo caso haja apenas uma Transacao")
    void setEstatisticas_retornaMesmoValorMinMaxSeForemIguais(){
        List<Transacao> listaTeste = List.of(
            new Transacao(2000.00, agora)
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(1L, resultado.getCount());
        assertEquals(2000.0, resultado.getSum());
        assertEquals(2000.0, resultado.getAvg());
        assertEquals(2000.0, resultado.getMax());
        assertEquals(2000.0, resultado.getMin());
    }

    //Testando o método filtrarLista
    @Test
    @DisplayName("Deve retornar uma lista filtrada com base no valor mínimo escolhido")
    void filtrarLista_retornaListaFiltrada(){
        Integer tempoLimiteEmSegundos = 60;
        OffsetDateTime tempoMin = agora.minusSeconds(tempoLimiteEmSegundos);
        List<Transacao> listaTeste = List.of(
            new Transacao(1000.00, agora),
            new Transacao(2000.00, agora),
            new Transacao(3000.00, agora),
            new Transacao(4000.00, agora),
            new Transacao(5000.00, agora.minusHours(1)),
            new Transacao(6000.00, agora.minusHours(1))
        );
        List<Transacao> esperado = List.of(
            new Transacao(1000.00, agora),
            new Transacao(2000.00, agora),
            new Transacao(3000.00, agora),
            new Transacao(4000.00, agora)
        );
        List<Transacao> resultado = estatisticasService.filtrarLista(tempoMin, listaTeste);
        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia caso não haja Transações no intervalo escolhido")
    void filtrarLista_retornaListaVazia(){
        Integer tempoLimiteEmSegundos = 60;
        OffsetDateTime tempoMin = agora.minusSeconds(tempoLimiteEmSegundos);
        List<Transacao> listaTeste = List.of(
            new Transacao(1000.00, agora.minusHours(1)),
            new Transacao(2000.00, agora.minusHours(1)),
            new Transacao(3000.00, agora.minusHours(1)),
            new Transacao(4000.00, agora.minusHours(1)),
            new Transacao(5000.00, agora.minusHours(1)),
            new Transacao(6000.00, agora.minusHours(1))
        );
        List<Transacao> esperado = new ArrayList<>();
        List<Transacao> resultado = estatisticasService.filtrarLista(tempoMin, listaTeste);
        assertEquals(esperado, resultado);
    }

}
