package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    public List<Client> listAll() {
        return clientRepository.listAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void create(Client client) {
        clientRepository.persist(client);
    }

    @Transactional
    public void update(Long id, Client client) {
        Client entity = clientRepository.findById(id);
        if (entity != null) {
            entity.setName(client.getName());
            entity.setEmail(client.getEmail());
            entity.setPhone(client.getPhone());
        }
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
