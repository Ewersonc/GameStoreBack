package com.github.ewersonc.gamestore.Repository;
import com.github.ewersonc.gamestore.Entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
