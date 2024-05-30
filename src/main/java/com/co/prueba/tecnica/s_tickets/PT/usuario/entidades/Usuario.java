package com.co.prueba.tecnica.s_tickets.PT.usuario.entidades;

import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    private String nombre;
    private LocalDateTime fechaCreacion;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = Tickets.class)
    private List<Tickets> tickets = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(CrearUsuario crearUsuario) {
        this.nombre = crearUsuario.nombre();
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
