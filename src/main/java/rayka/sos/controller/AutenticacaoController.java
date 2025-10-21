package rayka.sos.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rayka.sos.model.Usuario;
import rayka.sos.security.TokenServiceJWT;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class AutenticacaoController {
    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody DadosAutenticacao dados) {
        try {
            UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            Authentication at = manager.authenticate(autenticado);

            Usuario usuario = (Usuario) at.getPrincipal();
            String token = tokenService.gerarToken(usuario);

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private record DadosTokenJWT(String token) {}
    private record DadosAutenticacao(String login, String senha) {}
}
