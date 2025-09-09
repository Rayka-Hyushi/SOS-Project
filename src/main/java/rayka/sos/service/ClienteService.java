package rayka.sos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rayka.sos.model.Cliente;
import rayka.sos.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> read(UUID usuarioUuid) {
        return clienteRepository.findAllByUsuarioUuid(usuarioUuid);
    }

    public Optional<Cliente> findByUuid(UUID Uuid) {
        return clienteRepository.findByUuid(Uuid);
    }

    public void delete(long id) {
        clienteRepository.deleteById(id);
    }
}