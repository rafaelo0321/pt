package com.co.prueba.tecnica.s_tickets.PT.usuario.servicios;

import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.MostrarUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;

import java.util.List;

public interface IServiciosUsuario {
   MostrarUsuario crearUsuario(CrearUsuario crearUsuario);
   List<MostrarUsuario> mostrarUsuarios();
   Usuario traerusuario(String nombre);
}
