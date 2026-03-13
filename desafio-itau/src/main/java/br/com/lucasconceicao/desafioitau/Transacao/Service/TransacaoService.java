package br.com.lucasconceicao.desafioitau.Transacao.Service;

import org.springframework.stereotype.Service;

import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Getter
public class TransacaoService {
    private List<Transacao> listaTransacao;

    public TransacaoService() {
        this.listaTransacao = Collections.synchronizedList(new ArrayList<>());
    }

    public void salvar(Transacao transacao){
        if(transacao.valor() == null || 0.0 > transacao.valor()) 
            throw new IllegalArgumentException("Valor inválido!");
        if(transacao.dataHora() == null || transacao.dataHora().isAfter(OffsetDateTime.now()))
            throw new IllegalArgumentException("Data inválida!");
        listaTransacao.add(transacao);
    }

    public void deletar(){
        listaTransacao.clear();
    }

}
