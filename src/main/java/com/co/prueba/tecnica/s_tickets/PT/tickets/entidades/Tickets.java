package com.co.prueba.tecnica.s_tickets.PT.tickets.entidades;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.CrearTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.enums.Estatus;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TICKETS")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;
    private Estatus estatus;

    public Tickets(Usuario usuario) {
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
        this.estatus = Estatus.ABIERTO;
    }

    public Tickets() {
        this.fechaCreacion = LocalDateTime.now();
        this.estatus = Estatus.ABIERTO;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

}
