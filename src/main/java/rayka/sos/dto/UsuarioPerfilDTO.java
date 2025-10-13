package rayka.sos.dto;

import lombok.Getter;
import lombok.Setter;
import rayka.sos.model.Usuario;

import java.util.Base64;
import java.util.UUID;

@Getter
@Setter
public class UsuarioPerfilDTO {
    private UUID uuid;
    private String name;
    private String email;
    private String photo;
    
    public UsuarioPerfilDTO(Usuario usuario) {
        this.uuid = usuario.getUuid();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        
        if (usuario.getPhoto() != null) {
            this.photo = Base64.getEncoder().encodeToString(usuario.getPhoto());
        } else {
            this.photo = null;
        }
    }
}
