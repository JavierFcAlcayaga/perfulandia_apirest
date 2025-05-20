package com.servicio_persona.controllers;

import com.servicio_persona.models.Persona;
import com.servicio_persona.services.PersonaService;

import com.servicio_persona.dto.PersonaConFonoDTO;
import com.servicio_persona.dto.FonoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController // Indica que esta clase responde a peticiones REST
@RequestMapping("/personas") // Ruta base para este controlador
public class PersonaController {

    @Autowired // Inyección del servicio de persona
    private PersonaService personaService;

    // Obtener todas las personas (GET)
    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

    // Obtener persona por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Persona persona = personaService.obtenerPorId(id);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    // Crear nueva persona (POST)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Persona persona) {
        Persona nueva = personaService.add(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar persona existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Persona persona) {
        Persona actualizada = personaService.update(id, persona);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    // Eliminar persona (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Persona eliminada = personaService.eliminar(id);
        if (eliminada != null) {
            return ResponseEntity.ok(eliminada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    // POST /registro-completo → guarda persona + fono en otro microservicio
    @PostMapping("/registro-completo")
    public ResponseEntity<?> registrarPersonaConFono(@RequestBody PersonaConFonoDTO dto) {
        // 1. Guardar persona
        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setApellido_p(dto.getApellido_p());
        persona.setApellido_m(dto.getApellido_m());
        persona.setEmail(dto.getEmail());
        persona.setTelefono(dto.getTelefono());

        Persona personaGuardada = personaService.add(persona);

        // 2. Llamar al microservicio de fono
        RestTemplate restTemplate = new RestTemplate();
        FonoDTO fonoDTO = new FonoDTO(Integer.parseInt(dto.getTelefono()), personaGuardada.getId());

        restTemplate.postForObject(
            "http://localhost:8082/fonos",
            fonoDTO,
            FonoDTO.class
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(personaGuardada);
    }
}
