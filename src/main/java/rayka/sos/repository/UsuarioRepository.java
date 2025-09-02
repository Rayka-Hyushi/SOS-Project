package rayka.sos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import rayka.sos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmailAndPassword(String email, String password);

    public Usuario findByEmail(String email);

    @Query("select photo from Usuario where uid = :u_id")
    public byte[] getPhoto(@Param("u_id") long u_id);
}
