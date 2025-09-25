package rayka.sos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.Usuario;
import rayka.sos.service.UsuarioService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para cadastro
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.create(usuario);
        return ResponseEntity.ok(salvo);
    }

    
    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.login(usuario.getEmail(), usuario.getPass());
        if (autenticado) {
            return ResponseEntity.ok("Login realizado!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
        }
    }

    // Endpoint para atualizar
    @PutMapping("/{uuid}")
    public ResponseEntity<Usuario> update(@PathVariable UUID uuid, @RequestBody Usuario usuarioUpdate) {
        Optional<Usuario> usuario = usuarioService.update(uuid, usuarioUpdate);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para página de perfil
    @GetMapping("/{uuid}")
    public ResponseEntity<Usuario> userProfile(@PathVariable String uuid) {
        Optional<Usuario> usuario = usuarioService.findByUuid(UUID.fromString(uuid));
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        usuarioService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}