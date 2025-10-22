package rayka.sos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rayka.sos.dto.OrdemServicoRequestDTO;
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

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criarOrdem(@RequestBody OrdemServicoRequestDTO osrDTO) {
        OrdemServico salvo = ordemServicoService.create(osrDTO, getUsuarioLogado());
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdens() {
        return ResponseEntity.ok(ordemServicoService.readAll(getUsuarioLogado()));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrdemServico> buscarOrdem(@PathVariable UUID uuid) {
        Optional<OrdemServico> ordemServico = ordemServicoService.readOne(uuid, getUsuarioLogado());
        return ordemServico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrdemServico> atualizarOrdem(@PathVariable UUID uuid, @RequestBody OrdemServicoRequestDTO osrUpdate) {
        Optional<OrdemServico> salvo = ordemServicoService.update(uuid, osrUpdate, getUsuarioLogado());
        return salvo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerOrdem(@PathVariable UUID uuid) {
        boolean deletado = ordemServicoService.delete(uuid, getUsuarioLogado());
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
