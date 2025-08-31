package br.com.dio.ui;

import br.com.dio.persistence.entity.AppointmentEntity;
import br.com.dio.persistence.entity.ClientEntity;
import br.com.dio.persistence.entity.ServiceEntity;
import br.com.dio.service.AppointmentService;
import br.com.dio.service.ClientService;
import br.com.dio.service.ServiceService;
import br.com.dio.persistence.dao.AppointmentDAO;
import br.com.dio.persistence.dao.ClientDAO;
import br.com.dio.persistence.dao.ServiceDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SchedulerMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final ClientService clientService = new ClientService(new ClientDAO());
    private final ServiceService serviceService = new ServiceService(new ServiceDAO());
    private final AppointmentService appointmentService = new AppointmentService(new AppointmentDAO());

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Sistema de Agendamento ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar serviço");
            System.out.println("3. Agendar consulta");
            System.out.println("4. Confirmar consulta");
            System.out.println("5. Cancelar consulta");
            System.out.println("6. Listar consultas");
            System.out.println("0. Sair");


            System.out.print("Digite a opção: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> registerClient();
                case 2 -> registerService();
                case 3 -> scheduleAppointment();
                case 4 -> confirmAppointment();
                case 5 -> cancelAppointment();
                case 6 -> listAppointments();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void registerClient() {
        scanner.nextLine();
        ClientEntity client = new ClientEntity();
        System.out.print("Nome: ");
        client.name = scanner.nextLine();
        System.out.print("CPF: ");
        client.cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        client.phone = scanner.nextLine();
        System.out.print("Email: ");
        client.email = scanner.nextLine();

        client.id = System.currentTimeMillis();
        clientService.register(client);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void registerService() {
        scanner.nextLine();
        ServiceEntity service = new ServiceEntity();
        System.out.print("Descrição: ");
        service.description = scanner.nextLine();
        System.out.print("Duração (min): ");
        service.duration = scanner.nextInt();
        System.out.print("Preço: ");
        service.price = scanner.nextDouble();

        service.id = System.currentTimeMillis();
        serviceService.register(service);
        System.out.println("Serviço cadastrado com sucesso!");
    }

    private void scheduleAppointment() {
        System.out.print("ID Cliente: ");
        long clientId = scanner.nextLong();
        System.out.print("ID Serviço: ");
        long serviceId = scanner.nextLong();
        System.out.print("Data/Hora (yyyy-MM-ddTHH:mm): ");
        LocalDateTime dateTime = LocalDateTime.parse(scanner.next());

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.id = System.currentTimeMillis();
        appointment.clientId = clientId;
        appointment.serviceId = serviceId;
        appointment.dateTime = dateTime;

        appointmentService.schedule(appointment);
        System.out.println("Consulta agendada com sucesso!");
    }

    private void confirmAppointment() {
        System.out.print("ID da consulta: ");
        Long id = scanner.nextLong();
        appointmentService.confirm(id);
        System.out.println("Consulta confirmada!");
    }

    private void cancelAppointment() {
        System.out.print("ID da consulta: ");
        Long id = scanner.nextLong();
        appointmentService.cancel(id);
        System.out.println("Consulta cancelada!");
    }

    private void listAppointments() {
        List<AppointmentEntity> list = appointmentService.findAll();
        if (list.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
        } else {
            System.out.println("\n--- Consultas ---");
            for (AppointmentEntity ap : list) {
                System.out.printf("ID=%d | Cliente=%d | Serviço=%d | Data=%s | Status=%s%n",
                        ap.id, ap.clientId, ap.serviceId, ap.dateTime, ap.status);
            }
        }
    }
}
