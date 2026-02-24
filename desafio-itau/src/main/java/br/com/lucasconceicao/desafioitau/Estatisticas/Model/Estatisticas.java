package br.com.lucasconceicao.desafioitau.Estatisticas.Model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estatisticas {
    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

}
