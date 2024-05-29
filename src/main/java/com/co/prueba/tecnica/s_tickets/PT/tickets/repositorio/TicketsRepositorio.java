package com.co.prueba.tecnica.s_tickets.PT.tickets.repositorio;

import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepositorio extends JpaRepository<Tickets,Long> {
}
