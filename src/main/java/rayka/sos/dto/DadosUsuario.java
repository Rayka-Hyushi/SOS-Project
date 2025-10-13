package rayka.sos.dto;

import rayka.sos.model.Usuario;

public record DadosUsuario(Long id, String login, String permissao) {
    public DadosUsuario(Usuario usuario) {
        this(usuario.getUid(), usuario.getEmail(), usuario.getPermissao());
    }
}
