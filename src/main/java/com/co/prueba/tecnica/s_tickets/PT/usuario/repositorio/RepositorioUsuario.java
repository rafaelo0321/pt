package com.co.prueba.tecnica.s_tickets.PT.usuario.repositorio;

import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuario extends JpaRepository<Usuario,Long> {
}
