package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.OrdemServico;
import rayka.sos.repository.OrdemServicoRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico save(OrdemServico os) {
        return ordemServicoRepository.save(os);
    }

    public Optional<OrdemServico> findByUuid(UUID Uuid) {
        return ordemServicoRepository.findByUuid(Uuid);
    }

    public void delete(long id) {
        ordemServicoRepository.deleteById(id);
    }
}
