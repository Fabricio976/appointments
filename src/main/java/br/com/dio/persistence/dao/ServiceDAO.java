package br.com.dio.persistence.dao;

import br.com.dio.persistence.entity.ServiceEntity;
import java.util.*;

public class ServiceDAO { private final Map<Long, ServiceEntity> db = new HashMap<>(); public ServiceEntity save(ServiceEntity e){ db.put(e.id, e); return e;} public Optional<ServiceEntity> findById(Long id){ return Optional.ofNullable(db.get(id)); } }