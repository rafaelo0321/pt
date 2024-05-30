package com.co.prueba.tecnica.s_tickets.PT.tickets.controladores;

import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.CrearTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.EditarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.dto.MostrarTickets;
import com.co.prueba.tecnica.s_tickets.PT.tickets.servicios.IServiciosTickets;
import com.co.prueba.tecnica.s_tickets.PT.usuario.entidades.Usuario;
import com.co.prueba.tecnica.s_tickets.PT.usuario.repositorio.RepositorioUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tickets")
public class ControladorTickets {
    private static final Logger LOG = LoggerFactory.getLogger(ControladorTickets.class);
    private final IServiciosTickets iServiciosTickets;
    private final RepositorioUsuario repositorioUsuario;


    public ControladorTickets(IServiciosTickets iServiciosTickets, RepositorioUsuario repositorioUsuario) {
        this.iServiciosTickets = iServiciosTickets;
        this.repositorioUsuario = repositorioUsuario;
    }
    @PostMapping("/crear")
   public ResponseEntity<?> crearTicket(
           @RequestBody CrearTickets crearTickets){

        try {
            if(crearTickets == null){
                Map<String,String> error = new HashMap<>();
                error.put("Error: ","El id ingresado no es valido");
                return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
            }

            LOG.info("Se creó el tickets, de forma exitosa");
            return new ResponseEntity<>(iServiciosTickets.crearTickets(crearTickets), HttpStatus.CREATED);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
   @PutMapping("/editar/{id}")
   public ResponseEntity<?> editarTicket(@PathVariable("id") Long id, EditarTickets ticket) {
       try {
           if(id == null){
               Map<String,String> error = new HashMap<>();
               error.put("Error: ","El id ingresado no es valido");
               return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
           }
           LOG.info("Se creó el tickets, de forma exitosa");
           return new ResponseEntity<>(iServiciosTickets.editarTickets(id, ticket), HttpStatus.OK);
       }catch (Exception e) {
           LOG.error(e.getMessage(), e);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
    @GetMapping("/paginado")
    public ResponseEntity<Page<?>> mostrarPaginacion(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        try {
            Page<MostrarTickets> t = iServiciosTickets.mostrarPaginacion(pagina, tamanho);
            LOG.info("Se cargó la paginación de los tickets, de forma exitosa");
            return new ResponseEntity<>(t, HttpStatus.OK);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrar/especifico/{id}")
    public ResponseEntity<?> mostarEspecifico(@PathVariable("id") Long id) {
        try {
            LOG.info("Se muestra el tickets, de forma exitosa");
            return new ResponseEntity<>(iServiciosTickets.recuperarTicketsEspecifico(id), HttpStatus.OK);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mostrar/todos")
    public ResponseEntity<?> mostarTodos() {
        try {
            LOG.info("Se muestran todos los tickets, de forma exitosa");
            return new ResponseEntity<>(iServiciosTickets.recuperarTodosLosTickets(), HttpStatus.OK);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTickect(@PathVariable("id") Long id) {
        try {
            LOG.info("Se muestra el tickets, de forma exitosa");
            return new ResponseEntity<>(iServiciosTickets.eliminarTickets(id), HttpStatus.OK);
        }catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
