package com.github.ewersonc.gamestore.Controller;
import com.github.ewersonc.gamestore.Entity.Jogos;
import com.github.ewersonc.gamestore.Service.JogosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/jogos")
public class JogosController {
    @Autowired
    private JogosService jogosService;

    @GetMapping
    public ResponseEntity<List<Jogos>> listarJogos(){
        return ResponseEntity.ok(jogosService.listarJogos());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Jogos> retornarJogo(@PathVariable String id){
        Optional<Jogos> jogoPorId = jogosService.retornarJogo(id);
        if (jogoPorId.isPresent()){
            return ResponseEntity.ok(jogoPorId.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Jogos> criarJogos(@Valid @RequestBody Jogos jogos){
        Jogos novoJogo = jogosService.criarJogos(jogos);
        return ResponseEntity.status(201).body(novoJogo);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Jogos> atualizarJogo (@PathVariable String id, @Valid @RequestBody Jogos jogos){
        Jogos jogoAtualizado = jogosService.atualizarJogo(id,jogos);
        return ResponseEntity.status(200).body(jogoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo (@PathVariable String id){
        jogosService.deletarJogo(id);
        return  ResponseEntity.noContent().build();
    }

}
