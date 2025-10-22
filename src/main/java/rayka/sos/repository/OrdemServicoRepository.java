package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.OrdemServico;
import rayka.sos.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Buscar todas as ordens de serviço do usuário
    List<OrdemServico> findByUsuario(Usuario usuario);

    // Buscar uma ordem de serviço específica
    Optional<OrdemServico> findByUuidAndUsuario(UUID Uuid, Usuario usuario);
}