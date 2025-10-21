package rayka.sos.controller;

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

    // Endpoint para cadastrar um cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Usuario usuario = getUsuarioLogado();
        Cliente salvo = clienteService.create(cliente, usuario);
        return ResponseEntity.ok(salvo);
    }

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        Usuario usuario = getUsuarioLogado();
        return ResponseEntity.ok(clienteService.readAll(usuario));
    }

    // Endpoint para buscar o perfil de um cliente
    @GetMapping("/{uuid}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable UUID uuid) {
        Usuario usuario = getUsuarioLogado();
        Optional<Cliente> cliente = clienteService.readOne(uuid, usuario);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar dados de um clinte
    @PutMapping("/{uuid}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable UUID uuid, @RequestBody Cliente cliente) {
        Usuario usuario = getUsuarioLogado();
        Optional<Cliente> salvo = clienteService.update(uuid, cliente, usuario);
        return salvo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para remover um cliente
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerCliente(@PathVariable UUID uuid) {
        Usuario usuario = getUsuarioLogado();
        boolean deletado = clienteService.delete(uuid, usuario);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}