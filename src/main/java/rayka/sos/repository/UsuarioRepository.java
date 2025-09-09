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
    public Usuario findByEmailAndPassword(String email, String password);

    Optional<Usuario> findByUuid(UUID uuid);

    @Query("select photo from Usuario where uuid = :uuid")
    public byte[] getPhoto(@Param("uuid") long uuid);
}