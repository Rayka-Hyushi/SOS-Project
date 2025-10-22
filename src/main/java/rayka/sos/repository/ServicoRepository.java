package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Servico;
import rayka.sos.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    // Buscar todos os serviços do usuário
    List<Servico> findByUsuario(Usuario usuario);

    // Buscar um serviço específico
    Optional<Servico> findByUuidAndUsuario(UUID Uuid, Usuario usuario);
}