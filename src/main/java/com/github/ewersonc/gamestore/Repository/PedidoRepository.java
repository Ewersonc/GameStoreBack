package com.github.ewersonc.gamestore.Repository;
import com.github.ewersonc.gamestore.Entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
