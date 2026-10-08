package com.github.ewersonc.gamestore.Service;
import com.github.ewersonc.gamestore.Entity.Pedido;
import com.github.ewersonc.gamestore.Enums.StatusPedido;
import com.github.ewersonc.gamestore.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }
    public Pedido criarPedido(Pedido pedido){
        pedido.setId(null);
        if (pedido.getStatus() == null) {
            pedido.setStatus(StatusPedido.PENDENTE);
        }
        return pedidoRepository.save(pedido);
    }
    public Optional<Pedido> retornarPedido(String id) {
        return pedidoRepository.findById(id);
    }
    public Pedido atualizarPedido(String id, Pedido pedidoNovo){
        Pedido pedidoAntigo = pedidoRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        pedidoAntigo.setCliente(pedidoNovo.getCliente());
        pedidoAntigo.setValorTotal(pedidoNovo.getValorTotal());
        pedidoAntigo.setStatus(pedidoNovo.getStatus());

        return pedidoRepository.save(pedidoAntigo);
    }
    public void deletarPedido(String id){
        pedidoRepository.deleteById(id);
    }
}
