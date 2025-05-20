package com.servicio_persona.dto;

import lombok.Data;

@Data
public class PersonaConFonoDTO {
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private String email;
    private String telefono; // Esto se guardará también como fono
}
