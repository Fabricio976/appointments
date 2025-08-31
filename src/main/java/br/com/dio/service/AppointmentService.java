package br.com.dio.service;

import br.com.dio.persistence.dao.AppointmentDAO;
import br.com.dio.persistence.entity.AppointmentEntity;
import br.com.dio.persistence.entity.AppointmentStatusEnum;
import br.com.dio.exception.AppointmentConflictException;

import java.util.List;

public class AppointmentService {
    private final AppointmentDAO dao;

    public AppointmentService(AppointmentDAO dao) {
        this.dao = dao;
    }

    public AppointmentEntity schedule(AppointmentEntity appointment) {
        List<AppointmentEntity> existing = dao.findByDateTime(appointment.dateTime);
        if (!existing.isEmpty()) {
            throw new AppointmentConflictException("Já existe consulta agendada neste horário!");
        }
        appointment.status = AppointmentStatusEnum.PENDING;
        return dao.save(appointment);
    }

    public void confirm(Long id) {
        AppointmentEntity ap = dao.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        ap.status = AppointmentStatusEnum.CONFIRMED;
        dao.save(ap);
    }

    public void cancel(Long id) {
        AppointmentEntity ap = dao.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        ap.status = AppointmentStatusEnum.CANCELED;
        dao.save(ap);
    }

    public List<AppointmentEntity> findAll() {
        return dao.findAll();
    }
}
