package com.servicio_fono.services;

import com.servicio_fono.models.Fono;
import com.servicio_fono.repositories.FonoRepository;

import com.servicio_fono.dto.PersonaDTO;
import com.servicio_fono.dto.FonoConPersonaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FonoService {

    @Autowired
    private FonoRepository fonoRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Obtener todos los fonos
    public List<Fono> obtenerTodos() {
        return fonoRepository.findAll();
    }

    // Obtener un fono por su ID
    public Fono obtenerPorId(Integer id) {
        Optional<Fono> fono = fonoRepository.findById(id);
        return fono.orElse(null);
    }

    // Crear un nuevo fono
    public Fono agregar(Fono fono) {
        return fonoRepository.save(fono);
    }

    // Actualizar un fono existente
    public Fono actualizar(Integer id, Fono fono) {
        if (fonoRepository.existsById(id)) {
            fono.setId(id);
            return fonoRepository.save(fono);
        }
        return null;
    }

    // Eliminar un fono por ID
    public Fono eliminar(Integer id) {
        Optional<Fono> fono = fonoRepository.findById(id);
        if (fono.isPresent()) {
            fonoRepository.deleteById(id);
            return fono.get();
        }
        return null;
    }

     // Obtener fono con datos de persona desde servicio-persona
    public FonoConPersonaDTO obtenerFonoConPersona(Integer id) {
        Fono fono = obtenerPorId(id);
        if (fono == null) return null;

        // Llamar al microservicio servicio-persona
        PersonaDTO persona = restTemplate.getForObject(
            "http://localhost:8081/personas/" + fono.getIdPersona(),
            PersonaDTO.class
        );

        return new FonoConPersonaDTO(fono, persona);
    }
}
