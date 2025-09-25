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
    
    public boolean login(String email, String password) {
        return usuarioRepository.findByEmail(email).map(usuario -> passwordEncoder.matches(password, usuario.getPass())).orElse(false);
    }
    
    public Usuario create(Usuario usuario) {
        String passwordCoded = passwordEncoder.encode(usuario.getPass());
        usuario.setPass(passwordCoded);
        return usuarioRepository.save(usuario);
    }
    
    public Optional<Usuario> findByUuid(UUID uuid) {
        return usuarioRepository.findByUuid(uuid);
    }

    public Optional<Usuario> update(UUID uuid, Usuario usuarioUpdate) {
        return usuarioRepository.findByUuid(uuid).map(usuario -> {
            usuario.setName(usuarioUpdate.getName());
            usuario.setEmail(usuarioUpdate.getEmail());
            if (usuarioUpdate.getPhoto() != null) {
                usuario.setPhoto(usuarioUpdate.getPhoto());
            }
            if (usuarioUpdate.getPass() != null && !usuarioUpdate.getPass().isEmpty()) {
                String passwordCoded = passwordEncoder.encode(usuarioUpdate.getPass());
                usuario.setPass(passwordCoded);
            }
            return usuarioRepository.save(usuario);
        });
    }

    public void delete(UUID uuid) {
        usuarioRepository.findByUuid(uuid).ifPresent(usuarioRepository::delete);
    }
}
