package br.com.lucasconceicao.desafioitau.Estatisticas.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import br.com.lucasconceicao.desafioitau.Transacao.Service.TransacaoService;

@Service
public class EstatisticasService {
    @Autowired
    private TransacaoService transacaoService;

    public Estatisticas setEstatisticas(List<Transacao> listaFiltrada){
        if(listaFiltrada.isEmpty())
            return new Estatisticas(0l,0.0,0.0,0.0,0.0);
    
        return new Estatisticas(
            setCount(listaFiltrada),
            setSum(listaFiltrada),
            setAvg(listaFiltrada),
            setMin(listaFiltrada),
            setMax(listaFiltrada));
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

    public List<Transacao> filtrarLista(OffsetDateTime tempoMin){
        List<Transacao> lista = transacaoService.getListaTransacao();
        List<Transacao> listaFiltrada = new ArrayList<>();

        for(Transacao t : lista){
            if(t.getDataHora().isAfter(tempoMin))
                listaFiltrada.add(t);
        }
        return listaFiltrada;
    }
}
