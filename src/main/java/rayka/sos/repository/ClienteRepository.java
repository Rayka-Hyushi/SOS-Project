package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Cliente;
import rayka.sos.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Buscar todos os clientes do usuário
    List<Cliente> findByUsuario(Usuario usuario);

    // Buscar um cliente específico
    Optional<Cliente> findByUuidAndUsuario(UUID Uuid, Usuario usuario);
}