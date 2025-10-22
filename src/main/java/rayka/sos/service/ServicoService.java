package rayka.sos.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.Servico;
import rayka.sos.model.Usuario;
import rayka.sos.repository.ServicoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    // Operacao de create
    @Transactional
    public Servico create(Servico servico, Usuario usuario) {
        servico.setUsuario(usuario);
        return servicoRepository.save(servico);
    }

    // Operacao de read de todos os servicos do usuario
    public List<Servico> readAll(Usuario usuario) {
        return servicoRepository.findByUsuario(usuario);
    }

    // Operacao de read unico
    public Optional<Servico> readOne(UUID Uuid, Usuario usuario) {
        return servicoRepository.findByUuidAndUsuario(Uuid, usuario);
    }

    // Operacao de update
    @Transactional
    public Optional<Servico> update(UUID servicoUuid, Servico servicoUpdate, Usuario usuario) {
        return servicoRepository.findByUuidAndUsuario(servicoUuid, usuario)
                .map(servico -> {
                    servico.setService(servicoUpdate.getService());
                    servico.setDescription(servicoUpdate.getDescription());
                    servico.setValue(servicoUpdate.getValue());
                    return servicoRepository.save(servico);
                });
    }

    // Operacao de delete
    @Transactional
    public boolean delete(UUID servicoUuid, Usuario usuario) {
        Optional<Servico> servico = servicoRepository.findByUuidAndUsuario(servicoUuid, usuario);
        if (servico.isPresent()) {
            servicoRepository.delete(servico.get());
            return true;
        }
        return false;
    }
}
