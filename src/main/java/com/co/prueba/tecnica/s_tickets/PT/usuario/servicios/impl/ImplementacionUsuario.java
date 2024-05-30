package com.co.prueba.tecnica.s_tickets.PT.usuario.servicios.impl;

import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.MostrarUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.repositorio.RepositorioUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.servicios.IServiciosUsuario;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionUsuario implements IServiciosUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public ImplementacionUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void setRepositorioUsuario(Usuario usuario){
        repositorioUsuario.save(usuario);
    }

    @Override
    public MostrarUsuario crearUsuario(CrearUsuario crearUsuario) {
            Usuario usuario = new Usuario(crearUsuario);
            setRepositorioUsuario(usuario);
        return new MostrarUsuario(usuario);
    }
}
