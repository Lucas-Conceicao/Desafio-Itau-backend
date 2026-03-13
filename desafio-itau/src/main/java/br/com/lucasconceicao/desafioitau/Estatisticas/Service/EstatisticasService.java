package br.com.lucasconceicao.desafioitau.Estatisticas.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstatisticasService {

    public Estatisticas setEstatisticas(List<Transacao> lista, OffsetDateTime tempoMin){
        Long inicio = System.nanoTime();

        if(lista.isEmpty() || lista == null){
            log.info("Retornando estatisticas zeradas para lista vazia ou nula");
            return new Estatisticas();
        }

        log.info("Retornado as estatisticas das {} transações", lista.size());

        DoubleSummaryStatistics stats = lista.
        parallelStream().filter(t -> !t.dataHora().isBefore(tempoMin)).
        mapToDouble(Transacao::valor).summaryStatistics();

        Long fim = System.nanoTime();
        Long tempoExecucao = fim - inicio;
        log.info("Tempo para calcular as estatisticas em nanosegundos: {}", tempoExecucao);

        return new Estatisticas(stats);
    }
}
