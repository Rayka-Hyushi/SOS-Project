package rayka.sos.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rayka.sos.model.Usuario;
import rayka.sos.repository.UsuarioRepository;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usario nao encontrado");
        } else {
            UserDetails user = User.withUsername(usuario.getEmail())
                    .password(usuario.getPass())
                    .authorities(usuario.getPermissao())
                    .build();

            return user;
        }
    }

}
