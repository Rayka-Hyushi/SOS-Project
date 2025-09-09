package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Cliente;
import rayka.sos.model.OrdemServico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    Optional<OrdemServico> findByUuid(UUID uuid);

    // Buscar um cliente do usuário
    List<OrdemServico> findByUsuarioUuid(UUID usuarioUuid);

    // Buscar todos os clientes do usuário
    List<Cliente> findAllByUsuarioUuid(UUID usuarioUuid);
}