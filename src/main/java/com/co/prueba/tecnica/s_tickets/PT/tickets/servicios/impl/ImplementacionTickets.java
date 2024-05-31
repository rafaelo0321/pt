package com.co.prueba.tecnica.s_tickets.PT.tickets.servicios.impl;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.CrearTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.EditarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.MostrarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.enums.Estatus;
import com.co.prueba.tecnica.s_tickets.PT.tickets.repositorio.TicketsRepositorio;
import com.co.prueba.tecnica.s_tickets.PT.tickets.servicios.IServiciosTickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.repositorio.RepositorioUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionTickets implements IServiciosTickets {
    private static final Logger LOG = LoggerFactory.getLogger(ImplementacionTickets.class);
    private final TicketsRepositorio ticketsRepositorio;
    private final RepositorioUsuario repositorioUsuario;

    public ImplementacionTickets(TicketsRepositorio ticketsRepositorio, RepositorioUsuario repositorioUsuario) {
        this.ticketsRepositorio = ticketsRepositorio;
        this.repositorioUsuario = repositorioUsuario;
    }

    public void guardarEntidadEnBaseDeDatos(Tickets tickets) {
        ticketsRepositorio.save(tickets);
    }

    /**
     * Crear
     * */
    @Override
    public MostrarTickets crearTickets(CrearTickets crearTickets){
        Long id = crearTickets.idUsuario();
        Usuario usuario = null;
        if (id!= null) {
            Long numericId = id.longValue();
            usuario = repositorioUsuario.findById(numericId).orElse(null);
        } else {
            throw new IllegalStateException("ID del ticket no puede ser null");
        }

        Tickets nuevoTickets = new Tickets(usuario);
        ticketsRepositorio.save(nuevoTickets);
        LOG.info("Se creó el tickets correctamente");
        return new MostrarTickets(nuevoTickets);
    }
    /**
     * eliminar
     * */
    @Override
    public MostrarTickets eliminarTickets(long id){
        Tickets tickets = ticketsRepositorio.findById(id).orElse(null);
        tickets.setEstatus(Estatus.CERRADO);
        tickets.setFechaActualizacion(LocalDateTime.now());
        ticketsRepositorio.delete(tickets);
        //ticketsRepositorio.save(tickets);
        return new MostrarTickets(tickets);
    }
    @Override
    public MostrarTickets borradoLogicoDeTickets(long id){
        Tickets tickets = ticketsRepositorio.findById(id).orElse(null);
        tickets.setEstatus(Estatus.CERRADO);
        tickets.setFechaActualizacion(LocalDateTime.now());
        ticketsRepositorio.save(tickets);
        return new MostrarTickets(tickets);
    }

    /**
     * Editar
     * */
    @Override
    public MostrarTickets editarTickets(long id, EditarTickets ticket){
        Tickets tickets = ticketsRepositorio.findById(id).orElse(null);

        if (ticket.usuario() != null){
            Usuario usuario = repositorioUsuario.findById(ticket.usuario()).orElse(null);
            tickets.setUsuario(usuario);
        }
        if (ticket.estatus() != null){
            tickets.setEstatus(ticket.estatus());
        }
        tickets.setFechaActualizacion(LocalDateTime.now());
        ticketsRepositorio.save(tickets);
        return new MostrarTickets(tickets);
    }




    /**
     * Paginar
     * */

    @Override
    public Page<MostrarTickets> mostrarPaginacion(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return ticketsRepositorio.findAll(pageable).map(MostrarTickets::new);
    }

    @Override
    public List<MostrarTickets> recuperarTodosLosTickets(){
        return ticketsRepositorio.findAll().stream().map(MostrarTickets::new).collect(Collectors.toList());
    }
    @Override
    public MostrarTickets recuperarTicketsEspecifico(long id){
        return ticketsRepositorio.findById(id).map(MostrarTickets::new).orElse(null);
    }

}
