package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Servico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    // Buscar um serviço específico
    Optional<Servico> findServicoByUuid(UUID uuid);

    // Buscar todos os serviços do usuário
    List<Servico> findAllByUsuarioUuid(UUID usuarioUuid);
}