package com.servicio_fono.repositories;

import com.servicio_fono.models.Fono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un componente tipo repositorio
public interface FonoRepository extends JpaRepository<Fono, Integer> {
    // No se necesita implementar nada: Spring genera los métodos automáticamente
}
