package rayka.sos.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rayka.sos.model.Usuario;
import rayka.sos.repository.UsuarioRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usario nao encontrado");
        } else {
            UserDetails user = User.withUsername(usuario.get().getEmail())
                    .password(usuario.get().getPass())
                    .authorities(usuario.get().getPermissao())
                    .build();

            return user;
        }
    }

}
