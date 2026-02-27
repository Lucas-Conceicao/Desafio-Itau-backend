package br.com.lucasconceicao.desafioitau.Estatisticas.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstatisticasService {

    public Estatisticas setEstatisticas(List<Transacao> listaFiltrada){
        Long inicio = System.nanoTime();
        if(listaFiltrada.isEmpty()){
            log.info("Retornando estatisticas zeradas para lista vazia");
            return new Estatisticas(0l,0.0,0.0,0.0,0.0);
        }
        log.info("Retornado as estatisticas das {} transações", listaFiltrada.size());
        Estatisticas resultado = new Estatisticas(
            setCount(listaFiltrada),
            setSum(listaFiltrada),
            setAvg(listaFiltrada),
            setMin(listaFiltrada),
            setMax(listaFiltrada));
        Long fim = System.nanoTime();
        Long tempoExecucao = fim - inicio;
        log.info("Tempo para calcular as estatisticas em nanosegundos: {}", tempoExecucao);
        return resultado;
    }

    public List<Transacao> filtrarLista(OffsetDateTime tempoMin, List<Transacao> lista){
        log.info("Iniciando filtragem de {} transações para o período após {}", lista.size(), tempoMin);
        List<Transacao> listaFiltrada = new ArrayList<>();
        for(Transacao t : lista){
            if(t.getDataHora().isEqual(tempoMin) || t.getDataHora().isAfter(tempoMin))
                listaFiltrada.add(t);
        }
        log.info("Filtragem concluída. {} transações restantes.", listaFiltrada.size());
        return listaFiltrada;
    }

    public Long setCount(List<Transacao> lista){
        Long count = 0l;
        for(int x = 0; x < lista.size(); x++){
            count++;
        }
        return count;
    }

    public Double setSum(List<Transacao> lista){
        Double sum = 0.0;
        for(Transacao transacao : lista){
            sum += transacao.getValor();
        }
        return sum;
    }

    public Double setAvg(List<Transacao> lista){
        Double sum = 0.0;
        Long count = 0l;
        for(Transacao transacao : lista){
            sum += transacao.getValor();
            count++;
        }
        Double avg = sum/count;
        return avg;
    }

    public Double setMax(List<Transacao> lista){
        Double max = 0.0;

        for(Transacao t : lista)
            if(t.getValor() > max)
                max = t.getValor();
        return max;
    }

    public Double setMin(List<Transacao> lista){
        Double min = null;

        for(Transacao t : lista)
            if(min == null || t.getValor() < min)
                min = t.getValor();
        
        return min;
    }

}
