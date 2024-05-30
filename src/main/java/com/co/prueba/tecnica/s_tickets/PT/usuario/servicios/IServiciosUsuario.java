package com.co.prueba.tecnica.s_tickets.PT.usuario.servicios;

import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.MostrarUsuario;

public interface IServiciosUsuario {
   MostrarUsuario crearUsuario(CrearUsuario crearUsuario);
}
