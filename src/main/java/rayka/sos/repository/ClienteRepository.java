package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUuid(UUID uuid);

    // Buscar um cliente do usuário
    Optional<Cliente> findClienteByUsuarioUuid(UUID usuarioUuid);

    // Buscar todos os clientes do usuário
    List<Cliente> findAllByUsuarioUuid(UUID usuarioUuid);
}