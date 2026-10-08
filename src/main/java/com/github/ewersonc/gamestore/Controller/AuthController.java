package com.github.ewersonc.gamestore.Controller;
import com.github.ewersonc.gamestore.DTO.LoginRequest;
import com.github.ewersonc.gamestore.DTO.LoginResponse;
import com.github.ewersonc.gamestore.Entity.Usuario;
import com.github.ewersonc.gamestore.Service.JwtService;
import com.github.ewersonc.gamestore.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.autenticar(request.getEmail(), request.getSenha());
        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.gerarToken(usuario);
        return ResponseEntity.ok(
                new LoginResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), token));
    }
}
