package com.servicio_fono.controllers;

import com.servicio_fono.dto.FonoConPersonaDTO;
import com.servicio_fono.models.Fono;
import com.servicio_fono.services.FonoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonos")
public class FonoController {

    @Autowired
    private FonoService fonoService;

    // GET todos los fonos
    @GetMapping
    public ResponseEntity<List<Fono>> obtenerTodos() {
        return ResponseEntity.ok(fonoService.obtenerTodos());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Fono fono = fonoService.obtenerPorId(id);
        if (fono != null) {
            return ResponseEntity.ok(fono);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fono no encontrado");
        }
    }

    // POST crear nuevo fono
    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Fono fono) {
        Fono nuevo = fonoService.agregar(fono);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Fono fono) {
        Fono actualizado = fonoService.actualizar(id, fono);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fono no encontrado");
        }
    }

    // DELETE eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Fono eliminado = fonoService.eliminar(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fono no encontrado");
        }
    }

    @GetMapping("/con-persona/{id}")
    public ResponseEntity<?> obtenerFonoConPersona(@PathVariable Integer id) {
        FonoConPersonaDTO dto = fonoService.obtenerFonoConPersona(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fono no encontrado");
        }
    }
}
