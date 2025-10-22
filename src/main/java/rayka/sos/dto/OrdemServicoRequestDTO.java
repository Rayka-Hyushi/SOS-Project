package rayka.sos.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import rayka.sos.model.StatusOrdemServico;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrdemServicoRequestDTO {
    @NotNull
    UUID clienteUuid;

    @NotBlank
    @Size(min = 3, max = 50, message = "O nome do dispositivo deve ter entre 3 e 50 caracteres.")
    String device;

    @NotBlank
    @Size(max = 500, message = "A descrição fornecida é longa demais. Reduza a descrição para no máximo 500 caracteres.")
    String description;

    @NotNull
    List<UUID> servicosUuids;

    @NotNull
    StatusOrdemServico status;

    @NotNull
    @Digits(integer = 4, fraction = 2, message = "O valor de extras deve ter no maximo 6 dígitos inteiros e 2 casas decimais.")
    BigDecimal extras;

    @NotNull
    @Digits(integer = 4, fraction = 2, message = "O valor de desconto deve ter no maximo 6 dígitos inteiros e 2 casas decimais.")
    BigDecimal discount;
}
