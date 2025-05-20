package com.servicio_persona.controllers;

import com.servicio_persona.models.Persona;
import com.servicio_persona.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
