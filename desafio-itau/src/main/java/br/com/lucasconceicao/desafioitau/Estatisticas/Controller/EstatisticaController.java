package br.com.lucasconceicao.desafioitau.Estatisticas.Controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasconceicao.desafioitau.Estatisticas.Model.Estatisticas;
import br.com.lucasconceicao.desafioitau.Estatisticas.Service.EstatisticasService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/estatistica", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class EstatisticaController {
    @Autowired
    private EstatisticasService estatisticasService;
    //setar o tempo limite em segundos
    private Integer tempoLimiteEmSegundos = 60;
    
    @GetMapping
    public ResponseEntity<Estatisticas> mostrarEstatisticas(){
        OffsetDateTime tempoMin = OffsetDateTime.now().minusSeconds(tempoLimiteEmSegundos);
        log.info("mostrando estatisticas");
        return ResponseEntity.ok(estatisticasService.setEstatisticas(tempoMin));
    }
}
