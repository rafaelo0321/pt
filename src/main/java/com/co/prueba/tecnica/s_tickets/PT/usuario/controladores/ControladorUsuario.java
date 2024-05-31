package com.co.prueba.tecnica.s_tickets.PT.usuario.controladores;

import com.co.prueba.tecnica.s_tickets.PT.usuario.dto.CrearUsuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.servicios.IServiciosUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class ControladorUsuario {
    private static final Logger LOG = LoggerFactory.getLogger(ControladorUsuario.class);
    private final IServiciosUsuario serviciosUsuario;

    public ControladorUsuario(IServiciosUsuario serviciosUsuario) {
        this.serviciosUsuario = serviciosUsuario;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearusuarios(@RequestBody CrearUsuario usuario) {

            try {
                Usuario u = serviciosUsuario.traerusuario(usuario.nombre());
                if (usuario.nombre().isEmpty()) {
                    LOG.warn("El nombre se encuentra vacio");
                    return new ResponseEntity<>("Favor ingresar un nombre, este dato no puede estar vacio", HttpStatus.BAD_REQUEST);
                }
                if (u != null) {
                    if (usuario.nombre().equals(u.getNombre())){
                        LOG.warn("El nombre se encuentra repetido");
                        return new ResponseEntity<>("Favor ingresar un nombre, este dato no puede estar repetido", HttpStatus.BAD_REQUEST);

                    }
                }
                LOG.info("Se creó el usuario, de forma exitosa");
                return new ResponseEntity<>(serviciosUsuario.crearUsuario(usuario), HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                LOG.error(e.getMessage(), e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }
    @GetMapping("/mostrar/todos")
    public ResponseEntity<?> mostrarTodosLosUsuarios() {
        try {
            LOG.info("Se muestra una lista de usuarios, de forma exitosa");
            return new ResponseEntity<>(serviciosUsuario.mostrarUsuarios(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
