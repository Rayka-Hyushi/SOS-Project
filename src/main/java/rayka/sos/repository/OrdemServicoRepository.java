package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.OrdemServico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Busca uma ordem de serviço do usuário
    Optional<OrdemServico> findOrdemServicoByUuid(UUID Uuid);
    
    // Buscar todas as ordens de serviço do usuário
    List<OrdemServico> findAllByUsuarioUuid(UUID usuarioUuid);
}