package br.com.lucasconceicao.desafioitau.Transacao.Model;

import java.time.OffsetDateTime;

public record Transacao(Double valor, OffsetDateTime dataHora ) {
}
