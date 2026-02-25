package br.com.lucasconceicao.desafioitau.ServiceTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Estatisticas.Service.EstatisticasService;
import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;

public class EstatisticasServiceTeste {

    private EstatisticasService estatisticasService = new EstatisticasService();
    private Integer tempoLimiteEmSegundos;

    @Test
    void retornaEstatisticasCorretamente(){
        List<Transacao> listaTeste = List.of(
            new Transacao(1000.00, OffsetDateTime.now()),
            new Transacao(2000.00, OffsetDateTime.now())
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(2L, resultado.getCount());
        assertEquals(3000.0, resultado.getSum());
        assertEquals(1500.0, resultado.getAvg());
        assertEquals(2000.0, resultado.getMax());
        assertEquals(1000.0, resultado.getMin());
        
    }

    @Test 
    void retornaEstatisticasZeradasQuandoNaoTiverTransacao(){
        List<Transacao> listaTeste= new ArrayList<>();
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(0L, resultado.getCount());
        assertEquals(0.0, resultado.getSum());
        assertEquals(0.0, resultado.getAvg());
        assertEquals(0.0, resultado.getMax());
        assertEquals(0.0, resultado.getMin());

    }

    @Test
    void retornaMesmoValorMinMaxSeForemIguais(){
        List<Transacao> listaTeste = List.of(
            new Transacao(2000.00, OffsetDateTime.now()),
            new Transacao(2000.00, OffsetDateTime.now())
        );
        Estatisticas resultado = estatisticasService.setEstatisticas(listaTeste);
        assertEquals(2L, resultado.getCount());
        assertEquals(4000.0, resultado.getSum());
        assertEquals(2000.0, resultado.getAvg());
        assertEquals(2000.0, resultado.getMax());
        assertEquals(2000.0, resultado.getMin());
    }

    @Test
    void retornaListaFiltrada(){
        
    }

}
