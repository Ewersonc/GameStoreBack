package com.github.ewersonc.gamestore.Service;
import com.github.ewersonc.gamestore.Entity.Jogos;
import com.github.ewersonc.gamestore.Repository.JogosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@Service
public class JogosService {
    @Autowired
    private JogosRepository jogosRepository;

    public List<Jogos> listarJogos(){
        return jogosRepository.findAll();
    }
    public Jogos criarJogos(Jogos jogos){
        jogos.setId(null);
        return jogosRepository.save(jogos);
    }
    public Optional<Jogos> retornarJogo(String id) {
        return  jogosRepository.findById(id);
    }
    public Jogos atualizarJogo(String id, Jogos jogoNovo){
        Jogos jogoAntigo = jogosRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));

        jogoAntigo.setNome(jogoNovo.getNome());
        jogoAntigo.setGenero(jogoNovo.getGenero());
        jogoAntigo.setSinopse(jogoNovo.getSinopse());
        jogoAntigo.setDesenvolvedor(jogoNovo.getDesenvolvedor());
        jogoAntigo.setAnoLancamento(jogoNovo.getAnoLancamento());
        jogoAntigo.setValor(jogoNovo.getValor());
        jogoAntigo.setImagemCapa(jogoNovo.getImagemCapa());

        return jogosRepository.save(jogoAntigo);
    }
    public void deletarJogo(String id){
        jogosRepository.deleteById(id);
    }

}
