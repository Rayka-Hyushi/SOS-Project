package rayka.sos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.Cliente;
import rayka.sos.model.Usuario;
import rayka.sos.service.ClienteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Operation(summary = "Cadastrar Cliente", description = "Cria um novo cliente para o usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado (Token ausente ou inválido)")
    })
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Usuario usuario = getUsuarioLogado();
        Cliente salvo = clienteService.create(cliente, usuario);
        return ResponseEntity.ok(salvo);
    }

    @Operation(summary = "Listar Clientes", description = "Retorna todos os clientes cadastrados pelo usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Cliente.class)))),
            @ApiResponse(responseCode = "403", description = "Não autorizado (Token ausente ou inválido)")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        Usuario usuario = getUsuarioLogado();
        return ResponseEntity.ok(clienteService.readAll(usuario));
    }

    @Operation(summary = "Buscar Cliente", description = "Retorna o perfil de um cliente específico pelo UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado (Token ausente ou inválido)"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou não pertence ao usuário logado")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable UUID uuid) {
        Usuario usuario = getUsuarioLogado();
        Optional<Cliente> cliente = clienteService.readOne(uuid, usuario);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar Cliente", description = "Atualiza os dados de um cliente existente pelo UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "403", description = "Não autorizado (Token ausente ou inválido)"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou não pertence ao usuário logado")
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable UUID uuid, @RequestBody Cliente cliente) {
        Usuario usuario = getUsuarioLogado();
        Optional<Cliente> salvo = clienteService.update(uuid, cliente, usuario);
        return salvo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remover Cliente", description = "Remove um cliente pelo UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Não autorizado (Token ausente ou inválido)"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou não pertence ao usuário logado")

    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerCliente(@PathVariable UUID uuid) {
        Usuario usuario = getUsuarioLogado();
        boolean deletado = clienteService.delete(uuid, usuario);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}