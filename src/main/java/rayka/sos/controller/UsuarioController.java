package rayka.sos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rayka.sos.dto.LoginDTO;
import rayka.sos.dto.UsuarioDTO;
import rayka.sos.dto.UsuarioPerfilDTO;
import rayka.sos.model.Usuario;
import rayka.sos.service.UsuarioService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    // Endpoint para cadastro
    @PostMapping(consumes = {"multipart/form-data"}) // Multipart para permitir envio da foto de perfil
    public ResponseEntity<Usuario> criarUsuario(
            @ModelAttribute UsuarioDTO usuarioDTO, // Modelo de usuario para os campos de texto
            @RequestParam("photo") MultipartFile photo) {
        try {
            Usuario salvo = usuarioService.create(usuarioDTO, photo);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        } catch (Exception E) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        boolean autenticado = usuarioService.login(loginDTO.getEmail(), loginDTO.getPass());
        if (autenticado) {
            return ResponseEntity.ok("Login realizado!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
        }
    }
    
    // Endpoint para atualizar
    @PutMapping("/{uuid}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID uuid, @RequestBody UsuarioDTO usuarioUpdate) {
        Optional<Usuario> usuario = usuarioService.update(uuid, usuarioUpdate);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Endpoint para atualizar foto de perfil
    @PatchMapping(value = "/{uuid}/photo", consumes = {"multipart/form-data"})
    public ResponseEntity<Usuario> atualizarFoto(
            @PathVariable UUID uuid,
            @RequestParam MultipartFile photo) {
        try {
            Usuario atualizado = usuarioService.updatePhoto(uuid, photo);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } catch (Exception E) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Endpoint para página de perfil
    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioPerfilDTO> perfil(@PathVariable UUID uuid) {
        Optional<Usuario> usuario = usuarioService.findUser(uuid);
        return usuario.map(value -> new ResponseEntity<>(new UsuarioPerfilDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerUsuario(@PathVariable UUID uuid) {
        usuarioService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}