package br.com.lucasconceicao.desafioitau.Model;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private BigDecimal valor;
    private OffsetDateTime datahora;
}
