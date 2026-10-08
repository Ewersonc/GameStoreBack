package com.github.ewersonc.gamestore.Repository;
import com.github.ewersonc.gamestore.Entity.Jogos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JogosRepository extends MongoRepository<Jogos, String> {
}
