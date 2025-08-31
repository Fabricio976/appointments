package br.com.dio.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    public Long id;
    public Long clientId;
    public Long serviceId;
    public LocalDateTime dateTime;
    public String status;
}