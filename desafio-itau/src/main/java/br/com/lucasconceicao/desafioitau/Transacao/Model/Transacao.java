package br.com.lucasconceicao.desafioitau.Transacao.Model;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private Double valor;
    private OffsetDateTime dataHora;
}
