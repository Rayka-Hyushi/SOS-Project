package rayka.sos.controller;

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

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestAttribute String email, @RequestAttribute String password) {
        Usuario usuario = usuarioService.login(email, password);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Usuario> update(@PathVariable UUID uuid, @RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Usuario> userProfile(@PathVariable UUID uuid) {
        Optional<Usuario> usuario = usuarioService.findByUuid(uuid);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}