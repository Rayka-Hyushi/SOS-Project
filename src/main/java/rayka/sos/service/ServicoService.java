package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.Servico;
import rayka.sos.repository.ServicoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;
    
    public Servico save(Servico servico) {
        if (Objects.isNull(servico.getUsuario().getUid())) {
            System.out.println("Usuario nao encontrado");
            return null;
        }
        
        return servicoRepository.save(servico);
    }
    
    public List<Servico> read(UUID usuarioUuid) {
        return servicoRepository.findAllByUsuarioUuid(usuarioUuid);
    }
    
    public Optional<Servico> findByUuid(UUID Uuid) {
        return servicoRepository.findServicoByUuid(Uuid);
    }
    
    public void delete(long id) {
        servicoRepository.deleteById(id);
    }
}
