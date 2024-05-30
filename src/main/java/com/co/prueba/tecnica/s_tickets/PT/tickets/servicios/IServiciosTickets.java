package com.co.prueba.tecnica.s_tickets.PT.tickets.servicios;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.CrearTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.EditarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.MostrarTickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IServiciosTickets {
    MostrarTickets crearTickets(CrearTickets crearTickets);
    MostrarTickets eliminarTickets(long id);
    MostrarTickets editarTickets(long id, EditarTickets ticket);
    Page<MostrarTickets> mostrarPaginacion(int pagina, int tamanho);
    MostrarTickets recuperarTicketsEspecifico(long id);
    List<MostrarTickets> recuperarTodosLosTickets();
}
