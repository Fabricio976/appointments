package br.com.dio.service;

import br.com.dio.persistence.dao.ServiceDAO;
import br.com.dio.persistence.entity.ServiceEntity;

import java.util.Optional;

public class ServiceService {
    private final ServiceDAO dao;

    public ServiceService(ServiceDAO dao) {
        this.dao = dao;
    }

    public ServiceEntity register(ServiceEntity s) {
        return dao.save(s);
    }

    public Optional<ServiceEntity> find(Long id) {
        return dao.findById(id);
    }
}