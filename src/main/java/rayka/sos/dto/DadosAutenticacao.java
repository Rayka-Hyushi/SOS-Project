package rayka.sos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para receber os dados de login")
public record DadosAutenticacao(String login, String senha) {
}
