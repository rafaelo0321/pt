package com.co.prueba.tecnica.s_tickets.PT.usuario.dto;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.MostrarTickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record MostrarUsuario(long id, String nombre, LocalDateTime fechaCreacion, List<MostrarTickets> tickets) {
    public MostrarUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(),usuario.getFechaCreacion(),usuario.getTickets().stream().map(MostrarTickets::new).collect(Collectors.toList()));
    }
}
