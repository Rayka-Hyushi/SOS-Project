package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Usuario;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca o usuário pelo e-mail
    Optional<Usuario> findByEmail(String email);

    // Busca um usuário por uuid
    Optional<Usuario> findByUuid(UUID uuid);
}