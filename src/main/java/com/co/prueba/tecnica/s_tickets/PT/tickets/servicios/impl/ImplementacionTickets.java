package com.co.prueba.tecnica.s_tickets.PT.tickets.servicios.impl;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.CrearTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.EditarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.MostrarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.enums.Estatus;
import com.co.prueba.tecnica.s_tickets.PT.tickets.repositorio.TicketsRepositorio;
import com.co.prueba.tecnica.s_tickets.PT.tickets.servicios.IServiciosTickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.repositorio.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    public MostrarTickets crearTickets(Usuario usuario){
        Tickets nuevoTickets = new Tickets(usuario);
        LOG.info("Se creó el tickets correctamente");
        return new MostrarTickets(nuevoTickets);
    }
    /**
     * eliminar
     * */
    public MostrarTickets eliminarTickets(long id){
        Tickets tickets = ticketsRepositorio.findById(id).orElse(null);
        tickets.setEstatus(Estatus.CERRADO);
        ticketsRepositorio.deleteById(id);
        return new MostrarTickets(tickets);
    }

    /**
     * Editar
     * */
    public MostrarTickets editarTickets(long id, EditarTickets ticket){
        Tickets tickets = ticketsRepositorio.findById(id).orElse(null);

        tickets.setEstatus(Estatus.CERRADO);
        ticketsRepositorio.deleteById(id);
        return new MostrarTickets(tickets);
    }


    /**
     * Paginar
     * */


}
