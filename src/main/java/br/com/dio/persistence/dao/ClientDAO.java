package br.com.dio.persistence.dao;

import br.com.dio.persistence.entity.ClientEntity;
import java.util.*;

public class ClientDAO { private final Map<Long, ClientEntity> db = new HashMap<>(); public ClientEntity save(ClientEntity e){ db.put(e.id, e); return e;} public Optional<ClientEntity> findById(Long id){ return Optional.ofNullable(db.get(id)); } }