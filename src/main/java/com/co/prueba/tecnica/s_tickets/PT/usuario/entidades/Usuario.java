package com.co.prueba.tecnica.s_tickets.PT.usuario.entidades;

import com.co.prueba.tecnica.s_tickets.PT.tickets.entidades.Tickets;
import jakarta.persistence.*;

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
}
