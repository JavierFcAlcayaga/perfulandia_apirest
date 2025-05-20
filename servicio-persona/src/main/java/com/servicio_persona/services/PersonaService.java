package com.servicio_persona.services;

import com.servicio_persona.models.Persona;
import com.servicio_persona.repositories.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service// Anotación que indica que esta clase es un servicio de negocio
public class PersonaService {
    @Autowired// Inyecta automáticamente la dependencia del repositorio
    private PersonaRepository personaRepository;

    // Obtener todas las personas desde la base de datos    
    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    // Buscar una persona por su ID
    public Persona obtenerPorId(Integer id) {
        Optional<Persona> persona = personaRepository.findById(id);// Busca por ID
        return persona.orElse(null); // Si no la encuentra, retorna null
    }

    // Crear una nueva persona(Guardar nueva persona en la base de datos)
    public Persona add(Persona persona) {
        return personaRepository.save(persona);// Guarda y retorna la nueva persona
    }

     // Actualizar una persona existente
    public Persona update(Integer id, Persona persona) {
        if (personaRepository.existsById(id)) {
            persona.setId(id); // Aseguramos que se use el mismo ID
            return personaRepository.save(persona); // Guarda los cambios
        }
        return null; // No se encontró la persona
    }

    // Eliminar una persona por ID
    public Persona eliminar(Integer id) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isPresent()) {
            personaRepository.deleteById(id); // Elimina la persona
            return persona.get(); // Retorna la persona eliminada
        }
        return null; // No existe la persona
    }
   
}
