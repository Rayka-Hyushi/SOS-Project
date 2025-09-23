package rayka.sos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @UuidGenerator
    private UUID uuid;

    @Column(nullable = false, length = 50)
    private String service;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(nullable = false, name = "u_id")
    private Usuario usuario;

    @ManyToMany(mappedBy = "servicos")
    private Set<OrdemServico> ordens = new HashSet<>();
}
