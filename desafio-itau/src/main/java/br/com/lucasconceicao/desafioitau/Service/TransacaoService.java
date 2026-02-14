package br.com.lucasconceicao.desafioitau.Service;

import br.com.lucasconceicao.desafioitau.Model.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private List<Transacao> listaTransacao;

    public TransacaoService() {
        listaTransacao = new ArrayList<>();
    }

    public void salvar(Transacao transacao){
        listaTransacao.add(transacao);
    }

}
