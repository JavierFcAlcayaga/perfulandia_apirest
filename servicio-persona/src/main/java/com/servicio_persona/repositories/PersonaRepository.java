package com.servicio_persona.repositories;

import com.servicio_persona.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un componente tipo repositorio
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    // No se necesita implementar nada: Spring genera los métodos automáticamente
}

