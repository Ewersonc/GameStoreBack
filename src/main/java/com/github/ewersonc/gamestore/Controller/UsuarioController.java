package com.github.ewersonc.gamestore.Controller;
import com.github.ewersonc.gamestore.Entity.Usuario;
import com.github.ewersonc.gamestore.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> retornarUsuario(@PathVariable String id){
        Optional<Usuario> usuarioPorId = usuarioService.retornarUsuario(id);
        if (usuarioPorId.isPresent()){
            return ResponseEntity.ok(usuarioPorId.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String id, @Valid @RequestBody Usuario usuario){
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
        return ResponseEntity.status(200).body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
