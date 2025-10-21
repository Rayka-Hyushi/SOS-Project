package rayka.sos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    @NotBlank
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
    private String name;
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres.")
    private String email;
    @NotNull
    @Size(min = 8, message = "A senha deve ter no minímo 8 caracteres.")
    private String pass;
}
