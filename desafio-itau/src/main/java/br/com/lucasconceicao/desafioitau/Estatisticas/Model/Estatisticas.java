package br.com.lucasconceicao.desafioitau.Estatisticas.Model;
import java.util.DoubleSummaryStatistics;

public record Estatisticas (long count, double sum, double avg, double min, double max) {

    public Estatisticas(DoubleSummaryStatistics stats){
        this(
            stats.getCount(),
            stats.getSum(),
            stats.getAverage() == 0 ? 0.0 : stats.getAverage(),
            stats.getMin() == Double.POSITIVE_INFINITY ? 0.0 : stats.getMin(),
            stats.getMax() == Double.NEGATIVE_INFINITY ? 0.0 : stats.getMax()
        );
    }
    
    public Estatisticas(){
        this(0, 0.0, 0.0, 0.0, 0.0);
    }

}
