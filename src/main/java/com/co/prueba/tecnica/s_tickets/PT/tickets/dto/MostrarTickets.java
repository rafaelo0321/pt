package com.co.prueba.tecnica.s_tickets.PT.tickets.dto;

import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.enums.Estatus;

import java.time.LocalDateTime;

public record MostrarTickets(
        long idTicket,
        String usuario,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        String estado) {
    public MostrarTickets(Tickets tickets){
        this(
                tickets.getId(),
                tickets.getUsuario().getNombre(),
                tickets.getFechaCreacion(),
                tickets.getFechaActualizacion(),
                tickets.getEstatus().name());
    }
}
