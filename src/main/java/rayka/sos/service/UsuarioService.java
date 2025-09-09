package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.Usuario;
import rayka.sos.repository.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByUuid(UUID Uuid) {
        return usuarioRepository.findByUuid(Uuid);
    }

    public byte[] photo(long u_id) {
        return usuarioRepository.getPhoto(u_id);
    }

    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }
}
