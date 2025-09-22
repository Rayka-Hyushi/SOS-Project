package rayka.sos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.Cliente;
import rayka.sos.model.Usuario;
import rayka.sos.service.ClienteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente salvo = clienteService.save(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> read(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(clienteService.read(usuario.getUuid()));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Cliente> update(@PathVariable UUID uuid, @RequestBody Cliente cliente) {
        Cliente salvo = clienteService.save(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Cliente> cliente(@PathVariable UUID uuid) {
        Optional<Cliente> cliente = clienteService.findByUuid(uuid);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}