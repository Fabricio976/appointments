package br.com.dio.persistence.entity;

import java.time.LocalDateTime;

public class AppointmentEntity {
    public Long id;
    public Long clientId;
    public Long serviceId;
    public LocalDateTime dateTime;
    public AppointmentStatusEnum status;
}