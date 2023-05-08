package health.support.service;

import health.support.dto.ClientDTO;
import health.support.entity.Client;

import java.util.Optional;

public interface IClientService {
    Client createClient(ClientDTO client);

    boolean updateClient(ClientDTO client);

    Optional<Client> findClientById(Long id);

    Optional<Client> findClientByName(String name);
}
