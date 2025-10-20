package rayka.sos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rayka.sos.dto.DadosAutenticacao;
import rayka.sos.dto.UsuarioDTO;
import rayka.sos.dto.UsuarioPerfilDTO;
import rayka.sos.model.Usuario;
import rayka.sos.service.UsuarioService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Path relacionado a operações de usuários")
public class UsuarioController {
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    // Endpoint para cadastro
    @Operation(summary = "Criar Usuário", description = "Cria um novo usuário no banco")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos fornecidos"
            )
    })
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
//    @Operation(summary = "Login de Usuário", description = "Realiza a validação de login do sistema")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Usuário logado com sucesso"
//            ),
//            @ApiResponse(
//                    responseCode = "401", description = "E-mail ou senha inválida"
//            )
//    })
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody DadosAutenticacao dados) {
//        boolean autenticado = usuarioService.login(loginDTO.getEmail(), loginDTO.getPass());
//        if (autenticado) {
//            return ResponseEntity.ok("Login realizado!");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
//        }
//    }

    // Endpoint para atualizar
    @Operation(summary = "Atualizar Perfil de Usuário", description = "Atualiza os dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos fornecidos"
            )
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID uuid, @RequestBody UsuarioDTO usuarioUpdate) {
        Optional<Usuario> usuario = usuarioService.update(uuid, usuarioUpdate);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Endpoint para atualizar foto de perfil
    @Operation(summary = "Atualizar Foto do Usuário", description = "Atualiza a foto de perfil do usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Foto alterada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Usuário não encontrado ou erro no servidor"
            )
    })
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
    @Operation(summary = "Perfil de Usuário", description = "Recupera as informações de um usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioPerfilDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioPerfilDTO> perfil(@PathVariable UUID uuid) {
        Optional<Usuario> usuario = usuarioService.findUser(uuid);
        return usuario.map(value -> new ResponseEntity<>(new UsuarioPerfilDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Remover Usuário", description = "Remove um usuário através do UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerUsuario(@PathVariable UUID uuid) {
        usuarioService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}