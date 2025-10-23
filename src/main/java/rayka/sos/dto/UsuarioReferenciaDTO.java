package rayka.sos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rayka.sos.model.Usuario;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO de referência para o Usuário (minimalista)")
public class UsuarioReferenciaDTO {
    @Schema(description = "UUID do usuário", example = "5d7cxxb2-56xd-4xx5-a1ac-22xx608x4x57")
    private UUID uuid;

    @Schema(description = "Nome do usuário", example = "Rayka")
    private String name;

    public UsuarioReferenciaDTO(Usuario usuario) {
        this.uuid = usuario.getUuid();
        this.name = usuario.getName();
    }
}
