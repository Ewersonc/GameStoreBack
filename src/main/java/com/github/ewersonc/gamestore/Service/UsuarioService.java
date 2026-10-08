package com.github.ewersonc.gamestore.Service;
import com.github.ewersonc.gamestore.Entity.Usuario;
import com.github.ewersonc.gamestore.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final int TAMANHO_MAXIMO_SENHA = 72;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario criarUsuario(Usuario usuario){
        String email = normalizarEmail(usuario.getEmail());
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        usuario.setId(null);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> retornarUsuario(String id) {
        return usuarioRepository.findById(id);
    }
    public Usuario atualizarUsuario(String id, Usuario usuarioNovo){
        Usuario usuarioAntigo = usuarioRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        String email = normalizarEmail(usuarioNovo.getEmail());
        usuarioRepository.findByEmail(email)
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
                });

        usuarioAntigo.setNome(usuarioNovo.getNome());
        usuarioAntigo.setEmail(email);
        usuarioAntigo.setSenha(passwordEncoder.encode(usuarioNovo.getSenha()));

        return usuarioRepository.save(usuarioAntigo);
    }
    public void deletarUsuario(String id){
        usuarioRepository.deleteById(id);
    }

    public Usuario autenticar(String email, String senha) {
        if (email == null || senha == null || email.isBlank() || senha.isBlank()) {
            return null;
        }
        if (senha.length() > TAMANHO_MAXIMO_SENHA) {
            return null;
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(normalizarEmail(email));
        if (usuarioOpt.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            return null;
        }

        return usuario;
    }

    private String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }
}
