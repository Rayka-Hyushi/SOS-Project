package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Buscar um cliente específico
    Optional<Cliente> findClienteByUuid(UUID Uuid);
    
    // Buscar todos os clientes do usuário
    List<Cliente> findAllByUsuarioUuid(UUID usuarioUuid);
}