package com.github.ewersonc.gamestore.Controller;
import com.github.ewersonc.gamestore.Entity.Pedido;
import com.github.ewersonc.gamestore.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> retornarPedido(@PathVariable String id){
        Optional<Pedido> pedidoPorId = pedidoService.retornarPedido(id);
        if (pedidoPorId.isPresent()){
            return ResponseEntity.ok(pedidoPorId.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido){
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(201).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable String id, @Valid @RequestBody Pedido pedido){
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, pedido);
        return ResponseEntity.status(200).body(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable String id){
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
