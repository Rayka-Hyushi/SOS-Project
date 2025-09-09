package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.Servico;
import rayka.sos.repository.ServicoRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Optional<Servico> findByUuid(UUID Uuid) {
        return servicoRepository.findByUuid(Uuid);
    }

    public void delete(long id) {
        servicoRepository.deleteById(id);
    }
}
