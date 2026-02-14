package br.com.lucasconceicao.desafioitau.Controller;

import br.com.lucasconceicao.desafioitau.Model.Transacao;
import br.com.lucasconceicao.desafioitau.Service.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/desafio")
@Slf4j
public class TransacaoController {
    private final TransacaoService transacaoservice;

    @Autowired
    public TransacaoController(TransacaoService transacaoservice) {this.transacaoservice = transacaoservice;}

    @PostMapping("/transacao")
    public ResponseEntity<Transacao> salvar(@RequestBody Transacao transacao){
        log.info("lançando transação");
        transacaoservice.salvar(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }
}
