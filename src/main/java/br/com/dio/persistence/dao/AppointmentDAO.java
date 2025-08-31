package br.com.dio.persistence.dao;

import br.com.dio.persistence.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.util.*;

public class AppointmentDAO {
    private final Map<Long, AppointmentEntity> db = new HashMap<>();

    public AppointmentEntity save(AppointmentEntity e) {
        db.put(e.id, e);
        return e;
    }

    public Optional<AppointmentEntity> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<AppointmentEntity> findByDateTime(LocalDateTime dt) {
        List<AppointmentEntity> list = new ArrayList<>();
        for (AppointmentEntity e : db.values()) {
            if (e.dateTime.equals(dt)) {
                list.add(e);
            }
        }
        return list;
    }

    public List<AppointmentEntity> findAll() {
        return new ArrayList<>(db.values());
    }
}
