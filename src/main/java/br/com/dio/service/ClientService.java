package br.com.dio.service;

import br.com.dio.persistence.dao.ClientDAO;
import br.com.dio.persistence.entity.ClientEntity;

import java.util.Optional;

public class ClientService {
    private final ClientDAO dao;

    public ClientService(ClientDAO dao) {
        this.dao = dao;
    }

    public ClientEntity register(ClientEntity c) {
        return dao.save(c);
    }

    public Optional<ClientEntity> find(Long id) {
        return dao.findById(id);
    }
}