package rayka.sos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rayka.sos.model.Servico;
import rayka.sos.model.Usuario;
import rayka.sos.service.ServicoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico salvo = servicoService.create(servico, getUsuarioLogado());
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        return ResponseEntity.ok(servicoService.readAll(getUsuarioLogado()));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Servico> buscarServico(@PathVariable UUID uuid) {
        Optional<Servico> servico = servicoService.readOne(uuid, getUsuarioLogado());
        return servico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable UUID uuid, @RequestBody Servico servico) {
        Optional<Servico> salvo = servicoService.update(uuid, servico, getUsuarioLogado());
        return salvo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removerServico(@PathVariable UUID uuid) {
        boolean deletado = servicoService.delete(uuid, getUsuarioLogado());
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
