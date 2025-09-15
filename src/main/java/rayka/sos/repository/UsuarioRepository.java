package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rayka.sos.model.Usuario;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Verifica se o usuario inserido no login existe
    public Usuario findByEmailAndPass(String email, String pass);

    // Busca um usuário específico
    Optional<Usuario> findByUuid(UUID uuid);

    // Recupera a foto de perfil do usuário
    @Query("select photo from Usuario where uuid = :uuid")
    public byte[] getPhoto(@Param("uuid") long uuid);
}