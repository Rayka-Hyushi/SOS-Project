package rayka.sos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade de representa clientes do usuário")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID do Cliente", example = "1")
    private Long cid;
    
    @UuidGenerator
    private UUID uuid;
    
    @Column(nullable = false, length = 100)
    @Schema(description = "Nome do Usuário", example = "Lys")
    private String name;
    
    @Column(nullable = false, length = 15)
    @Schema(description = "Número de Telefone do Cliente", example = "(55) 95555-5555")
    private String phone;
    
    @Column(nullable = false, length = 100)
    @Schema(description = "E-mail de Contato do Cliente", example = "example@gmail.com")
    private String email;
    
    @Column(nullable = false, length = 100)
    @Schema(description = "Endereço do Cliente", example = "Rua Um, Bairro, Cidade")
    private String address;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "u_id")
    private Usuario usuario;
}
