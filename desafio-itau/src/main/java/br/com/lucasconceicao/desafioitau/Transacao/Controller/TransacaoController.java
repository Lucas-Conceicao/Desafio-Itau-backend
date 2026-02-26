package br.com.lucasconceicao.desafioitau.Transacao.Controller;

import br.com.lucasconceicao.desafioitau.Transacao.Model.Transacao;
import br.com.lucasconceicao.desafioitau.Transacao.Service.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/transacao")
@Slf4j
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoservice;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transacao> salvarTransacao(@RequestBody Transacao transacao){
        log.info("lançando transação");
        try{
        transacaoservice.salvar(transacao);
        log.info("Transação salva com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(IllegalArgumentException e){
            log.warn("Tentativa de transação inválida: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }catch(Exception e){
            log.error("Erro inesperado ao tentar processar a transação: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deletarTransacoes(){
        transacaoservice.deletar();
        log.info("Limpeza de transações solicitada. Todos os dados foram apagados.");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
