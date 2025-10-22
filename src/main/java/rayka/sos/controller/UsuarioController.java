package rayka.sos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rayka.sos.dto.UsuarioDTO;
import rayka.sos.dto.UsuarioPerfilDTO;
import rayka.sos.model.Usuario;
import rayka.sos.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Path relacionado a operações de usuários")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Operation(summary = "Cadastrar Usuário", description = "Cria um novo usuário no banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping(consumes = {"multipart/form-data"}) // Multipart para permitir envio da foto de perfil
    public ResponseEntity<Usuario> criarUsuario(
            @ModelAttribute @Valid UsuarioDTO usuarioDTO, // Modelo de usuario para os campos de texto
            @RequestParam("photo") MultipartFile photo) {
        Usuario salvo = usuarioService.create(usuarioDTO, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Perfil de Usuário", description = "Retorna o perfil do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioPerfilDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> perfil() {
        Usuario usuario = getUsuarioLogado();
        return new ResponseEntity<>(new UsuarioPerfilDTO(usuario), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar Perfil de Usuário", description = "Atualiza os dados do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioPerfilDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping
    public ResponseEntity<UsuarioPerfilDTO> atualizarUsuario(@RequestBody @Valid UsuarioDTO usuarioUpdate) {
        Usuario usuario = usuarioService.update(getUsuarioLogado().getUuid(), usuarioUpdate).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(new UsuarioPerfilDTO(usuario));
    }

    @Operation(summary = "Atualizar Foto do Usuário", description = "Atualiza a foto de perfil do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto alterada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioPerfilDTO.class))),
            @ApiResponse(responseCode = "500", description = "Usuário não encontrado ou erro no servidor")
    })
    @PatchMapping(value = "/foto", consumes = {"multipart/form-data"})
    public ResponseEntity<UsuarioPerfilDTO> atualizarFoto(@RequestParam(value = "photo", required = false) MultipartFile photo) {
        Usuario atualizado = usuarioService.updatePhoto(getUsuarioLogado().getUuid(), photo);
        return new ResponseEntity<>(new UsuarioPerfilDTO(atualizado), HttpStatus.OK);
    }

    @Operation(summary = "Remover Usuário", description = "Remove a conta do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping
    public ResponseEntity<Void> removerUsuario() {
        usuarioService.delete(getUsuarioLogado().getUuid());
        return ResponseEntity.noContent().build();
    }
}