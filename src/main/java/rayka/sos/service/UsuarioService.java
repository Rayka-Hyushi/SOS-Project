package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rayka.sos.dto.UsuarioDTO;
import rayka.sos.model.Usuario;
import rayka.sos.repository.UsuarioRepository;

import java.io.IOException;
import java.util.Base64;
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
    
    public Usuario create(UsuarioDTO usuarioDTO, MultipartFile photo) throws IOException {
        Usuario usuario = new Usuario();
        
        // Criptografia da senha e adição dos campos
        usuario.setPass(passwordEncoder.encode(usuarioDTO.getPass()));
        usuario.setName(usuarioDTO.getName());
        usuario.setEmail(usuarioDTO.getEmail());
        
        // Conversão da foto para bytes
        if (photo != null && !photo.isEmpty()) {
            usuario.setPhoto(photo.getBytes());
            System.out.println(usuario.getPhoto());
        }
        
        // Salva o novo usuário
        return usuarioRepository.save(usuario);
    }
    
    public Optional<Usuario> findUser(UUID uuid) {
        return usuarioRepository.findByUuid(uuid);
    }
    
    public Optional<Usuario> update(UUID uuid, UsuarioDTO usuarioUpdate) {
        return usuarioRepository.findByUuid(uuid).map(usuario -> {
            usuario.setName(usuarioUpdate.getName());
            usuario.setEmail(usuarioUpdate.getEmail());
            
            if (usuarioUpdate.getPass() != null && !usuarioUpdate.getPass().isEmpty()) {
                String passwordCoded = passwordEncoder.encode(usuarioUpdate.getPass());
                usuario.setPass(passwordCoded);
            }
            
            return usuarioRepository.save(usuario);
        });
    }
    
    public Usuario updatePhoto(UUID uuid, MultipartFile photo) throws IOException {
        Usuario usuario = usuarioRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        
        if (photo != null && !photo.isEmpty()) {
            usuario.setPhoto(photo.getBytes());
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public void delete(UUID uuid) {
        usuarioRepository.findByUuid(uuid).ifPresent(usuarioRepository::delete);
    }
}
