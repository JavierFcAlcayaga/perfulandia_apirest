package com.servicio_persona.models;

import jakarta.persistence.*;
import lombok.*;

//import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 45)
    private String apellido_p;

    @Column(nullable = false, length = 45)
    private String apellido_m;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 50)
    private String email;

    // Relación uno a muchos con Fono. Una persona puede tener muchos telefonos.

    //@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    //private List<Fono> fonos;

    // Relación uno a muchos con Venta. Una persona puede realizar muchas ventas.
    
    //@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnore
    //private List<Venta> ventas;
}
