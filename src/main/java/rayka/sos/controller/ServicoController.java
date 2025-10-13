package rayka.sos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.Servico;
import rayka.sos.model.Usuario;
import rayka.sos.service.ServicoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    private final ServicoService servicoService;
    
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }
    
    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico salvo = servicoService.save(servico);
        return ResponseEntity.ok(salvo);
    }
    
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(servicoService.read(usuario.getUuid()));
    }
    
    @PutMapping("/{uuid}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable UUID uuid, @RequestBody Servico servico) {
        Servico salvo = servicoService.save(servico);
        return ResponseEntity.ok(salvo);
    }
    
    @GetMapping("/{uuid}")
    public ResponseEntity<Servico> buscarServico(@PathVariable UUID uuid) {
        Optional<Servico> servico = servicoService.findByUuid(uuid);
        return servico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerServico(@PathVariable long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
