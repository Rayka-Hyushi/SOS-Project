package rayka.sos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.OrdemServico;
import rayka.sos.model.Usuario;
import rayka.sos.service.OrdemServicoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordens")
public class OrdemServicoController {
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdem(@RequestBody OrdemServico ordemServico) {
        OrdemServico salvo = ordemServicoService.save(ordemServico);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdens(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(ordemServicoService.read(usuario.getUuid()));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrdemServico> atualizarOrdem(@PathVariable UUID uuid, @RequestBody OrdemServico ordemServico) {
        OrdemServico salvo = ordemServicoService.save(ordemServico);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrdemServico> buscarOrdem(@PathVariable UUID uuid) {
        Optional<OrdemServico> ordemServico = ordemServicoService.findByUuid(uuid);
        return ordemServico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerOrdem(@PathVariable Long id) {
        ordemServicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
