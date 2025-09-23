package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rayka.sos.model.Usuario;
import rayka.sos.repository.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Usuario create(Usuario usuario) {
        String passwordCoded = passwordEncoder.encode(usuario.getPass());
        usuario.setPass(passwordCoded);
        return usuarioRepository.save(usuario);
    }
    
    public boolean login(String email, String password) {
        return usuarioRepository.findByEmail(email).map(usuario -> passwordEncoder.matches(password, usuario.getPass())).orElse(false);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByUuid(UUID Uuid) {
        return usuarioRepository.findByUuid(Uuid);
    }

    public byte[] photo(UUID uuid) {
        return usuarioRepository.getPhoto(uuid);
    }

    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }
}
