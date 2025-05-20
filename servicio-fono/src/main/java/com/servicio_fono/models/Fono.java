package com.servicio_fono.models;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fono")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona; // asi guardara la relación por ID, sin importar entidad Persona y asi servicio_fono es mas independiente
}
